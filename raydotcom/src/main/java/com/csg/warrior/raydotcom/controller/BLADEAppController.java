package com.csg.warrior.raydotcom.controller;

import com.csg.warrior.raydotcom.service.UserService;
import com.csg.warrior.raydotcom.service.WarriorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/blade")
public class BLADEAppController {
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
    	System.out.println("ray.com: BLADE Key request");
    	if (userService.getUser(username, password) != null) {
   			return warriorService.forwardKeyRequestToWARServer(username, device_id);
    	}
    	else {
    		return "From ray.com: Username and password mismatch";
    	}
    }
    
    @RequestMapping("/quad-delete")
    @ResponseBody
    public String bladeUnlinkFromMobile(@RequestParam("username") String username,
    									@RequestParam("password") String password,
    									@RequestParam("bladeKey") String bladeKey,
    									@RequestParam("bladeUUID") String bladeUUID) {
    	/* At this part, check if the username and password match before forwarding the delete request
    	 * This will vary depending on the webhost.
    	 */
    	System.out.println("ray.com: BLADE Quad delete request");
    	if (userService.getUser(username, password) != null) {
    		return warriorService.forwardQuadDeleteRequestToBLADEServer(username, bladeKey, bladeUUID);
    	}
    	else {
    		return "From ray.com: Username and password mismatch";
    	}
    }
}
