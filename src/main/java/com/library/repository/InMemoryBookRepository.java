package com.library.repository;


import com.library.model.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


/** Simple in-memory repository for books. */
public class InMemoryBookRepository implements BookRepository {
    private final ConcurrentMap<String, Book> store = new ConcurrentHashMap<>();


    @Override
    public void save(Book book) { store.put(book.getIsbn(), book); }


    @Override
    public Optional<Book> findByIsbn(String isbn) { return Optional.ofNullable(store.get(isbn)); }


    @Override
    public void deleteByIsbn(String isbn) { store.remove(isbn); }


    @Override
    public List<Book> findAll() { return new ArrayList<>(store.values()); }
}