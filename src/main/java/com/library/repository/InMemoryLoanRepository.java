package com.library.repository;


import com.library.model.Loan;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


public class InMemoryLoanRepository implements LoanRepository {
    private final ConcurrentMap<String, Loan> store = new ConcurrentHashMap<>();


    @Override
    public void save(Loan loan) { store.put(loan.getId(), loan); }


    @Override
    public Optional<Loan> findByIsbn(String isbn) {
        return store.values().stream().filter(l -> l.getIsbn().equals(isbn)).findFirst();
    }


    @Override
    public Optional<Loan> findById(String id) { return Optional.ofNullable(store.get(id)); }


    @Override
    public void deleteById(String id) { store.remove(id); }


    @Override
    public List<Loan> findAllActive() { return new ArrayList<>(store.values()); }
}