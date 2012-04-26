package net.slipp.support.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import net.slipp.user.JdbcUserDao;
import net.slipp.user.User;

public class UpdateJdbcTemplate {
	public void update(JdbcUserDao userDao, User user) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			StringBuffer updateQuery = userDao.createQueryForUpdate();

			con = ConnectionManager.getConnection();
			pstmt = con.prepareStatement(updateQuery.toString());
			userDao.setValuesForUpdate(user, pstmt);

			pstmt.executeUpdate();
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}

			if (con != null) {
				con.close();
			}
		}
	}
}
