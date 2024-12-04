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
	 * the total number of the book in the library
	 */
	int totalNumber;
	
	/**
	 * the available number of the book in the library
	 */
	int availableNumber;
	
	/**
	 * Constructor of the book's class: create a book object
	 * @param title: of the book
	 * @param writer: name of the writer of the book
	 * @param id: the book's id
	 */
	
	//class instructor
	public Book(String title, String author, String ID, int totalNumber) {
		this.title = title;
		this.author = author;
		this.ID = ID;
		this.totalNumber = totalNumber;
		this.availableNumber = totalNumber;
	}
	
	/**
	 * returns the id, title and name of the author
	 */
	public String toString() {
		return this.ID + " : "+ this.title + " : " + this.author;
	}
	
//	/**
//	 * Set the total number of that book
//	 * @param quantity is the total number of that book
//	 */
//	public void setTotalNumber(int quantity) {
//		this.totalNumber = quantity;
//	}
	
	/**
	 * Return total number of the book
	 * @return
	 */
	public int getTotalNumber() {
		return this.totalNumber;
	}
//	
//	/**
//	 * Determine the available number of the book before anybody borrow, so the available number is equal with total number
//	 */
//	public void setAvailableNumberBeforeBorrow() {
//		this.availableNumber = this.totalNumber;
//	}
	
	/**
	 * Return the available number of the book
	 * @return the number of the book that are available
	 */
	public int getAvailableNumber() {
		return this.availableNumber;
	}
	
    /**
     * Check whether the book is available or not
     * @return true if it is available, otherwise, returns false
     */
	public boolean checkAvailability() {
		if (this.availableNumber> 0){
			return true;
		}else {
			return false;
		}
	}
}
