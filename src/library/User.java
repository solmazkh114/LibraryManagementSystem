package library;

import java.util.ArrayList;

public class User {
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
	 * maximum number of books other users can borrow
	 */
	static int MAX_NUMBER_OTHERS = 4;

	/**
	 * full name of user
	 */
	String name;

	/**
	 * user ID
	 */
	String userID;

	/**
	 * A list that contains number of books the user has borrowed
	 */
	ArrayList<Book> listBooks = new ArrayList<Book>();

	/**
	 * Constructor of the class:
	 * 
	 * @param name
	 * @param ID
	 */
	public User(String userID, String name) {
		this.name = name;
		this.userID = userID;
	}

	/**
	 * Override the toString() method return user's name and ID
	 */
	public String toString() {
		return this.name + " (ID: " + this.userID + ")";
	}

	/**
	 * Check whether the user reached the maximum number of books who can borrowed
	 * 
	 * @return true if reached
	 */
	public boolean metMax() {
		if (this.userID.startsWith("p"))
			return (this.listBooks.size() == MAX_NUMBER_PHD);

		else if (this.userID.startsWith("m"))
			return (this.listBooks.size() == MAX_NUMBER_MASTER);

		else
			return (this.listBooks.size() == MAX_NUMBER_OTHERS);
	}

	/**
	 * print list of books that the user borrowed (call book.toString())
	 */
	public void printListBooks() {
		System.out.println(this.toString() + " has borrowed " + this.listBooks.size() + " books.");
		System.out.println("List of books:");
		for (Book b : this.listBooks) {
			System.out.println(b);// call book.toString()
		}
	}

	/**
	 * Check if the user is a student or a faculty member
	 * 
	 * @return true if user is a student
	 */
	public boolean checkStudent() {
		return !this.userID.startsWith("f");
	}
	
	/**
	 * Equals method for compare to objects of the class
	 * 
	 * @return true if two objects have the same name and userID
	 */
	public boolean equals(Object obj) {

		User otherUser = (User) obj;
		return this.userID.equals(otherUser.userID) && this.name.equals(otherUser.name);
	}

}
