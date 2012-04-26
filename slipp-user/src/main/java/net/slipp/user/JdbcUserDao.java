package net.slipp.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.slipp.support.jdbc.ConnectionManager;
import net.slipp.support.jdbc.UpdateJdbcTemplate;

import org.springframework.stereotype.Repository;

@Repository
public class JdbcUserDao implements UserDao {
	/* (non-Javadoc)
	 * @see net.slipp.user.IUserDao#create(net.slipp.user.User)
	 */
	@Override
	public void create(final User user) throws SQLException {
		UpdateJdbcTemplate jdbcTemplate = new UpdateJdbcTemplate() {
			public void setValues(PreparedStatement pstmt)
					throws SQLException {
				pstmt.setString(1, user.getUserId());
				pstmt.setString(2, user.getPassword());
				pstmt.setString(3, user.getName());
				pstmt.setString(4, user.getEmail());
				pstmt.setBoolean(5, user.isAdmin());
			}

			public StringBuffer createQuery() {
				StringBuffer insertQuery = new StringBuffer();
				insertQuery.append("INSERT INTO USERS VALUES ");
				insertQuery.append("(?, ?, ?, ?, ?)");
				return insertQuery;
			}
		};
		jdbcTemplate.update();
	}

	/* (non-Javadoc)
	 * @see net.slipp.user.IUserDao#update(net.slipp.user.User)
	 */
	@Override
	public void update(final User user) throws SQLException {
		UpdateJdbcTemplate jdbcTemplate = new UpdateJdbcTemplate() {
			public void setValues(PreparedStatement pstmt)
					throws SQLException {
				pstmt.setString(1, user.getName());
				pstmt.setString(2, user.getEmail());
				pstmt.setString(3, user.getUserId());
			}

			public StringBuffer createQuery() {
				StringBuffer updateQuery = new StringBuffer();
				updateQuery.append("UPDATE USERS SET ");
				updateQuery.append("name=?, email=?");
				updateQuery.append("WHERE userid=? ");
				return updateQuery;
			}
		};
		jdbcTemplate.update();
	}


	/* (non-Javadoc)
	 * @see net.slipp.user.IUserDao#remove(java.lang.String)
	 */
	@Override
	public void remove(final String userId) throws SQLException {
		UpdateJdbcTemplate jdbcTemplate = new UpdateJdbcTemplate() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, userId);
			}
			
			@Override
			public StringBuffer createQuery() {
				StringBuffer removeQuery = new StringBuffer();
				removeQuery.append("DELETE FROM USERS ");
				removeQuery.append("WHERE userid=? ");
				return removeQuery;
			}
		};
		jdbcTemplate.update();
	}

	/* (non-Javadoc)
	 * @see net.slipp.user.IUserDao#findUser(java.lang.String)
	 */
	@Override
	public User findUser(String userId) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer findQuery = new StringBuffer();
			findQuery.append("SELECT ");
			findQuery.append("userId, password, name, email, isAdmin ");
			findQuery.append("FROM USERS ");
			findQuery.append("WHERE userid=? ");

			con = ConnectionManager.getConnection();
			pstmt = con.prepareStatement(findQuery.toString());
			pstmt.setString(1, userId);

			rs = pstmt.executeQuery();

			User user = null;
			if (rs.next()) {
				user = new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
						rs.getString("email"), rs.getBoolean("isAdmin"));
			}

			return user;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}

	/* (non-Javadoc)
	 * @see net.slipp.user.IUserDao#findUserList()
	 */
	@Override
	public List<User> findUserList() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer findQuery = new StringBuffer();
			findQuery.append("SELECT ");
			findQuery.append("userid, password, name, email, isAdmin ");
			findQuery.append("FROM USERS");

			con = ConnectionManager.getConnection();
			pstmt = con.prepareStatement(findQuery.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			rs = pstmt.executeQuery();

			List<User> userList = new ArrayList<User>();
			while (rs.next()) {
				User user = new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
						rs.getString("email"), rs.getBoolean("isAdmin"));
				userList.add(user);
			}

			return userList;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}

	/* (non-Javadoc)
	 * @see net.slipp.user.IUserDao#countAdminUser()
	 */
	@Override
	public int countAdminUser() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer findQuery = new StringBuffer();
			findQuery.append("SELECT count(userId) FROM USERS WHERE isAdmin=true");

			con = ConnectionManager.getConnection();
			pstmt = con.prepareStatement(findQuery.toString());

			rs = pstmt.executeQuery();

			rs.next();
			return rs.getInt(1);
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}
}
