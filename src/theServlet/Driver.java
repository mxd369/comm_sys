package theServlet;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

public class Driver {

	// get a connection
	public static void main(String[] args) throws SQLException {
		/*
		String url = "jdbc:mysql://localhost:3306/demo";
		String user = "root";
		String password = "BKnFOSEL";
		*/
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			// 1. Establishing a connection.
			// Connection con = DriverManager.getConnection(url, user, password);
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "BKnFOSEL");

			// 2. Create a statement.
			// Statement stmt = con.createStatement();
			
			// 2. Prepare statement
			myStmt = myConn.prepareStatement("select * from employees where salary > ? and department=?");

			// 3. Execute the query.
			/*
			String sql = "INSERT INTO employees "
						+ "(last_name, first_name, email)"
						+ " values ('Brown', 'David', 'david.brown@foo.com')";
			*/
			
			//stmt.executeUpdate(sql);//this will execute the code against the database
			/*
			String sql = "delete from employees where last_name='Brown'";
			int rowsAffected = stmt.executeUpdate(sql);
			
			ResultSet rs = stmt.executeQuery("select * from employees");
			*/
			// 3. Set the parameters
			myStmt.setDouble(1,  80000);
			myStmt.setString(2, "Legal");
			
			// 4. Process the ResultSet object.
			/*
			while (rs.next()) {
				System.out.println(rs.getString("last_name") + ", " + rs.getString("first_name"));
			}

			System.out.println("Task Complete.");
			*/
			myRs = myStmt.executeQuery();

			// 5. Display the result set
			display(myRs);
			
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		// 5. Close the connection.
		finally {
			if (myRs != null) {
				myRs.close();
			}
			if (myStmt != null) {
				myStmt.close();
			}
			if (myConn != null) {
				myConn.close();
			}
		}
	}

	private static void display(ResultSet myRs) throws SQLException {
		// TODO Auto-generated method stub
		while (myRs.next()) {
			String lastName = myRs.getString("last_name");
			String firstName = myRs.getString("first_name");
			double salary = myRs.getDouble("salary");
			String department = myRs.getString("department");
			System.out.printf("%s, %s, %.2f, %s\n", lastName, firstName, salary, department);
		}
	}
	
}