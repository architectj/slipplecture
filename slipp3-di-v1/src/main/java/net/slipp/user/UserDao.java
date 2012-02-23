package net.slipp.user;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    void create(User user) throws SQLException;

    void update(User user) throws SQLException;

    void remove(String userId) throws SQLException;

    User findUser(String userId) throws SQLException;

    List<User> findUserList() throws SQLException;
}