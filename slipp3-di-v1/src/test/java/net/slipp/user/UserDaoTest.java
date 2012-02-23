package net.slipp.user;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class UserDaoTest {
	@Test
	public void crud() throws Exception {
	    UserDao dut = new JdbcUserDao();
		User expected = new User("userId", "password", "name", "javajigi@slipp.net");
		dut.remove(expected.getUserId());

		dut.create(expected);
		
		User actual = dut.findUser(expected.getUserId());
		assertThat(actual, is(expected));
	}
}
