package library;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataFetcher {

	private static Connection CONNECTION;

	public DataFetcher() throws SQLException, IOException {
		CONNECTION = ConnectToDatabase.getConnection();
	}

	/**
	 * fetch user's first name and last name from the database using user's id
	 * 
	 * @param userID
	 * @return user object with the given ID
	 * @throws SQLException
	 */
	public User fetchUser(String userID) throws SQLException {
		PreparedStatement preStm;
		String query;

		// define query based on that the user is a student of a faculty member
		if (userID.startsWith("f"))
			query = "SELECT first_name, last_name FROM users.faculties WHERE faculty_id = ?;";
		else
			query = "SELECT first_name, last_name FROM users.students WHERE student_id = ?;";

		preStm = CONNECTION.prepareStatement(query);
		preStm.setString(1, userID);

		ResultSet result = preStm.executeQuery();
		if (result.next()) {
			String name = result.getString("first_name") + " " + result.getString("last_name");
			return new User(userID, name);
		} else
			throw new SQLException("No user found for copy_id: " + userID); // Throw an exception if no match
	}
	
	/**
	 * fetch title and author of the book by using the copyID
	 * @param copy_id
	 * @return book object
	 * @throws SQLException
	 */
	public Book fetchBook(int copy_id) throws SQLException {
		PreparedStatement preStm;
		preStm = CONNECTION.prepareStatement("SELECT b.book_id, b.title, b.author FROM catalog.books b\r\n"
				+ "INNER JOIN catalog.copies c\r\n" + "ON c.book_id = b.book_id\r\n" + "WHERE c.copy_id = ?;");
		preStm.setInt(1, copy_id);

		ResultSet result = preStm.executeQuery();
		if (result.next()) {
			String bookID = result.getString("book_id");
			String title = result.getString("title");
			String author = result.getString("author");
			return new Book(bookID, title, author);
		} else {
			throw new SQLException("No book found for copy_id: " + copy_id); // Throw an exception if no match
		}
	}
	
	/**
	 * fetch book list of user and update user.listBooks
	 * @param user to find his list of books
	 */
	public void fetchUserListBooks(User user) {
		ArrayList<Book> bookList = new ArrayList<Book>();
		PreparedStatement preStm;
		try {
			String query;
			//define query variable based on that the user is a student or a faculty member
			if (user.userID.startsWith("f-")) {
				query = "SELECT c.book_id, b.title, b.author from catalog.copies c "
						+ "INNER JOIN users.faculties f ON c.faculty_id = f.faculty_id INNER JOIN catalog.books b "
						+ "ON b.book_id = c.book_id WHERE f.faculty_id =?;";
			} else {
				query = "SELECT c.book_id, b.title, b.author from catalog.copies c "
						+ "INNER JOIN users.students s ON c.student_id = s.student_id INNER JOIN catalog.books b "
						+ "ON b.book_id = c.book_id WHERE s.student_id =?;";
			}
			preStm = CONNECTION.prepareStatement(query);
			String userID = user.userID;
			preStm.setString(1, userID);

			ResultSet result = preStm.executeQuery();

			while (result.next()) {
				String bookID = result.getString("book_id");
				String title = result.getString("title");
				String author = result.getString("author");
				Book book = new Book(bookID, title, author);
				bookList.add(book);
			}
			user.listBooks = bookList;
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * fetch total number of copies of a specific book object and update book.totalNumCopies
	 * @param book to fins its total number of copies
	 * @throws SQLException
	 */
	public void fetchTotalNumCopies(Book book) throws SQLException {
		PreparedStatement preStm;
		preStm = CONNECTION
				.prepareStatement("SELECT COUNT(copy_id) total_copies FROM catalog.copies WHERE book_id = ?;");
		String bookID = book.ID;
		preStm.setString(1, bookID);

		ResultSet result = preStm.executeQuery();
		book.totalNumCopies = result.getInt("total_copies");
	}

	public void fetchAvailableNumCopies(Book book) throws SQLException {
		PreparedStatement preStm;
		preStm = CONNECTION.prepareStatement(
				"SELECT COUNT(copy_id) available_copies from catalog.copies WHERE book_id = ? and status= 'Available';");
		String bookID = book.ID;
		preStm.setString(1, bookID);

		ResultSet result = preStm.executeQuery();
		while (result.next()) {
			book.availableNumCopies = result.getInt("available_copies");
		}
	}

	/**
	 * fetch user ID who borrowed a given copy id 
	 * @param copyID
	 * @return user ID
	 * @throws SQLException
	 */
	public String fetchUserIDWhoBorrowedBook(int copyID) throws SQLException {
		PreparedStatement preStm;
		String studentID = null;
		String facultyID = null;
		preStm = CONNECTION.prepareStatement("SELECT student_id, faculty_id FROM catalog.copies WHERE copy_id = ?;");
		preStm.setInt(1, copyID);

		ResultSet result = preStm.executeQuery();
		while (result.next()) {
			studentID = result.getString("student_id");
			facultyID = result.getString("faculty_id");
		}
		if (studentID != null)
			return studentID;
		else
			return facultyID;
	}

	/**
	 * fetch status of a book, whether it is 'Available' or 'Checked-out'
	 * @param copyID
	 * @return status of the book ('Available' or 'Checked-out')
	 * @throws SQLException
	 */
	public String fetchCopyStatus(int copyID) throws SQLException {
		PreparedStatement preStm;
		String status = null;
		preStm = CONNECTION.prepareStatement("SELECT status FROM catalog.copies WHERE copy_id = ? ;");
		preStm.setInt(1, copyID);

		ResultSet result = preStm.executeQuery();
		while (result.next()) {
			status = result.getString("status");
		}
		return status;
	}
}
