package net.slipp.user;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration("classpath:user.xml")
public class JpaUserDaoTest extends AbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	private JpaUserDao dut;
	
	@Test
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public void crud() throws Exception {
		User expected = new User("userId", "password", "name", "javajigi@slipp.net", true);
		dut.create(expected);
		
		User actual = dut.findUser(expected.getUserId());
		assertThat(actual, is(expected));
		
		expected.setName("name3");
		expected.setEmail("javajigi@sk.com");
		dut.update(expected);
		actual = dut.findUser(expected.getUserId());
		assertThat(actual, is(expected));
	}
}
