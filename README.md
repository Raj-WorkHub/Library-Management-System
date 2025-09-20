![uml_class_diagram](https://github.com/user-attachments/assets/e976b762-456b-4bc5-9c9c-2b3972d41652)# Library Management System

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

![Upload<svg width="1200" height="800" xmlns="http://www.w3.org/2000/svg">
  <defs>
    <style>
      .class-box { fill: #ffffcc; stroke: #000; stroke-width: 1.5; }
      .interface-box { fill: #e6f3ff; stroke: #000; stroke-width: 1.5; }
      .abstract-box { fill: #f0f0f0; stroke: #000; stroke-width: 1.5; }
      .class-name { font-family: Arial, sans-serif; font-size: 12px; font-weight: bold; text-anchor: middle; }
      .interface-name { font-family: Arial, sans-serif; font-size: 12px; font-weight: bold; font-style: italic; text-anchor: middle; }
      .attribute { font-family: Arial, sans-serif; font-size: 10px; }
      .method { font-family: Arial, sans-serif; font-size: 10px; }
      .stereotype { font-family: Arial, sans-serif; font-size: 9px; font-style: italic; text-anchor: middle; }
      .arrow { fill: none; stroke: #000; stroke-width: 1.5; marker-end: url(#arrowhead); }
      .implements { fill: none; stroke: #000; stroke-width: 1.5; stroke-dasharray: 5,5; marker-end: url(#triangle); }
      .dependency { fill: none; stroke: #000; stroke-width: 1.5; stroke-dasharray: 3,3; marker-end: url(#arrowhead); }
      .composition { fill: none; stroke: #000; stroke-width: 1.5; marker-end: url(#diamond); }
    </style>
    <marker id="arrowhead" markerWidth="10" markerHeight="7" refX="9" refY="3.5" orient="auto">
      <polygon points="0 0, 10 3.5, 0 7" fill="#000" />
    </marker>
    <marker id="triangle" markerWidth="10" markerHeight="7" refX="9" refY="3.5" orient="auto">
      <polygon points="0 0, 10 3.5, 0 7" fill="white" stroke="#000" stroke-width="1"/>
    </marker>
    <marker id="diamond" markerWidth="10" markerHeight="7" refX="9" refY="3.5" orient="auto">
      <polygon points="0 3.5, 5 0, 10 3.5, 5 7" fill="white" stroke="#000" stroke-width="1"/>
    </marker>
  </defs>

  <!-- Model Classes -->
  <!-- Book Class -->
  <g>
    <rect class="class-box" x="50" y="50" width="180" height="120"/>
    <text class="class-name" x="140" y="70">Book</text>
    <line x1="50" y1="75" x2="230" y2="75" stroke="#000" stroke-width="1"/>
    <text class="attribute" x="55" y="90">- isbn: String</text>
    <text class="attribute" x="55" y="105">- title: String</text>
    <text class="attribute" x="55" y="120">- author: String</text>
    <text class="attribute" x="55" y="135">- available: boolean</text>
    <line x1="50" y1="140" x2="230" y2="140" stroke="#000" stroke-width="1"/>
    <text class="method" x="55" y="155">+ getters/setters()</text>
  </g>

  <!-- Patron Class -->
  <g>
    <rect class="class-box" x="280" y="50" width="200" height="140"/>
    <text class="class-name" x="380" y="70">Patron</text>
    <line x1="280" y1="75" x2="480" y2="75" stroke="#000" stroke-width="1"/>
    <text class="attribute" x="285" y="90">- id: String</text>
    <text class="attribute" x="285" y="105">- name: String</text>
    <text class="attribute" x="285" y="120">- borrowedBooks: Set&lt;String&gt;</text>
    <text class="attribute" x="285" y="135">- history: List&lt;String&gt;</text>
    <line x1="280" y1="140" x2="480" y2="140" stroke="#000" stroke-width="1"/>
    <text class="method" x="285" y="155">+ borrow(isbn: String)</text>
    <text class="method" x="285" y="170">+ returned(isbn: String)</text>
  </g>

  <!-- Loan Class -->
  <g>
    <rect class="class-box" x="520" y="50" width="180" height="140"/>
    <text class="class-name" x="610" y="70">Loan</text>
    <line x1="520" y1="75" x2="700" y2="75" stroke="#000" stroke-width="1"/>
    <text class="attribute" x="525" y="90">- id: String</text>
    <text class="attribute" x="525" y="105">- isbn: String</text>
    <text class="attribute" x="525" y="120">- patronId: String</text>
    <text class="attribute" x="525" y="135">- checkoutDate: LocalDate</text>
    <text class="attribute" x="525" y="150">- dueDate: LocalDate</text>
    <line x1="520" y1="155" x2="700" y2="155" stroke="#000" stroke-width="1"/>
    <text class="method" x="525" y="170">+ getters()</text>
  </g>

  <!-- Repository Interfaces -->
  <!-- BookRepository Interface -->
  <g>
    <rect class="interface-box" x="50" y="250" width="180" height="100"/>
    <text class="stereotype" x="140" y="265">&lt;&lt;interface&gt;&gt;</text>
    <text class="interface-name" x="140" y="280">BookRepository</text>
    <line x1="50" y1="285" x2="230" y2="285" stroke="#000" stroke-width="1"/>
    <text class="method" x="55" y="300">+ save(book: Book)</text>
    <text class="method" x="55" y="315">+ findByIsbn(isbn: String)</text>
    <text class="method" x="55" y="330">+ findAll(): List&lt;Book&gt;</text>
  </g>

  <!-- PatronRepository Interface -->
  <g>
    <rect class="interface-box" x="280" y="250" width="200" height="100"/>
    <text class="stereotype" x="380" y="265">&lt;&lt;interface&gt;&gt;</text>
    <text class="interface-name" x="380" y="280">PatronRepository</text>
    <line x1="280" y1="285" x2="480" y2="285" stroke="#000" stroke-width="1"/>
    <text class="method" x="285" y="300">+ save(patron: Patron)</text>
    <text class="method" x="285" y="315">+ findById(id: String)</text>
    <text class="method" x="285" y="330">+ findAll(): List&lt;Patron&gt;</text>
  </g>

  <!-- LoanRepository Interface -->
  <g>
    <rect class="interface-box" x="520" y="250" width="180" height="100"/>
    <text class="stereotype" x="610" y="265">&lt;&lt;interface&gt;&gt;</text>
    <text class="interface-name" x="610" y="280">LoanRepository</text>
    <line x1="520" y1="285" x2="700" y2="285" stroke="#000" stroke-width="1"/>
    <text class="method" x="525" y="300">+ save(loan: Loan)</text>
    <text class="method" x="525" y="315">+ findByIsbn(isbn: String)</text>
    <text class="method" x="525" y="330">+ findAllActive(): List&lt;Loan&gt;</text>
  </g>

  <!-- Repository Implementations -->
  <!-- InMemoryBookRepository -->
  <g>
    <rect class="class-box" x="50" y="400" width="180" height="80"/>
    <text class="class-name" x="140" y="420">InMemoryBookRepository</text>
    <line x1="50" y1="425" x2="230" y2="425" stroke="#000" stroke-width="1"/>
    <text class="attribute" x="55" y="440">- store: ConcurrentMap</text>
    <line x1="50" y1="445" x2="230" y2="445" stroke="#000" stroke-width="1"/>
    <text class="method" x="55" y="460">+ implemented methods</text>
  </g>

  <!-- InMemoryPatronRepository -->
  <g>
    <rect class="class-box" x="280" y="400" width="200" height="80"/>
    <text class="class-name" x="380" y="420">InMemoryPatronRepository</text>
    <line x1="280" y1="425" x2="480" y2="425" stroke="#000" stroke-width="1"/>
    <text class="attribute" x="285" y="440">- store: ConcurrentMap</text>
    <line x1="280" y1="445" x2="480" y2="445" stroke="#000" stroke-width="1"/>
    <text class="method" x="285" y="460">+ implemented methods</text>
  </g>

  <!-- InMemoryLoanRepository -->
  <g>
    <rect class="class-box" x="520" y="400" width="180" height="80"/>
    <text class="class-name" x="610" y="420">InMemoryLoanRepository</text>
    <line x1="520" y1="425" x2="700" y2="425" stroke="#000" stroke-width="1"/>
    <text class="attribute" x="525" y="440">- store: ConcurrentMap</text>
    <line x1="520" y1="445" x2="700" y2="445" stroke="#000" stroke-width="1"/>
    <text class="method" x="525" y="460">+ implemented methods</text>
  </g>

  <!-- Service Layer -->
  <!-- BookService Interface -->
  <g>
    <rect class="interface-box" x="750" y="250" width="180" height="120"/>
    <text class="stereotype" x="840" y="265">&lt;&lt;interface&gt;&gt;</text>
    <text class="interface-name" x="840" y="280">BookService</text>
    <line x1="750" y1="285" x2="930" y2="285" stroke="#000" stroke-width="1"/>
    <text class="method" x="755" y="300">+ addBook(book: Book)</text>
    <text class="method" x="755" y="315">+ updateBook(...)</text>
    <text class="method" x="755" y="330">+ searchByTitle(title: String)</text>
    <text class="method" x="755" y="345">+ searchByAuthor(author: String)</text>
    <text class="method" x="755" y="360">+ findByIsbn(isbn: String)</text>
  </g>

  <!-- BookServiceImpl -->
  <g>
    <rect class="class-box" x="750" y="400" width="180" height="100"/>
    <text class="class-name" x="840" y="420">BookServiceImpl</text>
    <line x1="750" y1="425" x2="930" y2="425" stroke="#000" stroke-width="1"/>
    <text class="attribute" x="755" y="440">- repository: BookRepository</text>
    <text class="attribute" x="755" y="455">- logger: Logger</text>
    <line x1="750" y1="460" x2="930" y2="460" stroke="#000" stroke-width="1"/>
    <text class="method" x="755" y="475">+ implemented methods</text>
  </g>

  <!-- LoanService Interface -->
  <g>
    <rect class="interface-box" x="980" y="250" width="200" height="100"/>
    <text class="stereotype" x="1080" y="265">&lt;&lt;interface&gt;&gt;</text>
    <text class="interface-name" x="1080" y="280">LoanService</text>
    <line x1="980" y1="285" x2="1180" y2="285" stroke="#000" stroke-width="1"/>
    <text class="method" x="985" y="300">+ checkout(...)</text>
    <text class="method" x="985" y="315">+ returnBook(...)</text>
    <text class="method" x="985" y="330">+ listActiveLoans()</text>
  </g>

  <!-- LoanServiceImpl -->
  <g>
    <rect class="class-box" x="980" y="400" width="200" height="140"/>
    <text class="class-name" x="1080" y="420">LoanServiceImpl</text>
    <line x1="980" y1="425" x2="1180" y2="425" stroke="#000" stroke-width="1"/>
    <text class="attribute" x="985" y="440">- bookRepo: BookRepository</text>
    <text class="attribute" x="985" y="455">- patronRepo: PatronRepository</text>
    <text class="attribute" x="985" y="470">- loanRepo: LoanRepository</text>
    <text class="attribute" x="985" y="485">- notifications: NotificationService</text>
    <line x1="980" y1="490" x2="1180" y2="490" stroke="#000" stroke-width="1"/>
    <text class="method" x="985" y="505">+ checkout(...)</text>
    <text class="method" x="985" y="520">+ returnBook(...)</text>
  </g>

  <!-- Design Patterns -->
  <!-- BookFactory -->
  <g>
    <rect class="class-box" x="750" y="50" width="180" height="80"/>
    <text class="stereotype" x="840" y="65">&lt;&lt;utility&gt;&gt;</text>
    <text class="class-name" x="840" y="80">BookFactory</text>
    <line x1="750" y1="85" x2="930" y2="85" stroke="#000" stroke-width="1"/>
    <line x1="750" y1="90" x2="930" y2="90" stroke="#000" stroke-width="1"/>
    <text class="method" x="755" y="105">+ create(...): Book</text>
  </g>

  <!-- NotificationService -->
  <g>
    <rect class="class-box" x="980" y="600" width="200" height="120"/>
    <text class="class-name" x="1080" y="620">NotificationService</text>
    <line x1="980" y1="625" x2="1180" y2="625" stroke="#000" stroke-width="1"/>
    <text class="attribute" x="985" y="640">- observers: Map</text>
    <text class="attribute" x="985" y="655">- logger: Logger</text>
    <line x1="980" y1="660" x2="1180" y2="660" stroke="#000" stroke-width="1"/>
    <text class="method" x="985" y="675">+ registerObserver(...)</text>
    <text class="method" x="985" y="690">+ notifyObservers(...)</text>
  </g>

  <!-- Observer Interface -->
  <g>
    <rect class="interface-box" x="750" y="600" width="180" height="60"/>
    <text class="stereotype" x="840" y="615">&lt;&lt;interface&gt;&gt;</text>
    <text class="interface-name" x="840" y="630">Observer</text>
    <line x1="750" y1="635" x2="930" y2="635" stroke="#000" stroke-width="1"/>
    <text class="method" x="755" y="650">+ update(message: String)</text>
  </g>

  <!-- Relationships -->
  <!-- Implementation arrows (dashed with triangle) -->
  <line class="implements" x1="140" y1="400" x2="140" y2="350"/>
  <line class="implements" x1="380" y1="400" x2="380" y2="350"/>
  <line class="implements" x1="610" y1="400" x2="610" y2="350"/>
  <line class="implements" x1="840" y1="400" x2="840" y2="370"/>
  <line class="implements" x1="1080" y1="400" x2="1080" y2="350"/>

  <!-- Dependency arrows (dashed) -->
  <line class="dependency" x1="750" y1="440" x2="230" y2="300"/>
  <line class="dependency" x1="980" y1="440" x2="230" y2="300"/>
  <line class="dependency" x1="980" y1="460" x2="480" y2="310"/>
  <line class="dependency" x1="980" y1="480" x2="700" y2="300"/>
  <line class="dependency" x1="1080" y1="540" x2="1080" y2="600"/>

  <!-- Factory creates relationship -->
  <line class="dependency" x1="750" y1="90" x2="230" y2="110"/>

  <!-- Observer relationship -->
  <line class="dependency" x1="980" y1="660" x2="930" y2="630"/>

  <!-- Labels for relationships -->
  <text class="stereotype" x="500" y="375">implements</text>
  <text class="stereotype" x="500" y="320">uses</text>
  <text class="stereotype" x="600" y="100">creates</text>
  <text class="stereotype" x="955" y="645">notifies</text>

</svg>ing uml_class_diagram.svg…]()


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
