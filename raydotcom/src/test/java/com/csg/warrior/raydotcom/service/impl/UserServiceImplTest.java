package com.csg.warrior.raydotcom.service.impl;

import com.csg.warrior.raydotcom.dao.UserDao;
import com.csg.warrior.raydotcom.domain.User;
import com.csg.warrior.raydotcom.service.WarriorService;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class UserServiceImplTest {
    private UserServiceImpl userService;
    private WarriorService warriorService;
    private UserDao userDao;

    @Before
    public void setup() {
        userDao = mock(UserDao.class);
        warriorService = mock(WarriorService.class);
        userService = new UserServiceImpl();
        userService.setUserDao(userDao);
        userService.setWarriorService(warriorService);
    }

    @Test
    public void validLoadOfSpringSecurityUser() {
        String validUsername = "validUsername";
        User testUser = createTestUser(validUsername, "password");
        when(userDao.getUserByUsername(validUsername)).thenReturn(testUser);
        userService.loadUserByUsername(validUsername);
        verify(warriorService).isUserLocked(validUsername);
        verify(userDao).getUserByUsername(validUsername);
    }

    private User createTestUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return user;
    }
}
