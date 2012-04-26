package net.slipp.support.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import net.slipp.user.User;

public abstract class UpdateJdbcTemplate {
	public void update(User user) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			StringBuffer updateQuery = createQueryForUpdate();

			con = ConnectionManager.getConnection();
			pstmt = con.prepareStatement(updateQuery.toString());
			setValuesForUpdate(user, pstmt);

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
	
	public abstract void setValuesForUpdate(User user, PreparedStatement pstmt)
			throws SQLException;
	
	public abstract StringBuffer createQueryForUpdate();
}
