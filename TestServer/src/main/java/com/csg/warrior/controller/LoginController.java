package com.csg.warrior.controller;

import com.csg.warrior.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/login")
    public String getLoginPage() {
        return "login/login";
    }

    @RequestMapping("/login-success")
    public String successfulLogin() {
        return "redirect:/home";
    }

    @RequestMapping("/login-failed")
    public String loginFailed(ModelMap model) {
        model.addAttribute("error", "Invalid username or password");
        return "login/login";
    }

    @RequestMapping(value = "/key-upload", method = RequestMethod.POST)
    public String acceptKeyFromMobile(@RequestParam("username") String username, @RequestParam("key") String key) {
        boolean keyIsValid = userService.updateMobileKey(username, key);
        return keyIsValid? "login/mobile-key-accepted" : "login/mobile-key-rejected";
    }
}
