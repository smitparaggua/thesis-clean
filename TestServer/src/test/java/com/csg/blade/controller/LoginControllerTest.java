package com.csg.blade.controller;

import com.csg.blade.core.BladeKeyStatus;
import com.csg.blade.domain.User;
import com.csg.blade.service.UserService;
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
        BladeKeyStatus expected = new BladeKeyStatus(false, false);
        when(userService.reportMobileKeyStatusOf(any(User.class), anyBoolean()))
                .thenReturn(expected);
        assertEquals(expected, loginController.getKeyLoginStatus("username", "website", false));
        assertEquals(expected, loginController.getKeyLoginStatus("username", "website", true));
    }

    @Test
    public void getKeyStatusOfWarriorUsers() {
        BladeKeyStatus expected = new BladeKeyStatus(true, true);
        when(userService.reportMobileKeyStatusOf(any(User.class), anyBoolean()))
                .thenReturn(expected);
        assertEquals(expected, loginController.getKeyLoginStatus("username", "website", true));
        assertEquals(expected, loginController.getKeyLoginStatus("username", "website", false));
    }
}
