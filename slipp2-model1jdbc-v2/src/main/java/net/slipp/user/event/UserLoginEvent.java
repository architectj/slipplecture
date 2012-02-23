package net.slipp.user.event;

public class UserLoginEvent {
    private String userId;
    private String password;
    private boolean success;

    public UserLoginEvent(String userId, String password, boolean success) {
        this.userId = userId;
        this.password = password;
        this.success = success;
    }

    public String getUserId() {
        return userId;
    }
    
    public String getPassword() {
        return password;
    }
    
    public boolean isSuccess() {
        return success;
    }

    @Override
    public String toString() {
        return "UserLoginEvent [userId=" + userId + ", password=" + password
                + ", success=" + success + "]";
    }
}
