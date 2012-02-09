package net.slipp.support.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

public class ConnectionManager {
    public static Connection getConnection() throws SQLException {
//        String url = "jdbc:mysql://localhost:3306/lecture";
//        String driver = "com.mysql.jdbc.Driver";
//        String username = "userid";
//        String password = "password";
//        try {
//            Class.forName(driver).newInstance();
//            return DriverManager.getConnection(url, username, password);
//        } catch (Exception e) {
//            logger.error(e.getMessage());
//            return null;
//        }
        return getDataSource().getConnection();
    }

    public static DataSource getDataSource() {
        String url = "jdbc:mysql://localhost:3306/lecture";
        String driver = "com.mysql.jdbc.Driver";
        String username = "userid";
        String password = "password";
        
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
}
