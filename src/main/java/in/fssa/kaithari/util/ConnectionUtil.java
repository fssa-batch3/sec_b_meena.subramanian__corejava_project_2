package in.fssa.kaithari.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ConnectionUtil {
	/**
	 * Establishes a database connection.
	 *
	 * This static method is responsible for creating a connection to the database.
	 * It uses the JDBC driver to establish a connection by providing the necessary
	 * URL, username, and password. The connection is then returned for use in
	 * various database operations. Note that the connection parameters (URL,
	 * username, and password) are currently hardcoded in this method. In a
	 * production environment, it's recommended to use a more secure approach, such
	 * as loading these parameters from environment variables or a configuration
	 * file.
	 *
	 * @return A Connection object representing the database connection.
	 * @throws RuntimeException If there is an issue with loading the JDBC driver or
	 *                          establishing the connection.
	 */
	public static Connection getConnection() {

		String url = System.getenv("DATABASE_HOSTNAME");
		String userName = System.getenv("DATABASE_USERNAME");
		String password = System.getenv("DATABASE_PASSWORD");

		
		

		Connection connection = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, userName, password);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return connection;
	}
	
	

	/**
	 * Closes a database connection and a prepared statement.
	 *
	 * This static method is responsible for closing both the provided database
	 * connection and prepared statement. It is important to properly close these
	 * resources to avoid potential memory leaks and database connection leaks. If
	 * either the prepared statement or the connection is not null, this method
	 * attempts to close them using their respective close methods. Any SQLException
	 * that occurs during the closing process is caught and printed to the standard
	 * error stream.
	 *
	 * @param connection The Connection object representing the database connection
	 *                   to be closed.
	 * @param ps         The PreparedStatement object to be closed.
	 */
	public static void close(Connection connection, PreparedStatement ps) {
		try {
			if (ps != null) {
				ps.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Closes a database connection, a prepared statement, and a result set.
	 *
	 * This static method is responsible for closing the provided database
	 * connection, prepared statement, and result set. It is crucial to close these
	 * resources properly to prevent memory leaks and connection leaks. If any of
	 * the resources (result set, prepared statement, or connection) are not null,
	 * this method attempts to close them using their respective close methods. Any
	 * SQLException that occurs during the closing process is caught and printed to
	 * the standard error stream.
	 *
	 * @param connection The Connection object representing the database connection
	 *                   to be closed.
	 * @param ps         The PreparedStatement object to be closed.
	 * @param rs         The ResultSet object to be closed.
	 */
	public static void close(Connection connection, PreparedStatement ps, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
