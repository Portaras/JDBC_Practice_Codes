package in.ars.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class JdbcStatementWIthExecuteMethodDynamicQuery {

	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		Scanner scanner = null;
		String sqlQueryDynamic = null;
		Integer userOperationSelection = null;
		Boolean executeResult = null;
		Integer sid = null;
		Integer sage = null;
		String sname = null;
		String saddr = null;

		try {
			// Loading and registering the Driver in JRE (can skip this after JDBC 4.x)
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://localhost:3306/jdbctraining";
			String user = "root";
			String password = "ARASUars@2788";

			connection = DriverManager.getConnection(url, user, password);

			if (connection != null) {
				statement = connection.createStatement();
			}

			// Asking user to pick the operation to proceed
			scanner = new Scanner(System.in);
			if (scanner != null) {
				System.out.print(
						"Please select the numbers to perform relevent operation \n 1.SELECT\n 2.INSERT\n 3.UPDATE\n 4.DELETE:\n Enter here:");
				userOperationSelection = scanner.nextInt();
			}

			switch (userOperationSelection) {

			// case 1 is for select operation
			case 1:
				System.out.print("Please enter the ID of the record to view : ");
				sid = scanner.nextInt();
				sqlQueryDynamic = String.format("SELECT sid, sname, sage, saddr FROM student WHERE sid = %d", sid);
				break;

			// case 2 is for Insert operation
			case 2:
				System.out.println("Please enter the ID to Insert : ");
				sid = scanner.nextInt();
				System.out.println("Please enter the Name to Insert : ");
				sname = scanner.next();
				System.out.println("Please enter the Age to Insert : ");
				sage = scanner.nextInt();
				System.out.println("Please enter the Address to Insert : ");
				saddr = scanner.next();
				sqlQueryDynamic = String.format(
						"INSERT INTO student (sid, sname, sage, saddr) VALUES (%d,'%s',%d,'%s')", sid, sname, sage,
						saddr);
				break;
			// case 3 is for Update operation
			case 3:
				System.out.println("Please enter the ID to update : ");
				sid = scanner.nextInt();
				System.out.println("Please enter the Name to Insert : ");
				sname = scanner.next();
				System.out.println("Please enter the Age to Insert : ");
				sage = scanner.nextInt();
				System.out.println("Please enter the Address to Insert : ");
				saddr = scanner.next();
				sqlQueryDynamic = String.format(
						"UPDATE student SET sname = '%s', sage = %d, saddr = '%s' WHERE sid = %d", sname, sage, saddr,
						sid);
				break;
			// case 4 is for Delete operation
			case 4:
				System.out.println("Please enter the ID to Delete : ");
				sid = scanner.nextInt();
				sqlQueryDynamic = String.format("DELETE FROM student WHERE sid = %d", sid);
				break;
			}

			if (statement != null) {
				executeResult = statement.execute(sqlQueryDynamic);
			}
			// SELECT OPERATION
			if (executeResult) {
				resultSet = statement.getResultSet();
				if (resultSet.next()) {
					sid = resultSet.getInt(1);
					sname = resultSet.getString(2);
					sage = resultSet.getInt(3);
					saddr = resultSet.getString(4);
					System.out.println(
							"SID\t\tSNAME\t\tSAGE\t\tSADDR\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
					System.out.println(sid + "\t\t" + sname + "\t\t" + sage + "\t\t" + saddr);
				} else {
					System.out.println("Record not found");
				}
			}
			// NON Select operation
			else {
				int updateCount = statement.getUpdateCount();
				System.out.println("No of rows affected: " + updateCount);
			}
			// Handling the Exceptions
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("Session closed abruptly : ID already available in Database!");
		} catch (InputMismatchException e) {
			System.out.println("Session closed abruptly : Please enter the correct value!");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Closing the resources
			if (statement != null && connection != null) {
				try {
					if (resultSet != null)
						resultSet.close();
					statement.close();
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}

		}
	}

}
