package net.slipp.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class UserDao {
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
    public int create(User user) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            StringBuffer insertQuery = new StringBuffer();
            insertQuery.append("INSERT INTO USERS VALUES ");
            insertQuery.append("(?, ?, ?, ?)");

            con = createConnection();
            pstmt = con.prepareStatement(insertQuery.toString());
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getEmail());

            int result = pstmt.executeUpdate();

            return result;
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }

            if (con != null) {
                con.close();
            }
        }
    }

	private Connection createConnection() throws SQLException {
		return dataSource.getConnection();
	}

    public int update(User user) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            StringBuffer updateQuery = new StringBuffer();
            updateQuery.append("UPDATE USERS SET ");
            updateQuery.append("password=?, name=?, email=? ");
            updateQuery.append("WHERE userid=? ");

            con = createConnection();
            pstmt = con.prepareStatement(updateQuery.toString());
            pstmt.setString(1, user.getPassword());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getUserId());

            int result = pstmt.executeUpdate();

            return result;
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }

            if (con != null) {
                con.close();
            }
        }
    }

    public int remove(String userId) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            StringBuffer removeQuery = new StringBuffer();
            removeQuery.append("DELETE FROM USERS ");
            removeQuery.append("WHERE userid=? ");

            con = createConnection();
            pstmt = con.prepareStatement(removeQuery.toString());
            pstmt.setString(1, userId);

            int result = pstmt.executeUpdate();

            return result;
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }

            if (con != null) {
                con.close();
            }
        }
    }

    public User findUser(String userId) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            StringBuffer findQuery = new StringBuffer();
            findQuery.append("SELECT ");
            findQuery.append("userId, password, name, email ");
            findQuery.append("FROM USERS ");
            findQuery.append("WHERE userid=? ");

            con = createConnection();
            pstmt = con.prepareStatement(findQuery.toString());
            pstmt.setString(1, userId);

            rs = pstmt.executeQuery();

            User user = null;
            if (rs.next()) {
                user = new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"), rs.getString("email"));
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

    public List<User> findUserList()
            throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            StringBuffer findQuery = new StringBuffer();
            findQuery.append("SELECT ");
            findQuery.append("userid, password, name, email ");
            findQuery.append("FROM USERS");

            con = createConnection();
            pstmt = con.prepareStatement(findQuery.toString(),
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            rs = pstmt.executeQuery();

            List<User> userList = new ArrayList<User>();
            while (rs.next()) {
                User user = new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"), rs.getString("email"));
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

    public boolean existedUser(String userId) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            StringBuffer existedQuery = new StringBuffer();
            existedQuery.append("SELECT count(*) FROM USERS ");
            existedQuery.append("WHERE userid=? ");

            con = createConnection();
            pstmt = con.prepareStatement(existedQuery.toString());
            pstmt.setString(1, userId);

            rs = pstmt.executeQuery();

            int count = 0;
            if (rs.next()) {
                count = rs.getInt(1);
            }

            if (count == 1) {
                return true;
            } else {
                return false;
            }
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