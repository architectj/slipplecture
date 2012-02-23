package net.slipp.user.event;

import org.junit.Test;

public class UserLoginEventDaoTest {
    @Test
    public void create() throws Exception {
        boolean success = true;
        UserLoginEvent event = new UserLoginEvent("userId", "password", success);
        
        UserLoginEventDao dut = new UserLoginEventDao();
        dut.create(event);
    }
}
