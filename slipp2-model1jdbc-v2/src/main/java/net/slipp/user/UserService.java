package net.slipp.user;

import java.sql.SQLException;
import java.util.List;

import net.slipp.user.event.UserLoginEvent;
import net.slipp.user.event.UserLoginEventDao;

public class UserService {
	public UserService() {
	}

	public void create(User user) throws SQLException, ExistedUserException {
		if (findUser(user.getUserId()) != null) {
			throw new ExistedUserException(user.getUserId() + "는 이미 존재하는 아이디입니다.");
		}

		getUserDAO().create(user);
	}

	public void update(User user) throws SQLException {
		getUserDAO().update(user);
	}

	public void remove(String userId) throws SQLException {
		getUserDAO().remove(userId);
	}

	public User findUser(String userId) throws SQLException {
		return getUserDAO().findUser(userId);
	}

	public List<User> findUserList() throws SQLException {
		return getUserDAO().findUserList();
	}

	public boolean login(String userId, String password) throws SQLException, PasswordMismatchException {
		User user = findUser(userId);
		UserLoginEvent userLoginEvent;
		UserLoginEventDao eventDao = new UserLoginEventDao();
		if (!user.isMatchPassword(password)) {
		    userLoginEvent = new UserLoginEvent(userId, password, false);
		    eventDao.create(userLoginEvent);
			throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
		}
		userLoginEvent = new UserLoginEvent(userId, password, true);
        eventDao.create(userLoginEvent);
		
		return true;
	}

	private UserDao getUserDAO() {
		return new UserDao();
	}
}
