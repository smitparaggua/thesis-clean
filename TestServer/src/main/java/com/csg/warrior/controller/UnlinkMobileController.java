package com.csg.warrior.controller;

import com.csg.warrior.service.UnlinkMobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

// TODO Refactor: extract ServerConstants to an XML
@Controller
@RequestMapping("/unlink-mobile")
public class UnlinkMobileController {
    @Autowired private UnlinkMobileService unlinkMobileService;
    
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String unlinkMobile(@RequestParam("username") String username,
    						   @RequestParam("website") String website) {
    	unlinkMobileService.unlinkMobile(username, website);
    	return "Mobile key unlinked";
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
