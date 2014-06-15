package persistenceLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//@author  :: Ahmed
//creation :: 26/5/2014
//Modified :: AbdelGawad
//Modified Date :: 28/5/2014
/*
 MY SQLDataSources Class is Responsible for connection between SQL and Java 
 */

public class MySQLDataSource {

	private String databaseName;
	private String username;
	private String password;
	private Connection connnection;
	private String url = "jdbc:mysql://localhost:3306/"; // local host
	private String driver = "com.mysql.jdbc.Driver"; // Driver

	// constructor set up connection
	public MySQLDataSource(String databaseName, String username, String password)
			throws exception.SQLException {
		this.databaseName = databaseName;
		this.username = username;
		this.password = password;
		try {
			Class.forName(this.driver); // Define driver to work
			this.connnection = DriverManager.getConnection(this.url
					+ this.databaseName, this.username, this.password);
		} catch (ClassNotFoundException e) {
			throw new exception.SQLException("Driver not found");
		} catch (SQLException e) {
			throw new exception.SQLException("Connection failed");
		}
	}

	// execute all queries except select queries
	public void excuteUpdate(String sql) throws exception.SQLException {
		try {
			Statement query = connnection.createStatement();
			query.executeUpdate(sql);
		} catch (SQLException e) {
			throw new exception.SQLException("Query execution failed");
		}
	}

	// execute select queries
	public ResultSet executeQuery(String sql) throws exception.SQLException {
		ResultSet result = null;
		try {
			Statement query = connnection.createStatement();
			result = query.executeQuery(sql);
		} catch (SQLException e) {
			throw new exception.SQLException("Query execution failed");
		}
		return result;
	}

	public void closeConnection() throws exception.SQLException {
		try {
			this.connnection.close();
		} catch (SQLException e) {
			throw new exception.SQLException("Clossing connection failed");
		}
	}

	// setters & getters
	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

}
