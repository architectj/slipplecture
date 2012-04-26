package net.slipp.support.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class UpdateJdbcTemplate {
	public void update() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			StringBuffer updateQuery = createQuery();
			con = ConnectionManager.getConnection();
			pstmt = con.prepareStatement(updateQuery.toString());
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
	
	public abstract void setValues(PreparedStatement pstmt)
			throws SQLException;
	
	public abstract StringBuffer createQuery();
}
