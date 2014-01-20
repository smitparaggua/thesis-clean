package com.csg.warrior.raydotcom.controller;

import com.csg.warrior.raydotcom.HttpUtils;
import com.csg.warrior.raydotcom.exception.WarriorSignUpException;
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
    
    private static final String WARRIOR_KEY_REQUEST_CONTROLLER_URL = "dummy_address";
    
    @RequestMapping("/sign-up")
    @ResponseBody
    public String warriorSignUp(@RequestParam("username") String username) {
        try {
            warriorService.signUpToWarrior(username);
            return "Sign-up Success";
        } catch(WarriorSignUpException e) {
            return "Sign-up Failed";
        }
    }
    
    @RequestMapping("/key-request")
    @ResponseBody
    public String warriorKeyRequest(@RequestParam("username") String username,
    								@RequestParam("password") String password,
    								@RequestParam("gcm_device_id") String gcm_device_id) {
    	
    	/* At this part, check if the username and password match.
    	 * This will vary depending on the webhost.
    	 */
    	
    	if (userService.getUser(username, password) != null) {
    		HttpUtils httpPOST = new HttpUtils();
    		httpPOST.addParameter("username", username);
    		httpPOST.addParameter("website", "ray.com");
    		httpPOST.addParameter("gcm_device_id", gcm_device_id);
    		httpPOST.sendPost(WARRIOR_KEY_REQUEST_CONTROLLER_URL);
    	}
    	else {
    		return "Incorrect username and password pair.";
    	}
    			
    	return "";
    }
}
