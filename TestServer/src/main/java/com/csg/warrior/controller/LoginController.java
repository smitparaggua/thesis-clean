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

    @RequestMapping(value = "/key-upload", method = RequestMethod.POST)
    public String acceptKeyFromMobile(@RequestParam("username") String username, @RequestParam("key") String key) {
        boolean keyIsValid = userService.updateMobileKey(username, key);
        return keyIsValid? "login/mobile-key-accepted" : "login/mobile-key-rejected";
    }
}
