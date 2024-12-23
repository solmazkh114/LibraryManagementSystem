package library;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectToDatabase {
	private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=Library;encrypt=true;trustServerCertificate=true";
	private static Connection CONNECTION;

	private static Properties loadProperties() throws IOException {
		Properties props = new Properties();
		FileInputStream fis = new FileInputStream("config.properties");
		props.load(fis);
		fis.close();
		return props;
	}
	// Get the connection instance
    public static Connection getConnection() throws SQLException, IOException {
        if (CONNECTION == null || CONNECTION.isClosed()) {
            Properties props = loadProperties();
   
            String user = props.getProperty("db.user");
            String password = props.getProperty("db.password");

            CONNECTION = DriverManager.getConnection(DB_URL, user, password);
            System.out.println("Connected to the database.");
        }
        return CONNECTION;
    }

	// Close the connection
	public static void closeConnection() {
		try {
			if (CONNECTION != null && CONNECTION.isClosed()) {
				CONNECTION.close();
				//System.out.println("Connection closed.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
