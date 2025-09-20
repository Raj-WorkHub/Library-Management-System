# Library Management System

A Java-based Library Management System demonstrating Object-Oriented Programming principles, SOLID design principles, and common design patterns.

## Features

- **Book Management**: Add, update, remove, and search books
- **Patron Management**: Register and manage library members
- **Loan Operations**: Check out and return books with due date tracking
- **Reservation System**: Queue system for unavailable books
- **Notification System**: Alerts when reserved books become available
- **Search Functionality**: Search books by title, author, or ISBN

## Project Structure

```
src/com/library/
├── Main.java                    # Application entry point & demo
├── model/                       # Domain entities
│   ├── Book.java               # Book entity with ISBN, title, author
│   ├── Patron.java             # Library member with borrowing history
│   └── Loan.java               # Loan record with dates
├── repository/                  # Data access layer
│   ├── *Repository.java        # Repository interfaces
│   └── InMemory*Repository.java # In-memory implementations
├── service/                     # Business logic layer
│   ├── *Service.java           # Service interfaces
│   └── *ServiceImpl.java       # Service implementations
├── designpatterns/              # Design pattern implementations
│   ├── factory/BookFactory.java
│   └── observer/               # Observer pattern for notifications
└── util/LoggerUtil.java        # Logging utility
```

## Design Patterns Used

1. **Repository Pattern**: Abstracts data access (BookRepository, PatronRepository, LoanRepository)
2. **Factory Pattern**: BookFactory for creating book instances
3. **Observer Pattern**: NotificationService for book availability alerts

## SOLID Principles Implementation

- **Single Responsibility**: Each class has one clear purpose
- **Open/Closed**: Extensible through interfaces
- **Liskov Substitution**: Repository implementations are interchangeable
- **Interface Segregation**: Focused, specific interfaces
- **Dependency Inversion**: Services depend on abstractions

## Class Diagram



## How to Run

1. **Prerequisites**: Java 8+

2. **Compile**:
   ```bash
   javac -d build src/com/library/**/*.java
   ```

3. **Run**:
   ```bash
   java -cp build com.library.Main
   ```

## Demo Output

The `Main.java` demonstrates:
- Creating books using BookFactory
- Registering patrons
- Book checkout and return operations
- Reservation queue when book is unavailable
- Notification system when book becomes available
- Search functionality

Expected output shows loan creation, notifications, and search results.

## Key Classes

### Model Layer
- **Book**: Represents library books with ISBN, availability status
- **Patron**: Library members with borrowing history
- **Loan**: Records of borrowed books with due dates

### Service Layer
- **BookService**: Book CRUD operations and search
- **PatronService**: Patron management
- **LoanService**: Checkout/return logic with reservation handling

### Repository Layer
- In-memory storage implementations
- Thread-safe using ConcurrentHashMap
- Easy to extend with database implementations

## Architecture

Uses layered architecture with dependency injection:
```
Main → Services → Repositories → Models
     ↘ Design Patterns (Factory, Observer)
```

This project demonstrates clean code principles, proper separation of concerns, and extensible design suitable for real-world applications.
