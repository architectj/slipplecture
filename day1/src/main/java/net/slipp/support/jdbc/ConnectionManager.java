package net.slipp.support.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionManager {
	private static Logger logger = LoggerFactory.getLogger(ConnectionManager.class);
	
	public static Connection getConnection() {
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "lecture";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "userid";
		String password = "password";
		try {
			Class.forName(driver).newInstance();
			return DriverManager.getConnection(url + dbName, userName, password);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
}
