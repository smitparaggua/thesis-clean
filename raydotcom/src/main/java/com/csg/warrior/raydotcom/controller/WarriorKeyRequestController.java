package com.csg.warrior.raydotcom.controller;

import com.csg.warrior.raydotcom.exception.WarriorKeyRequestException;
import com.csg.warrior.raydotcom.service.UserService;
import com.csg.warrior.raydotcom.service.WarriorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/warrior")
public class WarriorKeyRequestController {
    @Autowired private WarriorService warriorService;
    @Autowired private UserService userService;
    
    @RequestMapping("/key-request")
    @ResponseBody
    public String warriorKeyRequest(@RequestParam("username") String username,
    								@RequestParam("password") String password,
    								@RequestParam("device_id") String device_id) {
    	
    	/* At this part, check if the username and password match.
    	 * This will vary depending on the webhost.
    	 */
    	System.out.println("ray.com: WarriorKeyRequestController");
    	if (userService.getUser(username, password) != null) {
    		try {
    			return warriorService.forwardKeyRequestToWARServer(username, device_id);
    		} catch(WarriorKeyRequestException e) {
    			return "Sign-up Failed";
    		}
    	}
    	else return "Username and password mismatch";
    }
}
