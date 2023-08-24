package in.pws.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtilCommonCode {
	// Loading and registering driver in case of using this code before JDBC 4.X
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// Making constructor as private to avoid creating object for the utility code
	private JdbcUtilCommonCode() {

	}
	// Getting the connection
	public static Connection getConnectionForDB() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/jdbctraining";
		String user = "root";
		String password = "ARASUars@2788";
		return DriverManager.getConnection(url, user, password);
	}
	//Closing the resources
	public static void cleanupResources(Connection connection, Statement statement, ResultSet resultSet)
			throws SQLException {
		if (connection != null)
			connection.close();
		if (statement != null)
			statement.close();
		if (resultSet != null)
			resultSet.close();
	}
}
