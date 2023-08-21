package in.ars.main;

import java.sql.Connection;

import java.sql.SQLException;
import java.sql.Statement;

public class JdbcStatementUpdateAppUsingJdbcUtil {
		
	public static void main(String[] args) throws SQLException {
		Connection connection=null;
		Statement statement=null;
		
		// taking connection from JDBC utill app manually created
		connection = JdbcUtilCommonCode.getConnectionForDB();
		
		if (connection != null) {
			statement = connection.createStatement();
		}
		String sqlUpdateQuery = "UPDATE student SET sname='Brs' WHERE sid = 3";
		if (statement != null) {
			int rowAffected = statement.executeUpdate(sqlUpdateQuery);
			if (rowAffected == 0) {
				System.out.println("No Data found");
			} else {
				System.out.println("Updated successfully");

			}
			// Closing the resources
			JdbcUtilCommonCode.cleanupResources(connection, statement, null);	
		}
	}
}
