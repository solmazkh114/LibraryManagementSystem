package library;

import java.util.ArrayList;
import java.util.Scanner;

public class Library {

	private DataStore dataStore; // Declare dataStore as private to prevent direct access from outside this
									// class.

	// Class Constructor
	/**
	 * Instantiate the DataStore class automatically when the Library class is
	 * instantiated
	 */
	public Library() {
		dataStore = new DataStore();
	}

	/**
	 * The main function of the program Responsible to run the code
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		Library library = new Library();
		library.run();
	}

	/**
	 * Get student name and book title from user Loop until a valid name and title
	 * are given
	 * 
	 * @return a an ArrayList containing founded student and founded book
	 */
	public ArrayList<Object> getInput() {
		ArrayList<Object> outputList = new ArrayList<Object>();
		Student student = null;
		Book book = null;
		Scanner scanner = new Scanner(System.in);

		// Loop until a valid student is found
		while (student == null) {
			System.out.println("Enter full name of student:");
			String inputStudentName = scanner.nextLine();
			try {
				student = dataStore.findStudentByName(inputStudentName);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		// Loop until a valid book is found
		while (book == null) {
			System.out.println("Enter book title: ");
			String inputBookTitle = scanner.nextLine();
			try {
				book = dataStore.findBookByTitle(inputBookTitle);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		outputList.add(student);
		outputList.add(book);
		return outputList;
	}

	/**
	 * Runs the program, handle users requests
	 */
	private void run() {
		System.out.println("Welcome to the Library");

		// Ask what does the user want to do?
		System.out.println("What do you want to do?");
		System.out.println("Enter 1 if a student wants to borrow a book");
		System.out.println("Enter 2 if a students wants to return a book");
//		System.out.println("Enter 3 if you want to add a new book to the library");
//		System.out.println("Enter 5 if you want to check a book info:");

		// Create a scanner instance to get the user input
		Scanner scanner = new Scanner(System.in);

		int userIntention = scanner.nextInt();
		scanner.nextLine(); // to consume new line character

		if (userIntention == 1) {

			// get student name and book title
			ArrayList<Object> inputList = getInput();
			Student student = (Student) inputList.get(0);
			Book book = (Book) inputList.get(1);

			// try to borrow the book
			try {
				student.borrowBook(book);
				System.out.println("Done!");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			student.printListBooks();

		} else if (userIntention == 2) {

			// get student name and book title
			ArrayList<Object> inputList = getInput();
			Student student = (Student) inputList.get(0);
			Book book = (Book) inputList.get(1);

			// try to return the book
			try {
				student.returnBook(book);
				System.out.println("Done!");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			student.printListBooks();

		}
		scanner.close();
	}
}
