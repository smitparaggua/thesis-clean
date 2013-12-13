package com.csg.warrior.raydotcom.controller;

import com.csg.warrior.raydotcom.controller.SignUpController;
import com.csg.warrior.raydotcom.domain.User;
import com.csg.warrior.raydotcom.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

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
        String controllerResponse = signUpController.processSignUp(user, new RedirectAttributesModelMap());
        assertEquals("redirect:/sign-up/success", controllerResponse);
        verify(userService).save(user);
        verify(userService).signUpToWarrior(user);
    }
}
