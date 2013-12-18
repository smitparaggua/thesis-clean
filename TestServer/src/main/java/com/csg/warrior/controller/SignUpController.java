package com.csg.warrior.controller;

import com.csg.warrior.domain.MobileKey;
import com.csg.warrior.domain.User;
import com.csg.warrior.service.MobileKeyService;
import com.csg.warrior.service.UserMobileKeyService;
import com.csg.warrior.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/sign-up")
public class SignUpController {
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
