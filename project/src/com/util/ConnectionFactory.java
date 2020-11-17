package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	private static final String username = System.getenv("dbusername");
	private static final String password = System.getenv("dbpassword");
	private static final String url = System.getenv("dburl");
	private static Connection connection;
	
	public static synchronized Connection getConnection() throws SQLException {
		
		if(connection == null) {
			try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println("Could not register driver");
				e.printStackTrace();
			}
			connection = DriverManager.getConnection(url, username, password);
		}
		
		if(connection.isClosed()) {
			System.out.println("Opening new connection");
			connection = DriverManager.getConnection(url, username, password);
		}
		
		return connection;
		
	}
	
}
