package net.slipp.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import net.slipp.support.jdbc.AbstractPlanetDaoSupport;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("springJdbcUserDao")
public class SpringJdbcUserDao extends AbstractPlanetDaoSupport 
	implements UserDao {

	@Override
	public void create(User user) throws SQLException {
		String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?, ?)";
		getJdbcTemplate().update(sql, user.getUserId(), user.getPassword(),
				user.getName(), user.getEmail(), user.isAdmin());
	}

	@Override
	public void update(User user) throws SQLException {
		String sql = "UPDATE USERS SET name=?, email=? WHERE userid=?";
		getJdbcTemplate().update(sql, user.getName(), user.getEmail(),
				user.getUserId());
	}

	@Override
	public void remove(String userId) throws SQLException {
		String sql = "DELETE FROM USERS WHERE userid=?";
		getJdbcTemplate().update(sql, userId);
	}

	@Override
	public User findUser(String userId) throws SQLException {
		String sql = "SELECT userId, password, name, email, isAdmin "
				+ "FROM USERS WHERE userid=? ";

		RowMapper<User> rowMapper = new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new User(rs.getString("userId"),
						rs.getString("password"), rs.getString("name"),
						rs.getString("email"), rs.getBoolean("isAdmin"));
			}
		};

		return getJdbcTemplate().queryForObject(sql, rowMapper, userId);
	}

	@Override
	public List<User> findUserList() throws SQLException {
		String sql = "SELECT userId, password, name, email, isAdmin "
				+ "FROM USERS";

		RowMapper<User> rowMapper = new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new User(rs.getString("userId"),
						rs.getString("password"), rs.getString("name"),
						rs.getString("email"), rs.getBoolean("isAdmin"));
			}
		};

		return getJdbcTemplate().query(sql, rowMapper);
	}

	@Override
	public int countAdminUser() throws SQLException {
		String sql = "SELECT count(userId) FROM USERS WHERE isAdmin=true";
		return getJdbcTemplate().queryForInt(sql);
	}

}
