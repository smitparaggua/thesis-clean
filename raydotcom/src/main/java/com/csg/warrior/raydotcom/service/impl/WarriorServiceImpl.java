package com.csg.warrior.raydotcom.service.impl;

import com.csg.warrior.core.WarriorKeyStatus;
import com.csg.warrior.raydotcom.HttpUtils;
import com.csg.warrior.raydotcom.WarriorConfig;
import com.csg.warrior.raydotcom.exception.WarriorRequestException;
import com.csg.warrior.raydotcom.service.WarriorService;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.springframework.stereotype.Service;

@Service("warriorService")
public class WarriorServiceImpl implements WarriorService {
    private WarriorConfig warriorConfig = new WarriorConfig();
    
    @Override
    public WarriorKeyStatus getWarriorKeyStatus(String username, String website) throws WarriorRequestException {
        HttpUtils httpUtils = createPostWithUserWebsiteParam(username, website);
        httpUtils.addParameter("invalidateForLogin", "1");
        String response = httpUtils.sendPost(warriorConfig.getKeyStatusUrl());
        try {
            return new Gson().fromJson(response, WarriorKeyStatus.class);
        } catch (JsonSyntaxException e) {
            throw new WarriorRequestException("Invalid response from warrior server", e);
        }
    }

    @Override
    public String forwardKeyRequestToWARServer(String username, String website, String device_id){
		HttpUtils httpPOST = createPostWithUserWebsiteParam(username, website);
		httpPOST.addParameter("device_id", device_id);
		String response = httpPOST.sendPost(warriorConfig.getWarriorKeyRequestURL());
		return response;
    }
    
    @Override
    public String forwardQuadDeleteRequestToBLADEServer(String username, String bladeKey, String bladeUUID) {
    	HttpUtils httpPOST = new HttpUtils();
    	httpPOST.addParameter("username", username);
    	httpPOST.addParameter("website", warriorConfig.getHostWebsite());
    	httpPOST.addParameter("bladeKey", bladeKey);
    	httpPOST.addParameter("bladeUUID", bladeUUID);
    	String response = httpPOST.sendPost(warriorConfig.getQuadDeleteURL());
    	return response;
    }

    @Override
    public String getUnlinkMobileUrl(String username, String website) {
        HttpUtils httpPOST = createPostWithUserWebsiteParam(username, website);
        String serverResponse = httpPOST.sendPost(warriorConfig.getWarriorUnlinkMobileURL());
        return serverResponse;
    }

    @Override
    public String sendMobileKey(String username, String bladeKey, String bladeUUID) {
        HttpUtils request = new HttpUtils();
        request.addParameter("username", username);
        request.addParameter("website", warriorConfig.getHostWebsite());
        request.addParameter("bladeKey", bladeKey);
        request.addParameter("bladeUUID", bladeUUID);
        return request.sendPost(warriorConfig.getKeyUploadUrl());
    }

    private HttpUtils createPostWithUserWebsiteParam(String username, String website) {
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.addParameter("username", username);
        httpUtils.addParameter("website", website);
        return httpUtils;
    }
}
