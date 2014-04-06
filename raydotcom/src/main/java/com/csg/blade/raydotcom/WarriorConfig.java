package com.csg.blade.raydotcom;

public class WarriorConfig {
    private String warriorUrl = "http://localhost:8080/testserver";
    private String hostWebsite = "ray.com";

    public String getSignUpSuccessReply() {
        return "Sign up successful";
    }

    public String getBLADEServerURL() {
    	return "http://localhost:8080/BLADEserver/";
    }
    
    public String getKeyStatusUrl() {
        return getBLADEServerURL() + "key-status";
    }

    public String getWarriorKeyRequestURL() {
    	return getBLADEServerURL() + "key-request";
    }

    public String getWarriorUnlinkMobileURL() {
    	return getBLADEServerURL() + "unlink-mobile";
    }

    public String getQuadDeleteURL() {
    	return getBLADEServerURL() + "quad-delete";
    }
    
    //this must be the same base URL that you will give to users of the BLADE System
    public String getHostWebsite() {
    	return "http://172.16.1.117:8080/raydotcom";
    }
    
    public String getMessageWhenBLADEServerDown() {
    	return "BLADE Server down";
    }

    public String getKeyUploadUrl() {
        return getBLADEServerURL() + "key-upload";
    }
}
