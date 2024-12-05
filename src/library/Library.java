package library;

import java.util.Scanner;

public class Library {

	private DataStore dataStore; //Declare dataStore as private to prevent direct access from outside this class.


	//Class Constructor
	/**
	 * Instantiate the DataStore class automatically when the Library class is instantiated
	 */
	public Library() {
		dataStore = new DataStore();
	}
	
	public static void main(String[] args) {
		
		Library library = new Library();
		library.run();
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
		scanner.nextLine(); //to consume new line character

		if (userIntention == 1) {
			Student s = null;
			Book b = null;
			System.out.println("Enter full name of student:");
			String inputStudentName = scanner.nextLine();
			try {
				s = dataStore.findStudentByName(inputStudentName);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("Enter book title: ");
			String inputBookTitle = scanner.nextLine();
			try {
				b = dataStore.findBookByTitle(inputBookTitle);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				s.borrowBook(b);
			} catch (Exception e) {
				e.printStackTrace();
			}
			s.printListBooks();
		} else if (userIntention == 2) {
			Student s = null;
			Book b = null;
			System.out.println("Enter full name of student:");
			String inputStudentName = scanner.nextLine();
			try {
				s = dataStore.findStudentByName(inputStudentName);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("Enter book title: ");
			String inputBookTitle = scanner.nextLine();
			try {
				b = dataStore.findBookByTitle(inputBookTitle);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				s.returnBook(b);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			s.printListBooks();
			
		}
		scanner.close();
	}
}
