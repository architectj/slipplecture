package net.slipp.user2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.slipp.support.jdbc.ConnectionManager;

public abstract class SelectJdbcTemplate {
	public Object query(String sql) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql);
			setValues(pstmt);

			rs = pstmt.executeQuery();

			return rowMapper(rs);
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
	
	abstract void setValues(PreparedStatement pstmt)
			throws SQLException;
	
	abstract Object rowMapper(ResultSet rs) throws SQLException;
}
