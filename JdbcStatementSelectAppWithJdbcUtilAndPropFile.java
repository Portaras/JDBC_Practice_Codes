package in.ars.main;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcStatementSelectAppWithJdbcUtilAndPropFile {

	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		// Load and register the driver class from DB vendor package

		try {

			connection = JdbcUtilCommonCodeWithPropertyFile.getConnectionForDB();


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
		} catch ( SQLException se) {
			se.printStackTrace();
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
