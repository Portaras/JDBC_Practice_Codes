package in.ars.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcStatementDeleteApp {

	public static void main(String[] args) throws SQLException {

		Connection connection = null;
		Statement statement = null;

		// Class.forName is automatically generated after JDBC 4.x version
		String url = "jdbc:mysql://localhost:3306/jdbctraining";
		String user = "root";
		String psasword = "ARASUars@2788";

		// Another way of handling exception called Ducking
		connection = DriverManager.getConnection(url, user, psasword);
		if (connection != null)
			statement = connection.createStatement();
		String sqlDeleteQuery = "DELETE FROM student WHERE sid = 1 and sname = 'Arasu'";

		if (statement != null) {
			int rowAffected = statement.executeUpdate(sqlDeleteQuery);
			if (rowAffected == 0) {
				System.out.println("No Record Found");
			} else {
				System.out.println("No of rows deleted = " + rowAffected);
			}
		}
		// Closing the resources
		if (connection != null && statement != null) {
			statement.close();
			connection.close();
		}

	}

}
