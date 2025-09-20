package com.library.repository;


import com.library.model.Loan;
import java.util.List;
import java.util.Optional;


public interface LoanRepository {
    void save(Loan loan);
    Optional<Loan> findByIsbn(String isbn);
    Optional<Loan> findById(String id);
    void deleteById(String id);
    List<Loan> findAllActive();
}