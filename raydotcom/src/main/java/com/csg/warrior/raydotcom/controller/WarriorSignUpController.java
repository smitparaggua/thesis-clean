package com.csg.warrior.raydotcom.controller;

import com.csg.warrior.raydotcom.exception.WarriorSignUpException;
import com.csg.warrior.raydotcom.service.WarriorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/warrior")
public class WarriorSignUpController {
    @Autowired private WarriorService warriorService;

    @RequestMapping("/sign-up")
    @ResponseBody
    public String warriorSignUp(@RequestParam("username") String username) {
        try {
            warriorService.signUpToWarrior(username);
            return "Sign-up Success";
        } catch(WarriorSignUpException e) {
            return "Sign-up Failed";
        }
    }
}
