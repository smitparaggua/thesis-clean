package com.csg.blade.raydotcom.controller;

import com.csg.blade.raydotcom.domain.User;
import com.csg.blade.raydotcom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/sign-up")
public class SignUpController {
    @Autowired private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method= RequestMethod.GET)
    public String getSignUpPage(ModelMap model) {
        model.addAttribute("user", new User());
        return "sign-up/sign-up";
    }

    @RequestMapping(method=RequestMethod.POST)
    public String processSignUp(User user) {
        userService.signUp(user);
        return "redirect:/sign-up/success";
    }

    @RequestMapping("/success")
    public String signUpSuccess() {
        return "sign-up/success";
    }
}
