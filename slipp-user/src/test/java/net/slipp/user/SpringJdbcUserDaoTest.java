package net.slipp.user;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration("classpath:infrastructure.xml")
public class SpringJdbcUserDaoTest extends AbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	private DataSource dataSource;
	
	private SpringJdbcUserDao dut;
	
	@Before
	public void setUp() throws Exception {
		dut = new SpringJdbcUserDao();
		dut.setDataSource(dataSource);
		dut.afterPropertiesSet();
		
		deleteFromTables("USERS");
	}
	
	@Test
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
