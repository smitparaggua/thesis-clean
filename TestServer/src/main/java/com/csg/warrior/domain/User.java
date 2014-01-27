package com.csg.warrior.domain;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long userId;
    private String username;
    private String website;
    private String deviceID;
    @OneToOne
    private MobileKey mobileKey;

    public User() {  }
    
    public User(String username, String website, String deviceID) {
        this.username = username;
        this.website = website;
        this.deviceID = deviceID;
    }

    @Transient
    public boolean isMobileKeyTransient() {
        return mobileKey.isTransient();
    }

    @Transient
    public boolean isMobileKeyValid() {
        return mobileKey.isValid();
    }

    public Long getUserId() {
        return userId;
    }

    private void setUserId(Long userId) {
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

    public MobileKey getMobileKey() {
        return mobileKey;
    }

    public void setMobileKey(MobileKey mobileKey) {
        this.mobileKey = mobileKey;
    }

    @Override
    public String toString() {
        return "User{" + "username='" + username +  "', website=" + website + "}";
    }

    @Transient
    public String getMobileKeyString() {
        return mobileKey.getKeyString();
    }
}
