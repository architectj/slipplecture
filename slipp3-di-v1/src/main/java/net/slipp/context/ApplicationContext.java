package net.slipp.context;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import net.slipp.jdbc.DataSourceManager;
import net.slipp.user.JdbcUserDao;
import net.slipp.user.UserService;

public class ApplicationContext {
    private Map<String, Object> beans = new HashMap<String, Object>();
    
    public void init() {
        DataSource dataSource = DataSourceManager.getDataSource();
        JdbcUserDao userDao = new JdbcUserDao();
        userDao.setDataSource(dataSource);
        UserService userService = new UserService();
        userService.setUserDao(userDao);
        
        beans.put("userService", userService);
    }
    
    public Object getBean(String beanId) {
        return beans.get(beanId);
    }
}
