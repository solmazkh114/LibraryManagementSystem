package library;

import java.util.ArrayList;
import java.util.List;

public class DataStore {

	// instance vars
	/**
	 * list of all students in the library database
	 */
	private List<Student> studentsData;

	/**
	 * list of all books in library database
	 */
	private List<Book> booksData;

	// constructor
	/**
	 * Create datastore object containing a list of books and a list of students 
	 * As soon as instantiating an object from DataStore Class, load data
	 */
	public DataStore() {
		this.studentsData = new ArrayList<Student>();
		this.booksData = new ArrayList<Book>();
		loadData();
	}
	
	/**
	 * Add books and students to their list
	 */
	private void loadData() {
		// add some initial books

		booksData.add(new Book("Calculus", "James Stewart", "1204-MATH", 5));
		booksData.add(new Book("Linear Algebra", "Gilbert Strang", "5578-MATH", 2));
		booksData.add(new Book("Organic Chemistry", "Paula Yurkanis Bruice", "9121-CHEM", 9));
		booksData.add(new Book("Physics Principles", "David Halliday", "1122-PHY", 10));
		booksData.add(new Book("Probability Theory", "Joseph K. Blitzstein", "3344-MATH", 3));
		booksData.add(new Book("Introduction to Biochemistry", "Jeremy M. Berg", "5566-BIO", 8));
		booksData.add(new Book("Thermodynamics", "Richard E. Sonntag", "7788-ENG", 4));

		// add some initial students
		studentsData.add(new Student("Alice Smith", "p-12345"));
		studentsData.add(new Student("Bob Johnson", "b-67890"));
		studentsData.add(new Student("Charlie Brown", "b-54321"));
		studentsData.add(new Student("David Lee", "s-98765"));
		studentsData.add(new Student("Eva White", "p-11223"));
		studentsData.add(new Student("Frank Green", "m-44567"));
		studentsData.add(new Student("Grace Black", "b-78901"));

		// Student borrowed books

		try {
			// Alice Smith borrows "Calculus" and "Linear Algebra"
			studentsData.get(0).borrowBook(booksData.get(0));
			studentsData.get(0).borrowBook(booksData.get(1));

			// Bob Johnson borrows "Organic Chemistry", "Physics Principles" and
			// "Probability Theory"
			studentsData.get(1).borrowBook(booksData.get(2));
			studentsData.get(1).borrowBook(booksData.get(3));
			studentsData.get(1).borrowBook(booksData.get(4));

			// David Lee borrows "Probability Theory"
			studentsData.get(3).borrowBook(booksData.get(4));

			// Eva White borrows "Introduction to Biochemistry" and "Thermodynamics"
			studentsData.get(4).borrowBook(booksData.get(5));
			studentsData.get(4).borrowBook(booksData.get(6));

			// Frank Green (Master's student) borrows "Linear Algebra"
			studentsData.get(5).borrowBook(booksData.get(1));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Finds students using their IDs
	 * @param studentID to find
	 * @return student with given ID
	 * @throws Exception when the ID does not exist in the studetnsData
	 */
	public Student findStudentByID(String studentID) throws Exception {
		for (Student s : studentsData) {
			if (s.ID.equals(studentID)) {
				return s;
			}
		}
		throw new Exception("Not found!");
	}

	/**
	 * Find students using their name
	 * @param name to find
	 * @return student with given name
	 * @throws Exception when the name does not exist in the studetnsData
	 */
	public Student findStudentByName(String name) throws Exception {
		for (Student s : studentsData) {
			if (s.name.equals(name)) {
				return s;
			}
		}
		throw new Exception("Not found!");
	}

	/**
	 * Finds a book using its ID
	 * @param ID to find
	 * @return a book with the given ID
	 * @throws Exception when the ID does not exist in the booksData
	 */
	public Book findBookByID(String ID) throws Exception {
		for (Book b : booksData) {
			if (b.ID.equals(ID)) {
				return b;
			}
		}
		throw new Exception("Not found!");
	}

	/**
	 * Find a book by its title
	 * @param title to find
	 * @return a book with the given title
	 * @throws Exception when title does not exist in the booksData
	 */
	public Book findBookByTitle(String title) throws Exception {
		for (Book b : booksData) {
			if (b.title.equals(title)) {
				return b;
			}
		}
		throw new Exception("Not found!");
	}

}
