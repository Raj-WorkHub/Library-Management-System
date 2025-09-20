package com.library.repository;


import com.library.model.Patron;
import java.util.List;
import java.util.Optional;


public interface PatronRepository {
    void save(Patron patron);
    Optional<Patron> findById(String id);
    void deleteById(String id);
    List<Patron> findAll();
}