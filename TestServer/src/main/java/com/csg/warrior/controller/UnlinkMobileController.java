package com.csg.warrior.controller;

import com.csg.warrior.service.UnlinkMobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

// TODO Refactor: extract ServerConstants to an XML
@Controller
@RequestMapping("/unlink-mobile")
public class UnlinkMobileController {
    @Autowired private UnlinkMobileService unlinkMobileService;
    
    @RequestMapping(value = "/{username}/{website}/{unlinkKey}",method = RequestMethod.POST)
    @ResponseBody
    public String unlinkMobile(@PathVariable String username, @PathVariable String website, @PathVariable String unlinkKey) {
    	unlinkMobileService.unlinkMobile(username, website, unlinkKey);
    	return "Mobile key unlinked";
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String getUnlinkMobileUrl(@RequestParam("username") String username,
                                     @RequestParam("website") String website) {
        return unlinkMobileService.generateUnlinkUrl(username, website);
    }
}
