package com.csg.warrior.controller;

import com.csg.warrior.ServerConstants;
import com.csg.warrior.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// TODO Refactor: extract ServerConstants to an XML
@Controller
@RequestMapping(ServerConstants.UNLINK_MOBILE_KEY_URL)
public class UnlinkMobileController {
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String displayUnlinkMobilePage(@ModelAttribute("errorMessage") String errorMessage) {
        return "key-management/unlink-mobile";
    }

    @RequestMapping("/success")
    public String notifySuccessfulUnlink() {
        return "key-management/unlink-mobile-success";
    }

    @RequestMapping("/cannot-generate-link")
    public String notifyFailureOfLinkGeneration() {
        return "key-management/failed-unlink";
    }
}
