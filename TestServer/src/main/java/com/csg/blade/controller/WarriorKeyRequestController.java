package com.csg.blade.controller;

import com.csg.blade.domain.User;
import com.csg.blade.service.KeyRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/key-request")
public class WarriorKeyRequestController {
    @Autowired private KeyRequestService keyRequestService;

    @RequestMapping(method=RequestMethod.POST)
    @ResponseBody
    public String processKeyRequest(@RequestParam("username") String username, 
    								@RequestParam("website") String website,
    								@RequestParam("device_id") String gcm_device_id) {
    	
    	return keyRequestService.checkWarriorRegistration(new User(username, website, gcm_device_id));
    }
    
    
}
