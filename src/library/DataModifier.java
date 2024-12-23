package library;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataModifier {
	private static Connection CONNECTION;

	public DataModifier() throws SQLException, IOException {
			CONNECTION = ConnectToDatabase.getConnection();
	}
	
	/**
	 * Update SQL database when a student or a faculty member borrows a book
	 * @param copyID of the copy of the book to borrow
	 * @param student who wants to borrow
	 * @throws SQLException
	 */
	public void updateDBForBorrow(int copyID, User user) throws SQLException {
		PreparedStatement preStm;
		String userID = user.userID;
		String column = "student_id";
		
		if (!user.checkStudent()) 
			column = "faculty_id";
		
		String query = "UPDATE catalog.copies SET status = 'Checked Out', " + column + " = ? WHERE copy_id =?;";
		preStm = CONNECTION.prepareStatement(query);
		preStm.setString(1, userID);
		preStm.setInt(2, copyID);

		preStm.executeUpdate();
	}

	/**
	 * Update SQL database when a book is returned
	 * @param copyID of the copy to return
	 * @throws SQLException
	 */
	public void updateDBForReturn(int copyID) throws SQLException {
		PreparedStatement preStm;
		preStm = CONNECTION.prepareStatement(
				"UPDATE catalog.copies SET status = 'Available', student_id = NUll, faculty_id= Null WHERE copy_id =?;");
		preStm.setInt(1, copyID);
		preStm.executeUpdate();
	}

}
