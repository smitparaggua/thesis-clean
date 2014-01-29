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
    	return "http://localhost:8081/testserver/key-request";
    }
    
    public String getWarriorUnlinkMobileURL() {
    	return "http://localhost:8080/testserver/unlink-mobile";
    }
    
    public String getHostWebsite() {
    	return "ray.com";
    }
    
    public String getMessageWhenBLADEServerDown() {
    	return "BLADE Server down";
    }
    
}
