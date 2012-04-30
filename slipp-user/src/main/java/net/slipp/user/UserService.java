package net.slipp.user;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

@Service
@Transactional
public class UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Resource (name="userRepository")
	private UserRepository userRepository;
	
	public UserService() {
	}

	public void create(User user) throws SQLException, ExistedUserException, ExceedAdminUserException {
		if (findUser(user.getUserId()) != null) {
			throw new ExistedUserException(user.getUserId() + "는 이미 존재하는 아이디입니다.");
		}
		
		if (userRepository.countAdminUser() >= 3) {
			throw new ExceedAdminUserException();
		}

		userRepository.save(user);
	}

	public void update(User user) throws SQLException {
		userRepository.save(user);
	}

	public void remove(String userId) throws SQLException {
		userRepository.delete(userId);
	}

	public User findUser(String userId) throws SQLException {
		return userRepository.findOne(userId);
	}

	public List<User> findUserList() throws SQLException {
		return Lists.newArrayList(userRepository.findAll());
	}

	public boolean login(String userId, String password) throws SQLException, PasswordMismatchException {
		User user = findUser(userId);
		logger.debug("findUser : {}", user);
		if (!user.isMatchPassword(password)) {
			throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
		}
		return true;
	}
}
