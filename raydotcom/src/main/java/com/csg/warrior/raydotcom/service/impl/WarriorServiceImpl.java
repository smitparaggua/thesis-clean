package com.csg.warrior.raydotcom.service.impl;

import com.csg.warrior.raydotcom.HttpUtils;
import com.csg.warrior.raydotcom.WarriorConfig;
import com.csg.warrior.raydotcom.domain.WarriorKeyStatus;
import com.csg.warrior.raydotcom.exception.WarriorKeyRequestException;
import com.csg.warrior.raydotcom.service.UserService;
import com.csg.warrior.raydotcom.service.WarriorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("warriorService")
public class WarriorServiceImpl implements WarriorService {
    WarriorConfig warriorConfig = new WarriorConfig();
    
    @Autowired private UserService userService;
    

    @Override
    public WarriorKeyStatus getWarriorKeyStatus(String username, String requestSourceUrl) {
        return null;
    }


    @Override
    public boolean isSignUpSuccessFromReply(String response) {
        if(response == null && response.equals(warriorConfig.getSignUpSuccessReply())) {
            return false;
        }
        return true;
    }
    
    @Override
    public void createQuadrupleOnWarrior(String username, String gcm_device_id) throws WarriorKeyRequestException{
    	
		HttpUtils httpPOST = new HttpUtils();
		httpPOST.addParameter("username", username);
		httpPOST.addParameter("website", "ray.com");
		httpPOST.addParameter("gcm_device_id", gcm_device_id);
		String response = httpPOST.sendPost(warriorConfig.getWarriorKeyRequestURL()); 
		if (response.equals("FAIL")) throw new WarriorKeyRequestException("Sign up failed");
		
		// TODO separate causes of exception (failed connection, already registered, ...)
    }
}
