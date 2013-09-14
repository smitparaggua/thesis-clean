package com.csg.warrior.controller;

import org.junit.Test;
import org.springframework.ui.ModelMap;

import static org.junit.Assert.assertEquals;

public class LoginControllerTest {
    LoginController loginController = new LoginController();

    @Test
    public void retrieveLoginPage() {
        String loginPage = "login/login";
        String actual = loginController.getLoginPage();
        assertEquals(loginPage, actual);
    }

    @Test
    public void successfulLogin() {
        String redirectToHome = "redirect:/home";
        String actual = loginController.successfulLogin();
        assertEquals(redirectToHome, actual);
    }

    @Test
    public void wrongUsernameOrPassword() {
        ModelMap model = new ModelMap();
        String expectedPage = "login/login";
        String actualPage = loginController.loginFailed(model);
        String expectedErrorMessage = "Invalid username or password";
        String modelErrorMessage = (String) model.get("error");
        assertEquals(expectedErrorMessage, modelErrorMessage);
        assertEquals(expectedPage, actualPage);
    }

//    @Test
//    public void correctMobileKeyUploaded() {
//        ModelMap model = new ModelMap();
//        loginController.acceptKeyFromMobile(model);
//    }
}
