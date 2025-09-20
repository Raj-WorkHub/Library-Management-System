package com.library.service;


import com.library.model.Book;
import com.library.repository.BookRepository;
import com.library.util.LoggerUtil;


import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;


public class BookServiceImpl implements BookService {
    private final BookRepository repository;
    private final Logger logger = LoggerUtil.getLogger(BookServiceImpl.class);


    public BookServiceImpl(BookRepository repository) { this.repository = repository; }


    @Override
    public void addBook(Book book) {
        repository.save(book);
        logger.info(() -> "Added book: " + book.getIsbn());
    }


    @Override
    public void updateBook(String isbn, String title, String author, int year) {
        Optional<Book> ob = repository.findByIsbn(isbn);
        if (ob.isPresent()) {
            Book b = ob.get();
            b.setTitle(title);
            b.setAuthor(author);
            b.setPublicationYear(year);
            repository.save(b);
            logger.info(() -> "Updated book: " + isbn);
        } else {
            logger.warning(() -> "Book not found for update: " + isbn);
        }
    }


    @Override
    public void removeBook(String isbn) {
        repository.deleteByIsbn(isbn);
        logger.info(() -> "Removed book: " + isbn);
    }


    @Override
    public Optional<Book> findByIsbn(String isbn) { return repository.findByIsbn(isbn); }


    @Override
    public List<Book> searchByTitle(String title) {
        return repository.findAll().stream()
                .filter(b -> b.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }


    @Override
    public List<Book> searchByAuthor(String author) {
        return repository.findAll().stream()
                .filter(b -> b.getAuthor().toLowerCase().contains(author.toLowerCase()))
                .collect(Collectors.toList());
    }


    @Override
    public List<Book> listAll() { return repository.findAll(); }
}