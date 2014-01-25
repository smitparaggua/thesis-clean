package com.csg.warrior.raydotcom.service.impl;

import com.csg.warrior.core.WarriorKeyStatus;
import com.csg.warrior.raydotcom.HttpUtils;
import com.csg.warrior.raydotcom.WarriorConfig;
import com.csg.warrior.raydotcom.exception.WarriorKeyRequestException;
import com.csg.warrior.raydotcom.exception.WarriorRequestException;
import com.csg.warrior.raydotcom.service.UserService;
import com.csg.warrior.raydotcom.service.WarriorService;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service("warriorService")
public class WarriorServiceImpl implements WarriorService {
    WarriorConfig warriorConfig = new WarriorConfig();
    
    @Autowired private UserService userService;
    

    @Override
    public WarriorKeyStatus getWarriorKeyStatus(String username, String website) throws WarriorRequestException {
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.addParameter("username", username);
        httpUtils.addParameter("website", website);
        try {
            HttpResponse response = httpUtils.sendPostRequest(warriorConfig.getKeyStatusUrl());
            return (WarriorKeyStatus) httpUtils.receiveReplyAsObject(response);
        } catch (IOException e) {
            throw new WarriorRequestException("Error connecting to warrior server", e);
        } catch (ClassNotFoundException e) {
            throw new WarriorRequestException("Error in warrior server response", e);
        }
    }


    @Override
    public boolean isSignUpSuccessFromReply(String response) {
        if(response == null && response.equals(warriorConfig.getSignUpSuccessReply())) {
            return false;
        }
        return true;
    }
    
    @Override
    public String forwardKeyRequestToWARServer(String username, String device_id) throws WarriorKeyRequestException{
    	
		HttpUtils httpPOST = new HttpUtils();
		httpPOST.addParameter("username", username);
		httpPOST.addParameter("website", "ray.com");
		httpPOST.addParameter("device_id", device_id);
		System.out.println("ray.com HTTP posting to WAR server:" +
							"\nusername: " + username +
							"\nwebsite: " + warriorConfig.getHostWebsite() +
							"\ndevice_id: " + device_id);
		String response = httpPOST.sendPost(warriorConfig.getWarriorKeyRequestURL()); 
		return response;
		
		
		// TODO separate causes of exception (failed connection, already registered, ...)
    }
    
    @Override
    public void forwardUnlinkRequestToWARServer(String username, String website) {
    	// TODO error handling
    	
    	HttpUtils httpPOST = new HttpUtils();
    	httpPOST.addParameter("username", username);
    	httpPOST.addParameter("website", website);
		System.out.println("ray.com HTTP posting to WAR server/unlink-mobile:" +
				"\nusername: " + username +
				"\nwebsite: " + warriorConfig.getHostWebsite());
		httpPOST.sendPost(warriorConfig.getWarriorUnlinkMobileURL());    	
    }
}
