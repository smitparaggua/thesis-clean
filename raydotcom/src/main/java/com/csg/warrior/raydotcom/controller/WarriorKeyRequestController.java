package com.csg.warrior.raydotcom.controller;

import com.csg.warrior.raydotcom.HttpUtils;
import com.csg.warrior.raydotcom.exception.WarriorKeyRequestException;
import com.csg.warrior.raydotcom.service.UserService;
import com.csg.warrior.raydotcom.service.WarriorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/warrior")
public class WarriorKeyRequestController {
    @Autowired private WarriorService warriorService;
    @Autowired private UserService userService;
    
    @RequestMapping("/key-request")
    @ResponseBody
    public String warriorKeyRequest(@RequestParam("username") String username,
    								@RequestParam("password") String password,
    								@RequestParam("gcm_device_id") String gcm_device_id) {
    	
    	System.out.println("ray.com/warrior/key-request: requesting key!");
    	
    	/* At this part, check if the username and password match.
    	 * This will vary depending on the webhost.
    	 */
    	if (userService.getUser(username, password) != null) {
    		try {
    			warriorService.createQuadrupleOnWarrior(username, gcm_device_id);
    			return "Sign-up success";
    		} catch(WarriorKeyRequestException e) {
    			return "Sign-up Failed";
    		}
    	}
    	else return "Username and password mismatch";
    }
}
