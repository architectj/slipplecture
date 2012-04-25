package net.slipp.user;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
	private UserService userService;
	
	@Mock
	UserDao userDao;

	@Before
	public void setUp() throws Exception {
		userService = new UserService();
		userService.setUserDao(userDao);
	}
	
	@Test
	public void create_normal() throws Exception {
		when(userDao.findUser("userId")).thenReturn(null);
		when(userDao.countAdminUser()).thenReturn(1);
		userService.create(createUser("userId"));
		verify(userDao).create(createUser("userId"));
	}

	private User createUser(String userId) {
		User user = new User(userId, "pass", "재성", 
				"javajigi@sk.com", true);
		return user;
	}
	
	@Test(expected=ExistedUserException.class)
	public void create_existedUser() throws Exception {
		when(userDao.findUser("sanjigi")).thenReturn(createUser("sanjigi"));
		userService.create(createUser("sanjigi"));
	}
	
	@Test(expected=ExceedAdminUserException.class)
	public void create_exceedAdminUser() throws Exception {
		when(userDao.findUser("sanjigi")).thenReturn(null);
		when(userDao.countAdminUser()).thenReturn(3);
		userService.create(createUser("sanjigi"));
	}

}
