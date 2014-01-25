package com.csg.warrior.controller;

import com.csg.warrior.ServerConstants;
import com.csg.warrior.service.UnlinkMobileService;
import com.csg.warrior.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.naming.AuthenticationException;

// TODO Refactor: extract ServerConstants to an XML
@Controller
@RequestMapping("/unlink-mobile")
public class UnlinkMobileController {
    @Autowired private UserService userService;
    @Autowired private UnlinkMobileService unlinkMobileService; 
    
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String unlinkMobile(@RequestParam("username") String username,
    						   @RequestParam("website") String website) {
    	System.out.println("WAR server/unlink-mobile");
    	unlinkMobileService.unlinkMobile(username, website);
    	return "";
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
