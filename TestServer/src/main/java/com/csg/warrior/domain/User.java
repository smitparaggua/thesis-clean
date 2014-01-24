package com.csg.warrior.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long userId;
    private String username;
    private String website;
    private String deviceID;
    
    public User() {
    	
    }
    
    public User(String username, String website, String deviceID) {
        this.username = username;
        this.website = website;
        this.deviceID = deviceID;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
    
    public void setDeviceID(String gcmDeviceID) {
    	this.deviceID = gcmDeviceID;
    }
    
    public String getDeviceID() {
    	return deviceID;
    }

    @Override
    public String toString() {
        return "User{" + "username='" + username +  "', website=" + website + "}";
    }
}
