package com.library;

import com.library.designpatterns.factory.BookFactory;
import com.library.designpatterns.observer.NotificationService;
import com.library.designpatterns.observer.Observer;
import com.library.model.Book;
import com.library.model.Patron;
import com.library.repository.*;
import com.library.service.*;

import java.util.Optional;

/** Simple demonstration main. */
public class Main {
    public static void main(String[] args) {
        // repositories
        InMemoryBookRepository bookRepo = new InMemoryBookRepository();
        InMemoryPatronRepository patronRepo = new InMemoryPatronRepository();
        InMemoryLoanRepository loanRepo = new InMemoryLoanRepository();

        // services
        BookService bookService = new BookServiceImpl(bookRepo);
        PatronService patronService = new PatronServiceImpl(patronRepo);
        NotificationService notificationService = new NotificationService();
        LoanService loanService = new LoanServiceImpl(bookRepo, patronRepo, loanRepo, notificationService);

        // create sample data
        Book b1 = BookFactory.create("978-0134685991", "Effective Java", "Joshua Bloch", 2018);
        Book b2 = BookFactory.create("978-0596009205", "Head First Java", "Kathy Sierra", 2005);
        bookService.addBook(b1);
        bookService.addBook(b2);

        Patron p1 = new Patron("P001", "Alice", "alice@example.com");
        Patron p2 = new Patron("P002", "Bob", "bob@example.com");
        patronService.addPatron(p1);
        patronService.addPatron(p2);

        // simple observer implementation to print notifications
        Observer o1 = message -> System.out.println("[Notification to Alice] " + message);
        Observer o2 = message -> System.out.println("[Notification to Bob] " + message);

        // register observers for book b1
        notificationService.registerObserver(b1.getIsbn(), o1);
        notificationService.registerObserver(b1.getIsbn(), o2);

        // Checkout b1 to Alice
        Optional.ofNullable(loanService.checkout(b1.getIsbn(), p1.getId(), 14)).ifPresent(l ->
                System.out.println("Loan created: " + l));

        // Bob tries to checkout same book -> will be added to reservation queue
        Optional.ofNullable(loanService.checkout(b1.getIsbn(), p2.getId(), 14)).ifPresent(l ->
                System.out.println("Loan created: " + l));

        // Alice returns the book -> should notify Bob
        boolean returned = loanService.returnBook(b1.getIsbn(), p1.getId());
        System.out.println("Returned: " + returned);

        // Search examples
        System.out.println("Search by title 'Effective': " + bookService.searchByTitle("Effective"));
        System.out.println("All books: " + bookService.listAll());

        // Show patrons and history
        patronService.findById(p1.getId()).ifPresent(p -> System.out.println("Patron: " + p + " history=" + p.getHistory()));
        patronService.findById(p2.getId()).ifPresent(p -> System.out.println("Patron: " + p + " history=" + p.getHistory()));
    }
}
