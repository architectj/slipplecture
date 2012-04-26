package net.slipp.support.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class JdbcTemplate {
	public void executeUpdate() throws SQLException {
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
	
	public Object executeQuery() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer findQuery = createQuery();

			con = ConnectionManager.getConnection();
			pstmt = con.prepareStatement(findQuery.toString());
			setValues(pstmt);

			rs = pstmt.executeQuery();

			return mapRow(rs);
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
	
	public abstract Object mapRow(ResultSet rs) throws SQLException;
	
	public abstract void setValues(PreparedStatement pstmt)
			throws SQLException;
	
	public abstract StringBuffer createQuery();
}
