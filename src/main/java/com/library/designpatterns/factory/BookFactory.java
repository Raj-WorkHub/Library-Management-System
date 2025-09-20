package com.library.designpatterns.factory;


import com.library.model.Book;


/** Simple factory for Book instances. Can be extended for EBook/Audiobook types. */
public final class BookFactory {
    private BookFactory() {}


    public static Book create(String isbn, String title, String author, int year) {
        return new Book(isbn, title, author, year);
    }
}