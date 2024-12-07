package library;

import java.util.ArrayList;

public class Student {

	// Static Variable
	/**
	 * maximum number of books a PHD students can borrow
	 */
	static int MAX_NUMBER_PHD = 9;

	/**
	 * maximum number of books that a master student can borrow
	 */
	static int MAX_NUMBER_MASTER = 7;

	/**
	 * maximum number of books other people can borrow
	 */
	static int MAX_NUMBER_OTHERS = 4;

	// Instance variables

	/**
	 * Name of Student
	 */
	String name;

	/**
	 * Student ID
	 */
	String ID;

	/**
	 * A list that contains number of books the student borrowed
	 */
	ArrayList<Book> listBooks = new ArrayList<Book>();

	// Constructor

	/**
	 * Constructor of the Student class: Create a Student object for the given name
	 * and given ID number
	 * 
	 * @param name
	 * @param ID
	 */
	public Student(String name, String ID) {
		this.name = name;
		this.ID = ID;
	}

	/**
	 * Override the toString() method return student name and ID
	 */
	public String toString() {
		return this.name + " (ID: " + this.ID + ")";
	}

	// methods
	/**
	 * Student borrows the given book
	 * 
	 * @param book to borrow
	 * @throws Exception if the student already borrowed this book, if student met
	 *                   the maximum number of books that she/he can borrow, if the
	 *                   book is not available (book.availableNumber =0)
	 */
	public void borrowBook(Book book) throws Exception {
		// check the book is in list of student's book
		if (this.listBooks.contains(book)) {
			throw new Exception("Student already borrowed this book"); // throw exception and exit the program
		}
		// check student meet the maximum number of books
		if (this.metMax()) {
			throw new Exception("Already achieved the maximum number of books that the student can borrow");
		}
		if (!book.checkAvailability()) {
			throw new Exception("This book is not available");
		}
		book.availableNumber -= 1;
		this.listBooks.addFirst(book);

	}

	/**
	 * Check whether the student based on its program (Ph.D. (student ID starts with
	 * `p`), master (student ID starts with `m`), and other) reached the maximum
	 * number of books who can borrowed
	 * 
	 * @return true if reached
	 */
	public boolean metMax() {
		if (this.ID.startsWith("p")) {
			return (this.listBooks.size() == Student.MAX_NUMBER_PHD);
		} else if (this.ID.startsWith("m")) {
			return (this.listBooks.size() == Student.MAX_NUMBER_MASTER);
		} else {
			return (this.listBooks.size() == Student.MAX_NUMBER_OTHERS);
		}
	}

	/**
	 * print list of books that the student borrowed (call book.toString())
	 */
	public void printListBooks() {
		System.out.println(this.toString() + " has borrowed " + this.listBooks.size() + " books.");
		System.out.println("List of books:");
		for (Book b : this.listBooks) {
			System.out.println(b);// call book.toString()
		}
	}

	/**
	 * Return a book to the library by removing it from list of borrowed books and
	 * and add one to available items
	 * 
	 * @param book to return
	 * @throws Exception if the book does not exist in the list of student's book
	 */
	public void returnBook(Book book) throws Exception {
		// First check if the book does not exist in the list of books, then throw an
		// exception and exist the program
		if (! this.listBooks.contains(book)) {
			throw new Exception("The book does not exist in the list of your books.");
		}
		// remove the book from listBooks
		this.listBooks.remove(book);
		book.availableNumber += 1;

	}

}
