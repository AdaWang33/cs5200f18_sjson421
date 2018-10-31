package edu.northeastern.cs5200;

import java.sql.DriverManager;
import java.sql.SQLException;
 
public class Connection {
	private static Connection instance = null;
	private Connection() {};
	public static Connection getInstance() {
		if(instance == null) {
			instance = new Connection();
		}
		return instance;
	}
	private java.sql.Connection connection = null;
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://cs5200-fall2018-son.c1i9dtwavyc3.us-east-1.rds.amazonaws.com/cs5200_fall2018_son?useSSL=false";
	private static final String USER = "root";
	private static final String PASSWORD = "rootroot";

	public java.sql.Connection getConnection() {
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	public void closeConnection() {
		if(connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}