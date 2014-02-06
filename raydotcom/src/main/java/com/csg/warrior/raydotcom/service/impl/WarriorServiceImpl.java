package com.csg.warrior.raydotcom.service.impl;

import com.csg.warrior.core.WarriorKeyStatus;
import com.csg.warrior.raydotcom.HttpUtils;
import com.csg.warrior.raydotcom.WarriorConfig;
import com.csg.warrior.raydotcom.exception.WarriorRequestException;
import com.csg.warrior.raydotcom.service.WarriorService;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

@Service("warriorService")
public class WarriorServiceImpl implements WarriorService {
    private WarriorConfig warriorConfig = new WarriorConfig();
    
    @Override
    public WarriorKeyStatus getWarriorKeyStatus(String username, String website) throws WarriorRequestException {
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.addParameter("username", username);
        httpUtils.addParameter("website", website);
        String response = httpUtils.sendPost(warriorConfig.getKeyStatusUrl());
        return new Gson().fromJson(response, WarriorKeyStatus.class);
    }

    @Override
    public String forwardKeyRequestToWARServer(String username, String device_id){
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
    public String getUnlinkMobileUrl(String username, String website) {
        HttpUtils httpPOST = new HttpUtils();
        httpPOST.addParameter("username", username);
        httpPOST.addParameter("website", website);
        String serverResponse = httpPOST.sendPost(warriorConfig.getWarriorUnlinkMobileURL());
        return serverResponse;
    }
}
