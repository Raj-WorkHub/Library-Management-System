package com.library.repository;


import com.library.model.Patron;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


public class InMemoryPatronRepository implements PatronRepository {
    private final ConcurrentMap<String, Patron> store = new ConcurrentHashMap<>();


    @Override
    public void save(Patron patron) { store.put(patron.getId(), patron); }


    @Override
    public Optional<Patron> findById(String id) { return Optional.ofNullable(store.get(id)); }


    @Override
    public void deleteById(String id) { store.remove(id); }


    @Override
    public List<Patron> findAll() { return new ArrayList<>(store.values()); }
}