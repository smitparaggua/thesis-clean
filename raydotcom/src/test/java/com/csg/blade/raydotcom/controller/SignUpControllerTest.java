package com.csg.blade.raydotcom.controller;

import com.csg.blade.raydotcom.domain.User;
import com.csg.blade.raydotcom.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ModelMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SignUpControllerTest {
    SignUpController signUpController;
    UserService userService;

    @Before
    public void setup() {
        userService = mock(UserService.class);
        signUpController = new SignUpController();
        signUpController.setUserService(userService);
    }

    @Test
    public void retrieveSignUpPage() {
        assertEquals("sign-up/sign-up", signUpController.getSignUpPage(new ModelMap()));
    }

    @Test
    public void signUpSuccess() {
        User user = new User();
        String controllerResponse = signUpController.processSignUp(user);
        assertEquals("redirect:/sign-up/success", controllerResponse);
        verify(userService).signUp(user);
    }
}
