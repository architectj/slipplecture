package net.slipp.user;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockUserDao implements UserDao {
	private Map<String, User> users = new HashMap<String, User>();

	@Override
	public void create(User user) throws SQLException {
		users.put(user.getUserId(), user);
	}

	@Override
	public void update(User user) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(String userId) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public User findUser(String userId) throws SQLException {
		return users.get(userId);
	}

	@Override
	public List<User> findUserList() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countAdminUser() throws SQLException {
		return users.size();
	}

}
