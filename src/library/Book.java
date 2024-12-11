package library;

public class Book {
	// Instance variables

	/**
	 * name of the book
	 */
	String title;

	/**
	 * name of the writer of the book
	 */
	String author;

	/**
	 * the book's ID
	 */
	String ID;

	/**
	 * the total number of copies of the book in the library
	 */
	int totalNumCopies = 0;

	/**
	 * the available number of the copies of the book in the library
	 */
	int availableNumCopies = 0;

	/**
	 * Book ISBN number
	 */
	String isbn;

	/**
	 * Constructor of the book's class: create a book object
	 * 
	 * @param title  title of the book
	 * @param author name of the author of the book
	 * @param ID     the book's id
	 */

	// class Constructor
	public Book(String ID, String title, String author) {
		this.title = title;
		this.author = author;
		this.ID = ID;
		this.isbn = null;
	}

	/**
	 * returns the id, title and name of the author
	 */
	public String toString() {
		return this.ID + " : " + this.title + " : " + this.author;
	}

	/**
	 * override equals method to return true if id and title of the two books are
	 * the same
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null || getClass() != obj.getClass())
			return false;

		Book book = (Book) obj;
		return ID.equals(book.ID) && title.equals(book.title);
	}
}
