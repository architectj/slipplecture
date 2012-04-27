package net.slipp.user;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Resource (name="springJdbcUserDao")
	private UserDao userDao;
	
	public UserService() {
	}

	public void create(User user) throws SQLException, ExistedUserException, ExceedAdminUserException {
		if (findUser(user.getUserId()) != null) {
			throw new ExistedUserException(user.getUserId() + "는 이미 존재하는 아이디입니다.");
		}
		
		if (getUserDAO().countAdminUser() >= 3) {
			throw new ExceedAdminUserException();
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
		logger.debug("findUser : {}", user);
		if (!user.isMatchPassword(password)) {
			throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
		}
		return true;
	}

	private UserDao getUserDAO() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
