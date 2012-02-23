package net.slipp.jdbc;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

public class DataSourceManager {
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
