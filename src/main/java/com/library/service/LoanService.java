package com.library.service;


import com.library.model.Loan;
import java.util.List;
import java.util.Optional;


public interface LoanService {
    Optional<Loan> checkout(String isbn, String patronId, int days);
    boolean returnBook(String isbn, String patronId);
    List<Loan> listActiveLoans();
}