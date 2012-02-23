package net.slipp.context;

import javax.sql.DataSource;

import net.slipp.jdbc.DataSourceManager;
import net.slipp.user.JdbcUserDao;
import net.slipp.user.UserService;

public class SlippFactory {
    public UserService getUserService() {
        DataSource dataSource = DataSourceManager.getDataSource();
        JdbcUserDao userDao = new JdbcUserDao();
        userDao.setDataSource(dataSource);
        UserService userService = new UserService();
        userService.setUserDao(userDao);
        return userService;
    }
}
