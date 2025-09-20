package com.library.model;


import java.time.LocalDate;
import java.util.UUID;


/** A loan record for a borrowed book. */
public class Loan {
    private final String id;
    private final String isbn;
    private final String patronId;
    private final LocalDate checkoutDate;
    private final LocalDate dueDate;


    public Loan(String isbn, String patronId, LocalDate checkoutDate, LocalDate dueDate) {
        this.id = UUID.randomUUID().toString();
        this.isbn = isbn;
        this.patronId = patronId;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
    }


    public String getId() { return id; }
    public String getIsbn() { return isbn; }
    public String getPatronId() { return patronId; }
    public LocalDate getCheckoutDate() { return checkoutDate; }
    public LocalDate getDueDate() { return dueDate; }


    @Override
    public String toString() {
        return String.format("Loan{id=%s, isbn=%s, patron=%s, from=%s, due=%s}",
                id, isbn, patronId, checkoutDate, dueDate);
    }
}