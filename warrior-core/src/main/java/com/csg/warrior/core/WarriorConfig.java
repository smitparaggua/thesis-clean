package com.csg.warrior.core;

public class WarriorConfig {
    private String warriorUrl;
    private String hostWebsite;

    public WarriorConfig(String warriorUrl, String hostWebsite) {
        this.warriorUrl = warriorUrl;
        this.hostWebsite = hostWebsite;
    }

    public String getSignUpSuccessReply() {
        return "Sign up successful";
    }

    public String getKeyStatusUrl() {
        return warriorUrl + "/key-status";
    }

    public String getWarriorKeyRequestURL() {
        return warriorUrl + "/key-request";
    }

    public String getWarriorUnlinkMobileURL() {
        return warriorUrl + "/unlink-mobile";
    }

    public String getQuadDeleteURL() {
        return warriorUrl + "/quad-delete";
    }

    public String getKeyUploadUrl() {
        return warriorUrl + "/key-upload";
    }

    //this must be the same base URL that you will give to users of the BLADE System
    public String getHostWebsite() {
        return hostWebsite;
    }
}
