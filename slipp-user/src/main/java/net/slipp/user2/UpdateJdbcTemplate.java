package net.slipp.user2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import net.slipp.support.jdbc.ConnectionManager;

public abstract class UpdateJdbcTemplate {
	public void update(String query) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionManager.getConnection();
			pstmt = con.prepareStatement(query);
			setValues(pstmt);

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
	
	abstract void setValues(PreparedStatement pstmt)
			throws SQLException;
}
