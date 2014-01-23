package com.csg.warrior.controller;

import com.csg.warrior.service.KeyRequestService;
import com.csg.warrior.service.UserMobileKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/key-request")
public class WarriorKeyRequestController {
    @Autowired private UserMobileKeyService userMobileKeyService;
    @Autowired private KeyRequestService keyRequestService;

    @RequestMapping(method=RequestMethod.POST)
    @ResponseBody
    public String processKeyRequest(@RequestParam("username") String username, 
    								@RequestParam("website") String website,
    								@RequestParam("gcm_device_id") String gcm_device_id) {
    									
        // TODO HANDLE THIS SIGN UP
    	
    	/*
    	 * Possible situations:
    	 * 1.) First time cinlick yung "Request key" button --> no (username, website, device_id, warriorkey) yet
    	 * 2.) "This (username, website, device_id) already has a warriorkey"
    	 * 3.) "This (username, website) already exists but different device_id and/or warriorkey: you are an impostor"
    	 * 
    	 * Best order of checking: 
    	 * 2 --> regenerate key or ibigay na lang yung key?
    	 * 1 --> create quadruple
    	 * 3 --> LOLNOPE
    	 */
    	
    	keyRequestService.checkWarriorRegistration(username, website, gcm_device_id);
    	
    	System.out.println("WAR server: WarriorKeyRequestController");
    	
    	
        return "User successfully registered to warrior server";
    }
    
    
}
