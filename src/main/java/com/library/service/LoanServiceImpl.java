package com.library.service;

import com.library.model.Book;
import com.library.model.Loan;
import com.library.model.Patron;
import com.library.repository.BookRepository;
import com.library.repository.LoanRepository;
import com.library.repository.PatronRepository;
import com.library.util.LoggerUtil;
import com.library.designpatterns.observer.NotificationService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;

/**
 * Loan service handles checkout and return. Also simple reservation queue + notification.
 */
public class LoanServiceImpl implements LoanService {
    private final BookRepository bookRepo;
    private final PatronRepository patronRepo;
    private final LoanRepository loanRepo;
    private final Logger logger = LoggerUtil.getLogger(LoanServiceImpl.class);

    // Reservations: isbn -> queue of patronIds waiting
    private final ConcurrentMap<String, Queue<String>> reservations = new ConcurrentHashMap<>();

    private final NotificationService notificationService;

    public LoanServiceImpl(BookRepository bookRepo, PatronRepository patronRepo, LoanRepository loanRepo,
                           NotificationService notificationService) {
        this.bookRepo = bookRepo;
        this.patronRepo = patronRepo;
        this.loanRepo = loanRepo;
        this.notificationService = notificationService;
    }

    @Override
    public Optional<Loan> checkout(String isbn, String patronId, int days) {
        Optional<Book> ob = bookRepo.findByIsbn(isbn);
        if (ob.isEmpty()) {
            logger.warning(() -> "Checkout failed: book not found " + isbn);
            return Optional.empty();
        }
        Book book = ob.get();
        if (!book.isAvailable()) {
            // add to reservation queue
            reservations.computeIfAbsent(isbn, k -> new ConcurrentLinkedQueue<>()).add(patronId);
            logger.info(() -> "Book not available, patron added to reservation queue: " + patronId);
            return Optional.empty();
        }

        Optional<Patron> op = patronRepo.findById(patronId);
        if (op.isEmpty()) {
            logger.warning(() -> "Checkout failed: patron not found " + patronId);
            return Optional.empty();
        }

        // create loan
        LocalDate checkoutDate = LocalDate.now();
        LocalDate dueDate = checkoutDate.plusDays(days);
        Loan loan = new Loan(isbn, patronId, checkoutDate, dueDate);
        loanRepo.save(loan);

        // update book and patron
        book.setAvailable(false);
        bookRepo.save(book);

        Patron patron = op.get();
        patron.borrow(isbn);
        patronRepo.save(patron);

        logger.info(() -> "Book checked out: " + isbn + " to " + patronId);
        return Optional.of(loan);
    }

    @Override
    public boolean returnBook(String isbn, String patronId) {
        Optional<Loan> loanOpt = loanRepo.findByIsbn(isbn);
        if (loanOpt.isEmpty()) {
            logger.warning(() -> "Return failed: loan not found for isbn=" + isbn);
            return false;
        }
        Loan loan = loanOpt.get();
        if (!loan.getPatronId().equals(patronId)) {
            logger.warning(() -> "Return failed: patron mismatch");
            return false;
        }

        // remove loan
        loanRepo.deleteById(loan.getId());

        // update book
        bookRepo.findByIsbn(isbn).ifPresent(b -> {
            b.setAvailable(true);
            bookRepo.save(b);
        });

        // update patron
        patronRepo.findById(patronId).ifPresent(p -> {
            p.returned(isbn);
            patronRepo.save(p);
        });

        logger.info(() -> "Book returned: " + isbn + " by " + patronId);

        // If reservations exist, notify next patron
        Queue<String> q = reservations.get(isbn);
        if (q != null) {
            String next = q.poll();
            if (next != null) {
                String message = "Book " + isbn + " is now available.";
                notificationService.notifyObservers(isbn, message);
                logger.info(() -> "Notified reserved patrons for isbn=" + isbn);
            }
        }

        return true;
    }

    @Override
    public List<Loan> listActiveLoans() { return loanRepo.findAllActive(); }
}
