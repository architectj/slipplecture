package net.slipp.user;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration("classpath:user.xml")
public class JpaUserDaoTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
	private JpaUserDao dut;
    
	@Test
	@Rollback(false)
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
		
		assertThat(dut.findUserList().size(), is(1));
		assertThat(dut.countAdminUser(), is(1));
	}
	
	@Test
    public void findUser() throws Exception {
        User result = dut.findUser("userId");
        assertThat(result, is(notNullValue()));
    }
}
