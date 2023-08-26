package in.pws.codes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.InputMismatchException;
import java.util.Scanner;

import in.pws.util.JdbcUtilCommonCode;

public class JdbcPreparesStatementAppCRUDOperation {
	private static Integer sid;
	private static Integer sage;
	private static String sname;
	private static String saddr;
	private static PreparedStatement pstmt;
	private Scanner scanner = null;
	private String sqlQueryDynamic = null;
	private static Integer userOperationSelection;

	// Getting user input
	public String gettingUserInput() throws SQLException {

		// Asking user to pick the operation to proceed

		scanner = new Scanner(System.in);
		if (scanner != null) {
			System.out.print(
					"Please select the numbers to perform relevent operation \n 1.SELECT\n 2.INSERT\n 3.UPDATE\n 4.DELETE\n 5.Exit\nEnter here: ");
			userOperationSelection = scanner.nextInt();
		}
		switch (userOperationSelection) {

		// case 1 is for select operation
		case 1:
			System.out.print("Please enter the ID of the record to view : ");
			sid = scanner.nextInt();
			sqlQueryDynamic = "SELECT sid, sname, sage, saddr FROM student WHERE sid = ?";
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
			sqlQueryDynamic = "INSERT INTO student (sid, sname, sage, saddr) VALUES (?,?,?,?)";
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
			sqlQueryDynamic = "UPDATE student SET sname = ?, sage = ?, saddr = ? WHERE sid = ?";
			break;
		// case 4 is for Delete operation
		case 4:
			System.out.println("Please enter the ID to Delete : ");
			sid = scanner.nextInt();
			sqlQueryDynamic = "DELETE FROM student WHERE sid = ?";
			break;
		default:
			System.out.println("Thanks for choosing our APP!");
			System.exit(0);

		}

		return sqlQueryDynamic;

	}

	// Main method
	public static void main(String[] args) {
		Connection connection = null;
		Boolean executeResult = null;
		ResultSet resultSet = null;

		try {
			connection = JdbcUtilCommonCode.getConnectionForDB();
			if (connection != null) {
				pstmt = connection.prepareStatement(new JdbcPreparesStatementAppCRUDOperation().gettingUserInput());
				System.out.println(pstmt);
			}
			if (pstmt != null && userOperationSelection == 1) {
				pstmt.setInt(1, sid);
				System.out.println("inside" + pstmt);
				executeResult = pstmt.execute();
				System.out.println("execute : " + executeResult);
				if (executeResult == true) {

					resultSet = pstmt.executeQuery();

					if (resultSet.next()) {
						System.out.println("SID\t\tSNAME\t\tSAGE\t\tSADDR\n+++++++++++++++++++++++++++++++");
						int stid = resultSet.getInt(1);
						String stname = resultSet.getString(2);
						int stage = resultSet.getInt(3);
						String staddr = resultSet.getString(4);
						System.out.println(stid + "\t\t" + stname + "\t\t" + stage + "\t\t" + staddr);
					} else {
						System.out.println("Record not available for the ID : " + sid);
					}
				}
			} else {
				if (userOperationSelection == 2) {
					pstmt.setInt(1, sid);
					pstmt.setString(2, sname);
					pstmt.setInt(3, sage);
					pstmt.setString(4, saddr);
					System.out.println(pstmt);
					nonSelectOperation();
				} else if (userOperationSelection == 3) {
					pstmt.setInt(4, sid);
					pstmt.setString(1, sname);
					pstmt.setInt(2, sage);
					pstmt.setString(3, saddr);
					System.out.println(pstmt);
					nonSelectOperation();
				} else if (userOperationSelection == 4) {
					pstmt.setInt(1, sid);
					System.out.println(pstmt);
					nonSelectOperation();
				} else {
					System.out.println("Please enter the valid input!");
				}

			}
			// Handling the invalid input
		} catch (InputMismatchException ime)

		{
			System.out.println("Please enter the valid input");
		}
		// Handling the Duplicate entry
		catch (SQLIntegrityConstraintViolationException sse) {
			System.out.println("Duplicate ID found please enter the different ID");
		}

		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				JdbcUtilCommonCode.cleanupResources(connection, pstmt, resultSet);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static void nonSelectOperation() throws SQLException {
		int rowsAffected = pstmt.executeUpdate();

		if (rowsAffected == 0)
			System.out.println("No of rows affected : " + rowsAffected + ": No record found");
		else
			System.out.println("No of rows affected : " + rowsAffected);
	}

}
