package in.ars.main;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtilCommonCodeWithPropertyFile {
	// Loading and registering driver in case of using this code before JDBC 4.X
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// Making constructor as private to avoid creating object for the utility code
	private JdbcUtilCommonCodeWithPropertyFile() {

	}

	// Getting the connection
	public static Connection getConnectionForDB() throws SQLException {
		FileInputStream fis = null;
		Connection connection = null;
		try {
			fis = new FileInputStream("/Volumes/WorkSpace/EclipseWorkspace_JRE/JDBCapi/01_JDBC_Statement/application.properties");

			System.out.println(fis);
			Properties properties = new Properties();

			properties.load(fis);
			connection = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("user"),
					properties.getProperty("password"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return connection;

	}

	// Closing the resources
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
