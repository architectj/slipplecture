package net.slipp.support.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import net.slipp.user.User;

public abstract class InsertJdbcTemplate {
	public void create(User user) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			StringBuffer insertQuery = createQueryForInsert();
			con = ConnectionManager.getConnection();
			pstmt = con.prepareStatement(insertQuery.toString());
			setValuesForInsert(user, pstmt);

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
	
	public abstract StringBuffer createQueryForInsert() throws SQLException;
	
	
	public abstract void setValuesForInsert(User user, PreparedStatement pstmt)
			throws SQLException;
}
