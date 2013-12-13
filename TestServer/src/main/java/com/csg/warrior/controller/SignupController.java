package com.csg.warrior.controller;

import com.csg.warrior.domain.MobileKey;
import com.csg.warrior.domain.User;
import com.csg.warrior.service.MobileKeyService;
import com.csg.warrior.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/signup")
public class SignupController {
    @Autowired
    private UserService userService;
    @Autowired
    private MobileKeyService mobileKeyService;

    @RequestMapping(method=RequestMethod.POST)
    public String processSignup(User user, RedirectAttributes redirectAttributes) {
        // TODO HANDLE THIS SIGN UP
        MobileKey mobileKey = MobileKey.generateKey();
        mobileKeyService.save(mobileKey);
        user.setMobileKey(mobileKey);
        userService.save(user);
        redirectAttributes.addFlashAttribute("userRegistered", user);
        redirectAttributes.addFlashAttribute("mobileKey", mobileKey);
        return "redirect:/signup/success";
    }
}
