package com.library.service;


import com.library.model.Book;
import java.util.List;
import java.util.Optional;


public interface BookService {
    void addBook(Book book);
    void updateBook(String isbn, String title, String author, int year);
    void removeBook(String isbn);


    Optional<Book> findByIsbn(String isbn);
    List<Book> searchByTitle(String title);
    List<Book> searchByAuthor(String author);
    List<Book> listAll();
}