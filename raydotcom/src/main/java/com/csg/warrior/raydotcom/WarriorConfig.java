package com.csg.warrior.raydotcom;

public class WarriorConfig {
    // TODO place the configurations in XML.

    public String getSignUpSuccessReply() {
        return "Sign up successful";
    }

    public String getKeyStatusUrl() {
        return "http://localhost:8080/testserver/key-status";
    }
    
    public String getWarriorKeyRequestURL() {
    	return "http://localhost:8080/testserver/key-request";
    }
    
    public String getWarriorUnlinkMobileURL() {
    	return "http://localhost:8080/testserver/unlink-mobile";
    }
    
    public String getQuadDeleteURL() {
    	return "http://localhost:8080/testserver/quad-delete";
    }
    
    //this must be the same base URL that you will give to users of the BLADE System
    public String getHostWebsite() {
    	return "ray.com";
    }
    
    public String getMessageWhenBLADEServerDown() {
    	return "BLADE Server down";
    }

    public String getKeyUploadUrl() {
        return "http://localhost:8080/testserver/key-upload";
    }
}
