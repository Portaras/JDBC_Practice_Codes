package in.ars.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcStatementUpdateApp {

	public static void main(String[] args) throws SQLException {
		Connection connection = null;
		Statement statement = null;

		String url = "jdbc:mysql://localhost:3306/jdbctraining";
		String user = "root";
		String password = "ARASUars@2788";
		
		connection = DriverManager.getConnection(url, user, password);

		if (connection != null) {
			statement = connection.createStatement();
		}
		String sqlUpdateQuery = "UPDATE student SET sname='Ars' WHERE sid = 1";
		if (statement != null) {
			int rowAffected = statement.executeUpdate(sqlUpdateQuery);
			if (rowAffected == 0) {
				System.out.println("No Data found");
			} else {
				System.out.println("Updated successfully");

			}
			// Closing the resources
			if (connection != null && statement != null) {
				statement.close();
				connection.close();
			}
		}
	}
}
