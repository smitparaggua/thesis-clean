package com.csg.warrior.raydotcom.service.impl;

import com.csg.warrior.core.WarriorKeyStatus;
import com.csg.warrior.raydotcom.HttpUtils;
import com.csg.warrior.raydotcom.WarriorConfig;
import com.csg.warrior.raydotcom.exception.WarriorKeyRequestException;
import com.csg.warrior.raydotcom.exception.WarriorRequestException;
import com.csg.warrior.raydotcom.service.WarriorService;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service("warriorService")
public class WarriorServiceImpl implements WarriorService {
    WarriorConfig warriorConfig = new WarriorConfig();
    
    @Override
    public WarriorKeyStatus getWarriorKeyStatus(String username, String website) throws WarriorRequestException {
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.addParameter("username", username);
        httpUtils.addParameter("website", website);
        String response = httpUtils.sendPost(warriorConfig.getKeyStatusUrl());
        return new Gson().fromJson(response, WarriorKeyStatus.class);
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
