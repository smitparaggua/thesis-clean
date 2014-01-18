package com.csg.warrior.raydotcom.service.impl;

import com.csg.warrior.raydotcom.dao.UserDao;
import com.csg.warrior.raydotcom.domain.User;
import com.csg.warrior.raydotcom.service.WarriorService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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
        makeUserDaoReturnMockUser(validUsername);
        userService.loadUserByUsername(validUsername);
        verify(warriorService).requestForMobileKey(validUsername, "ray.com");
        verify(userDao).getUserByUsername(validUsername);
    }

    @Test
    public void warriorValidatedUserCanLogin() {
        String validUsername = "validUsername";
        makeUserDaoReturnMockUser(validUsername);
        when(warriorService.requestForMobileKey(validUsername, "ray.com")).thenReturn("User can now login");
        UserDetails springUser = userService.loadUserByUsername(validUsername);
        assertTrue(springUser.isAccountNonLocked());
    }

    @Test
    public void nonWarriorValidatedUserCanLogin() {
        String validUsername = "validUsername";
        makeUserDaoReturnMockUser(validUsername);
        when(warriorService.requestForMobileKey(validUsername, "ray.com")).thenReturn("User does not use warrior service");
        UserDetails springUser = userService.loadUserByUsername(validUsername);
        assertTrue(springUser.isAccountNonLocked());
    }

    @Test
    public void mobileKeyNotUploadedLocksLogin() {
        // note: this fails because of faulty test
        String validUsername = "validUsername";
        makeUserDaoReturnMockUser(validUsername);
        when(warriorService.requestForMobileKey(validUsername, "ray.com")).thenReturn("User must upload mobile key");
        UserDetails springUser = userService.loadUserByUsername(validUsername);
        assertFalse(springUser.isAccountNonLocked());
    }

    private void makeUserDaoReturnMockUser(String username) {
        User testUser = createTestUser(username, "password");
        when(userDao.getUserByUsername(username)).thenReturn(testUser);
    }

    private User createTestUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return user;
    }
}
