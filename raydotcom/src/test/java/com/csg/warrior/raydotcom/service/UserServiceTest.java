package com.csg.warrior.raydotcom.service;

import com.csg.warrior.raydotcom.dao.UserDao;
import com.csg.warrior.raydotcom.domain.User;
import com.csg.warrior.raydotcom.domain.WarriorKeyStatus;
import com.csg.warrior.raydotcom.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    private static final String URL = "ray.com";

    private UserServiceImpl userService = new UserServiceImpl();
    private WarriorService warriorService;
    private UserDao userDao;
    private User mockValidUser;

    @Before
    public void setup() {
        userDao = mock(UserDao.class);
        mockValidUser = mockValidUser();
        warriorService = mock(WarriorService.class);
        when(warriorService.getWarriorKeyStatus(mockValidUser.getUsername(), URL))
                .thenReturn(new WarriorKeyStatus(true, true));
        userService.setWarriorService(warriorService);
        when(userDao.getUserByUsername(mockValidUser.getUsername())).thenReturn(mockValidUser);
        userService.setUserDao(userDao);
    }

    private User mockValidUser() {
        User user = new User();
        user.setEmail("user@gmail.com");
        user.setUsername("user");
        user.setPassword("pass");
        return user;
    }

    @Test
    public void validLoadOfSpringSecurityUser() {
        userService.loadUserByUsername(mockValidUser.getUsername());
        verify(warriorService).getWarriorKeyStatus(mockValidUser.getUsername(), URL);
        verify(userDao).getUserByUsername(mockValidUser.getUsername());
    }

    @Test
    public void warriorValidatedUserCanLogin() {
        UserDetails springUser = userService.loadUserByUsername(mockValidUser.getUsername());
        assertTrue(springUser.isAccountNonLocked());
    }

    @Test
    public void nonWarriorValidatedUserCanLogin() {
        when(warriorService.getWarriorKeyStatus(mockValidUser.getUsername(), URL))
                .thenReturn(new WarriorKeyStatus(false, false));
        UserDetails springUser = userService.loadUserByUsername(mockValidUser.getUsername());
        assertTrue(springUser.isAccountNonLocked());
    }

    @Test
    public void mobileKeyNotUploadedLocksLogin() {
        when(warriorService.getWarriorKeyStatus(mockValidUser.getUsername(), URL))
                .thenReturn(new WarriorKeyStatus(true, false));
        UserDetails springUser = userService.loadUserByUsername(mockValidUser.getUsername());
        assertFalse(springUser.isAccountNonLocked());
    }
}
