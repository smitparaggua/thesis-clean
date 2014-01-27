package com.csg.warrior.controller;

import com.csg.warrior.core.WarriorKeyStatus;
import com.csg.warrior.domain.User;
import com.csg.warrior.service.UserService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LoginControllerTest {
    private LoginController loginController = new LoginController();
    private UserService userService = mock(UserService.class);

    @Before
    public void setup() {
        loginController.setUserService(userService);
    }

    @Test
    public void getKeyStatusOfNotWarriorUsers() {
        WarriorKeyStatus expected = new WarriorKeyStatus(false, false);
        when(userService.reportMobileKeyStatusOf(any(User.class), anyBoolean()))
                .thenReturn(expected);
        assertEquals(expected, loginController.getKeyLoginStatus("username", "website", false));
        assertEquals(expected, loginController.getKeyLoginStatus("username", "website", true));
    }

    @Test
    public void getKeyStatusOfWarriorUsers() {
        WarriorKeyStatus expected = new WarriorKeyStatus(true, true);
        when(userService.reportMobileKeyStatusOf(any(User.class), anyBoolean()))
                .thenReturn(expected);
        assertEquals(expected, loginController.getKeyLoginStatus("username", "website", true));
        assertEquals(expected, loginController.getKeyLoginStatus("username", "website", false));
    }
}

