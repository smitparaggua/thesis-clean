package com.csg.blade.core;

import com.csg.blade.core.exception.BladeRequestException;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class BladeService {
    private BladeConfig bladeConfig;

    public BladeService(String bladeUrl, String website) {
        bladeConfig = new BladeConfig(bladeUrl, website);
    }

    public BladeKeyStatus getBladeKeyStatus(String username) throws BladeRequestException {
        HttpUtils httpUtils = createPostWithUserWebsiteParam(username);
        httpUtils.addParameter("invalidateForLogin", "1");
        String response = httpUtils.sendPost(bladeConfig.getKeyStatusUrl());
        try {
            return new Gson().fromJson(response, BladeKeyStatus.class);
        } catch (JsonSyntaxException e) {
            throw new BladeRequestException("Invalid response from blade server", e);
        }
    }

    public String forwardKeyRequest(String username, String device_id){
        HttpUtils httpPOST = createPostWithUserWebsiteParam(username);
        httpPOST.addParameter("device_id", device_id);
        String response = httpPOST.sendPost(bladeConfig.getBladeKeyRequestURL());
        return response;
    }

    public String forwardKeyDeletion(String username, String bladeKey, String bladeUUID) {
        HttpUtils httpPOST = new HttpUtils();
        httpPOST.addParameter("username", username);
        httpPOST.addParameter("website", bladeConfig.getHostWebsite());
        httpPOST.addParameter("bladeKey", bladeKey);
        httpPOST.addParameter("bladeUUID", bladeUUID);
        String response = httpPOST.sendPost(bladeConfig.getQuadDeleteURL());
        return response;
    }

    public String getUnlinkMobileUrl(String username) {
        HttpUtils httpPOST = createPostWithUserWebsiteParam(username);
        String serverResponse = httpPOST.sendPost(bladeConfig.getBladeUnlinkMobileURL());
        return serverResponse;
    }

    public String sendMobileKey(String username, String bladeKey, String bladeUUID) {
        HttpUtils request = new HttpUtils();
        request.addParameter("username", username);
        request.addParameter("website", bladeConfig.getHostWebsite());
        request.addParameter("bladeKey", bladeKey);
        request.addParameter("bladeUUID", bladeUUID);
        return request.sendPost(bladeConfig.getKeyUploadUrl());
    }

    private HttpUtils createPostWithUserWebsiteParam(String username) {
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.addParameter("username", username);
        httpUtils.addParameter("website", bladeConfig.getHostWebsite());
        return httpUtils;
    }

    public String unlinkMobileKeyOf(String username, String unlinkKeyString) {
        HttpUtils request = new HttpUtils();
        String url = String.format("http://%s/%s/%s/%s", bladeConfig.getBladeUnlinkMobileURL(),
                username, bladeConfig.getHostWebsite(), unlinkKeyString);
        return request.sendGet(url);
    }
}
