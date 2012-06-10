package net.slipp.user2;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import net.slipp.user.User;

import org.junit.Before;
import org.junit.Test;

public class JdbcUserDaoTest {
	private JdbcUserDao dut;
	
	@Before
	public void setup() {
		dut = new JdbcUserDao();
	}

	@Test
	public void crud() throws Exception {
		User expected = new User("javajigi", "password", "name", "javajigi@slipp.net", true);
		dut.remove(expected.getUserId());

		dut.create(expected);
		
		User actual = dut.findUser(expected.getUserId());
		assertThat(actual, is(expected));
		
		expected.update("name2", "sanjigi@slipp.net");
		dut.update(expected);
		
		actual = dut.findUser(expected.getUserId());
		assertThat(actual, is(expected));
		
		List<User> users = dut.findUsers();
		assertThat(users.size(), is(1));
	}
}
