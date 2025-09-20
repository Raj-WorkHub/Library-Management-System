package com.library.model;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/** Represents a library patron (member). */
public class Patron {
    private final String id; // unique
    private String name;
    private String contactInfo;


    // Currently borrowed book ISBNs
    private final Set<String> borrowedBooks = new HashSet<>();


    // Borrowing history (ISBNs)
    private final List<String> history = new ArrayList<>();


    public Patron(String id, String name, String contactInfo) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
    }


    public String getId() { return id; }
    public String getName() { return name; }
    public String getContactInfo() { return contactInfo; }


    public void setName(String name) { this.name = name; }
    public void setContactInfo(String contactInfo) { this.contactInfo = contactInfo; }


    public Set<String> getBorrowedBooks() { return borrowedBooks; }
    public List<String> getHistory() { return history; }


    public void borrow(String isbn) {
        borrowedBooks.add(isbn);
        history.add(isbn);
    }


    public void returned(String isbn) {
        borrowedBooks.remove(isbn);
    }


    @Override
    public String toString() {
        return String.format("Patron{id='%s', name='%s', contact='%s'}", id, name, contactInfo);
    }
}