package com.csg.warrior.controller;

import com.csg.warrior.core.WarriorKeyStatus;
import com.csg.warrior.domain.User;
import com.csg.warrior.service.UserService;
import com.csg.warrior.exception.NoMobileKeyForUserException;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
    @Autowired private UserService userService;

    // TODO consider using ModelAttribute (too many parameters)
    @RequestMapping(value = "/key-upload", method = RequestMethod.POST)
    @ResponseBody
    public String acceptKeyFromMobile(@RequestParam("username") String username,
                                      @RequestParam("key") String key,
                                      @RequestParam("website") String requestSourceWebsite) {
        boolean keyIsValid;
        try {
            keyIsValid = userService.updateMobileKey(username, requestSourceWebsite, key);
        } catch (NoMobileKeyForUserException e) {
            return "User does not have a designated mobile key";
        }
        return keyIsValid? "Key Uploaded!" : "Invalid Key";
    }

    @RequestMapping(value = "/key-status")
    @ResponseBody
    public String getKeyLoginStatus(@RequestParam("username") String username,
                                    @RequestParam("website") String website,
                                    @RequestParam("invalidateForLogin") boolean invalidateForLogin) {
        User keyOwner = userService.getUser(username, website);
        WarriorKeyStatus status = userService.reportMobileKeyStatusOf(keyOwner, invalidateForLogin);
        return new Gson().toJson(status);
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
