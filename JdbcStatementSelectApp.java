package in.ars.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcStatementSelectApp {

	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		// Load and register the driver class from DB vendor package

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://localhost:3306/jdbctraining";
			String user = "root";
			String password = "ARASUars@2788";
			// Driver object created and registered during class loading (static block)
			connection = DriverManager.getConnection(url, user, password);

			if (connection != null)
				statement = connection.createStatement();

			String SqlQuery = "SELECT sid,sname,sage,saddr FROM student";
			if (statement != null)
				resultSet = statement.executeQuery(SqlQuery);

			System.out.println("SID\t\tSNAME\t\tSAGE\t\tSADDR\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			if (resultSet != null) {
				while (resultSet.next()) {
					int sid = resultSet.getInt("sid");
					String sname = resultSet.getString(2);
					int sage = resultSet.getInt(3);
					String saddr = resultSet.getString("saddr");
					System.out.println(sid + "\t\t" + sname + "\t\t" + sage + "\t\t" + saddr);
				}
			}
			// Handling exception
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// closing the resources
				if (resultSet != null && statement != null && connection != null) {
					resultSet.close();
					statement.close();
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
