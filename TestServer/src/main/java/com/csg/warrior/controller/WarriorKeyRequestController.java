package com.csg.warrior.controller;

import com.csg.warrior.domain.MobileKey;
import com.csg.warrior.domain.User;
import com.csg.warrior.service.UserMobileKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/key-request")
public class WarriorKeyRequestController {
    @Autowired private UserMobileKeyService userMobileKeyService;

    @RequestMapping(method=RequestMethod.POST)
    @ResponseBody
    public String processSignUp(@RequestParam("username") String username, @RequestParam("website") String website) {
        // TODO HANDLE THIS SIGN UP
        MobileKey mobileKey = MobileKey.generateKey();
        User user = new User(username, website);
        userMobileKeyService.save(user, mobileKey);
        return "User successfully registered to warrior server";
    }
}
