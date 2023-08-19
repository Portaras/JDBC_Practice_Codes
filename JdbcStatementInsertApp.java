package in.ars.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcStatementInsertApp {

	public static void main(String[] args) throws SQLException {
		Connection connection = null;
		Statement statement = null;

		// Load and register the driver class from DB vendor package

		String url = "jdbc:mysql://localhost:3306/jdbctraining";
		String user = "root";
		String password = "ARASUars@2788";

		connection = DriverManager.getConnection(url, user, password);
		if (connection != null)
			statement = connection.createStatement();
		String sqlInsertQuery = "INSERT INTO student (sid,sname,sage,saddr) VALUES (1,'Arasu',36,'Chennai')";
		if (statement != null) {
			int rowsAffected = statement.executeUpdate(sqlInsertQuery);
			if (rowsAffected == 0) {
				System.out.println("Insertion Failed");
			} else {
				System.out.println("Data Interted successfully");
			}
		}
		if (statement != null && connection != null) {
			statement.close();
			connection.close();
		}
	}
}
