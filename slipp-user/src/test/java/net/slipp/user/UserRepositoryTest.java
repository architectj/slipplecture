package net.slipp.user;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.google.common.collect.Lists;

@ContextConfiguration("classpath:user.xml")
public class UserRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	private UserRepository dut;
	
	@Test
	@Rollback(false)
	public void crud() {
		User expected = new User("userId", "password", "name", "javajigi@slipp.net", true);
		dut.save(expected);
		
		User actual = dut.findOne(expected.getUserId());
		assertThat(actual, is(expected));
		
		expected.setName("name3");
		expected.setEmail("javajigi@sk.com");
		dut.save(expected);
		actual = dut.findOne(expected.getUserId());
		assertThat(actual, is(expected));
		
		Iterable<User> users = dut.findAll();
		List<User> newUsers = Lists.newArrayList(users);
		assertThat(newUsers.size(), is(1));
	}

}
