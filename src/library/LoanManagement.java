package library;

import java.io.IOException;
import java.sql.SQLException;

public class LoanManagement {

	DataFetcher datafetcher;
	DataModifier dataModifier;

	public LoanManagement() throws SQLException, IOException {
		datafetcher = new DataFetcher();
		dataModifier = new DataModifier();
	}

	/**
	 * Check whether the book is available or not
	 * 
	 * @return true if it is available
	 * @throws SQLException
	 */
	public boolean checkAvailability(Book book) throws SQLException {
		datafetcher.fetchAvailableNumCopies(book);
		return (book.availableNumCopies > 0);
	}
	
	/**
	 * Check if the copy of the book is available or not
	 * @param copyID
	 * @return true if the copy is available
	 * @throws SQLException
	 */
	public boolean checkAvailibitlyOfCopy(int copyID) throws SQLException {
		String status = datafetcher.fetchCopyStatus(copyID);
		return status.equals("Available");
	}

	/**
	 * Borrowing book process
	 * @param copyID of the book to borrow
	 * @param studentID of student who want to borrow
	 * @throws Exception if borrow eligibilities were not meet
	 */
	public void borrowProcess(int copyID, String userID) throws Exception {
		Book book = datafetcher.fetchBook(copyID);
		User user = datafetcher.fetchUser(userID);
		datafetcher.fetchUserListBooks(user);

		//check borrow eligibility
		borrowEligibility(user, book, copyID);

		//if eligibility passed, modify instances data
		book.availableNumCopies -= 1;
		user.listBooks.addFirst(book);
		
		//update database
		dataModifier.updateDBForBorrow(copyID, user);

		user.printListBooks();
		ConnectToDatabase.closeConnection();
	}

	/**
	 * Check borrowing conditions 
	 * 
	 * @param book to borrow
	 * @throws Exception if the student already borrowed this book, if student met
	 *                   the maximum number of books that she/he can borrow, if the
	 *                   book is not available (book.availableNumber =0)
	 */
	public void borrowEligibility(User user, Book book, int copyID) throws Exception {
		// check the book is in list of student's book
		if (user.listBooks.contains(book))
			throw new Exception("Student already borrowed this book"); // throw exception and exit the program ///needs
		// update
		// check student meet the maximum number of books
		if (user.metMax())
			throw new Exception("Already achieved the maximum number of books that the student can borrow");

		if (!checkAvailability(book))
			throw new Exception("This book is not available");

		if (!checkAvailibitlyOfCopy(copyID))
			throw new Exception("This copy of the book is not available");
	}

	/**
	 * Return a book to the library and update database
	 * 
	 * @param CopyID of the book to return
	 * @throws Exception if the book does not exist in the list of student's book
	 */
	public void returnBook(int copyID) throws Exception {
		String userID = datafetcher.fetchUserIDWhoBorrowedBook(copyID);
		if (userID==null)
			throw new Exception("This copy of the book was not borrowed!");
		User user = datafetcher.fetchUser(userID);

		datafetcher.fetchUserListBooks(user);
		Book book = datafetcher.fetchBook(copyID);

		if (!user.listBooks.contains(book))
			throw new Exception("The book does not exist in the list of your books.");

		// remove the book from listBooks
		user.listBooks.remove(book);
		dataModifier.updateDBForReturn(copyID);
		System.out.println("Book return done!");
		user.printListBooks();

		ConnectToDatabase.closeConnection();

	}
}