package net.slipp.user;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

public class UserServiceTest {
	private UserService userService;

	@Before
	public void setUp() throws Exception {
		userService = new UserService();
		UserDao userDao = new MockUserDao();
		userService.setUserDao(userDao);
	}
	
	@Test
	public void create_normal() throws Exception {
		userService.create(createUser("userId"));
	}

	private User createUser(String userId) {
		User user = new User(userId, "pass", "재성", 
				"javajigi@sk.com", true);
		return user;
	}
	
	@Test(expected=ExistedUserException.class)
	public void create_existedUser() throws Exception {
		userService.create(createUser("sanjigi"));
		userService.create(createUser("sanjigi"));
	}
	
	@Test(expected=ExceedAdminUserException.class)
	public void create_exceedAdminUser() throws Exception {
		userService.create(createUser("userId1"));
		userService.create(createUser("userId2"));
		userService.create(createUser("userId3"));
		userService.create(createUser("userId4"));
	}

}
