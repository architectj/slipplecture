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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:infrastructure.xml")
public class SpringJdbcUserDaoTest {
	@Autowired
	private DataSource dataSource;
	
	private SpringJdbcUserDao dut;
	
	@Before
	public void setUp() throws Exception {
		dut = new SpringJdbcUserDao();
		dut.setDataSource(dataSource);
		DatabasePopulatorUtils.execute(databasePopulator(), dataSource);
	}
	
	private static DatabasePopulator databasePopulator() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("user.sql"));
		return populator;
	}

	@Test
	public void crud() throws Exception {
		User expected = new User("userId", "password", "name", "javajigi@slipp.net", true);
		dut.remove(expected.getUserId());

		dut.create(expected);
		
		User actual = dut.findUser(expected.getUserId());
		assertThat(actual, is(expected));
		
		expected.setName("name2");
		expected.setEmail("java@sk.com");
		dut.update(expected);
		actual = dut.findUser(expected.getUserId());
		assertThat(actual, is(expected));
	}

}
