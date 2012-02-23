package net.slipp.user.event;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.sql.DataSource;

public class UserLoginEventDao {
    private DataSource dataSource;

    private Connection createConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public void create(UserLoginEvent event) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            StringBuffer insertQuery = new StringBuffer();
            insertQuery.append("INSERT INTO USERS_LOGIN_EVENT ");
            insertQuery.append("(createdDate, userId, password, idaddress, success ) VALUES ");
            insertQuery.append("(?, ?, ?, ?, ?)");

            con = createConnection();
            pstmt = con.prepareStatement(insertQuery.toString());
            pstmt.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            pstmt.setString(2, event.getUserId());
            pstmt.setString(3, event.getPassword());
            pstmt.setString(4, "127.0.0.1");
            pstmt.setBoolean(5, event.isSuccess());

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
