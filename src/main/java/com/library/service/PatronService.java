package com.library.service;


import com.library.model.Patron;
import java.util.List;
import java.util.Optional;


public interface PatronService {
    void addPatron(Patron patron);
    void updatePatron(String id, String name, String contact);
    Optional<Patron> findById(String id);
    List<Patron> listAll();
}