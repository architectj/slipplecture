package net.slipp.user2;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.slipp.user.User;

public class JdbcUserDao {
	public void insert(final User user) throws SQLException {
		UpdateJdbcTemplate jdbcTemplate = new UpdateJdbcTemplate() {
			void setValues(PreparedStatement pstmt)
					throws SQLException {
				pstmt.setString(1, user.getUserId());
				pstmt.setString(2, user.getPassword());
				pstmt.setString(3, user.getName());
				pstmt.setString(4, user.getEmail());
				pstmt.setBoolean(5, user.isAdmin());
			}
		};
		String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql);
	}

	public void update(final User user) throws SQLException {
		UpdateJdbcTemplate updateJdbcTemplate = new UpdateJdbcTemplate() {
			void setValues(PreparedStatement pstmt)
					throws SQLException {
				pstmt.setString(1, user.getName());
				pstmt.setString(2, user.getEmail());
				pstmt.setString(3, user.getUserId());
			}
		};
		String sql = "UPDATE USERS SET name=?, email=? WHERE userid=?";
		updateJdbcTemplate.update(sql);
	}

	public void remove(final String userId) throws SQLException {
		UpdateJdbcTemplate updateJdbcTemplate = new UpdateJdbcTemplate() {
			void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, userId);				
			}
		};
		String sql = "DELETE FROM USERS WHERE userid=?";
		updateJdbcTemplate.update(sql);
	}

	public User findUser(final String userId) throws SQLException {
		SelectJdbcTemplate selectJdbcTemplate = new SelectJdbcTemplate() {
			void setValues(PreparedStatement pstmt)
					throws SQLException {
				pstmt.setString(1, userId);
			}

			Object rowMapper(ResultSet rs) throws SQLException {
				User user = null;
				if (rs.next()) {
					user = new User(
							rs.getString("userId"), 
							rs.getString("password"), 
							rs.getString("name"),
							rs.getString("email"), 
							rs.getBoolean("isAdmin"));
				}
				return user;
			}
		};
		
		String sql = "SELECT userId, password, name, email, isAdmin FROM USERS WHERE userid=?";
		return (User)selectJdbcTemplate.query(sql);
	}

	@SuppressWarnings("unchecked")
	public List<User> findUsers() throws SQLException {
		SelectJdbcTemplate selectJdbcTemplate = new SelectJdbcTemplate() {
			void setValues(PreparedStatement pstmt)
					throws SQLException {
			}

			Object rowMapper(ResultSet rs) throws SQLException {
				List<User> users = new ArrayList<User>();
				while (rs.next()) {
					User user = new User(
							rs.getString("userId"), 
							rs.getString("password"), 
							rs.getString("name"),
							rs.getString("email"), 
							rs.getBoolean("isAdmin"));
					users.add(user);
				}
				return users;
			}
		};
		
		String sql = "SELECT userid, password, name, email, isAdmin FROM USERS";
		return (List<User>)selectJdbcTemplate.query(sql);
	}
}
