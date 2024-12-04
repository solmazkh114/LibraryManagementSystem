# Library Management System

This project is a basic library management system implemented in Java. The system allows students to borrow and return books. It is designed to manage student information, books in the library, and the borrowing and returning of books.

## Features
- **Borrow Books**: Allows students to borrow books from the library.
- **Return Books**: Allows students to return borrowed books.
- **Student & Book Search**: Students and books can be searched by name or title respectively.

## Classes Overview

### 1. `Library`
This is the main class that runs the library system. It interacts with users, accepts input, and calls methods from the `DataStore` and `Student` classes to borrow and return books.

#### Key Methods:
- **`run()`**: This method drives user interaction by displaying options, accepting input, and calling appropriate methods for borrowing, returning, and managing books.
- **`main(String[] args)`**: Entry point of the application where the library instance is created and the `run()` method is executed.

### 2. `DataStore`
The `DataStore` class is responsible for managing the library's data, including a list of books and students. It provides methods for adding initial data and searching for students and books by their names or IDs.

### 3. `Student`
The `Student` class represents a student in the library system. Each student has a name, ID, and a list of borrowed books.

### 4. `Book`
The `Book` class represents a book in the library. Each book has a title, author, ID, and the number of copies available.



