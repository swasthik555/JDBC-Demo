package Database.JDBCDemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Demo {

	public static void main(String[] args)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "qafox";
		String serverTimeZone = "?serverTimezone=UTC";

		String dbURL = url + dbName + serverTimeZone;
		String username = "root";
		String password = "root";

		// Create an object for mySL driver class;
		String driver = "com.mysql.cj.jdbc.Driver";
		Class.forName(driver).newInstance();

		// Connect to the qafox database in MySQL DBMS.
		Connection connection = DriverManager.getConnection(dbURL, username, password);

		if (!connection.isClosed()) {

			System.out.println("Connected to qafox database");

			// Execute SQL statement on the qafox database using which we can retrieve thedata that is stored in the database.
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("Select * from employee");

			while (resultSet.next()) {

				System.out.println(resultSet.getString("empname") + "--" + resultSet.getString("location") + "--"+ resultSet.getString("Experience"));

			}

			System.out.println("--------------------------------------------");

			// Execute SQL statements on the qafox database using which we can retrieve the filtered data
			PreparedStatement preparedStatement = connection.prepareStatement("Select * from Employee where EmpName=? and Experience=?");
			preparedStatement.setString(1,"SHOBHA");
			preparedStatement.setInt(2,13);

			ResultSet resultSet2 = preparedStatement.executeQuery();
			
			while (resultSet2.next()) {

				System.out.println(resultSet2.getString("EmpName") + "--" + resultSet2.getString("Location") + "--"+ resultSet2.getInt("Experience"));
				
			}

		}
		
		connection.close();
		if(connection.isClosed()) {
			System.out.println("Connection to the qafox database is closed");
		}

	}

}
