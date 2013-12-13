package com.csg.warrior.raydotcom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
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
}
