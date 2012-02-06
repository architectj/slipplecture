package net.slipp.user;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    public UserService() {
    }

    public int create(User user) throws SQLException, ExistedUserException {
        if (getUserDAO().existedUser(user.getUserId())) {
            throw new ExistedUserException(user.getUserId()
                    + "는 이미 존재하는 아이디입니다.");
        }

        return getUserDAO().create(user);
    }

    public int update(User user) throws SQLException {
        return getUserDAO().update(user);
    }

    public int remove(String userId) throws SQLException {
        return getUserDAO().remove(userId);
    }

    public User findUser(String userId) throws SQLException,
            UserNotFoundException {
        User user = getUserDAO().findUser(userId);

        if (user == null) {
            throw new UserNotFoundException(userId + "사용자가 존재하지 않습니다.");
        }

        return user;
    }

    public List<User> findUserList() throws SQLException {
        return getUserDAO().findUserList();
    }

    public boolean login(String userId, String password) throws SQLException,
            UserNotFoundException, PasswordMismatchException {
        User user = findUser(userId);
        if (!user.isMatchPassword(password)) {
            throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
        }
        return true;
    }

    private UserDao getUserDAO() {
        return new UserDao();
    }
}
