package com.csg.warrior.raydotcom.controller;

import org.junit.Test;
import org.springframework.ui.ModelMap;

import static org.junit.Assert.assertEquals;

public class LoginControllerTest {
    LoginController loginController = new LoginController();

    @Test
    public void validLogin() {
        assertEquals("redirect:/home", loginController.successfulLogin());
    }

    @Test
    public void invalidLogin() {
        ModelMap modelMap = new ModelMap();
        String actual = loginController.loginFailed(modelMap);
        assertEquals("Invalid username or password", modelMap.get("error"));
        assertEquals("login/login", actual);
    }

    @Test
    public void retrieveOfLoginPage() {
        assertEquals("login/login", loginController.getLoginPage());
    }
}
