package com.csg.blade.core;

public class BladeConfig {
    private String bladeUrl;
    private String hostWebsite;

    public BladeConfig(String bladeUrl, String hostWebsite) {
        this.bladeUrl = bladeUrl;
        this.hostWebsite = hostWebsite;
    }

    public String getKeyStatusUrl() {
        return bladeUrl + "/key-status";
    }

    public String getBladeKeyRequestURL() {
        return bladeUrl + "/key-request";
    }

    public String getBladeUnlinkMobileURL() {
        return bladeUrl + "/unlink-mobile";
    }

    public String getQuadDeleteURL() {
        return bladeUrl + "/quad-delete";
    }

    public String getKeyUploadUrl() {
        return bladeUrl + "/key-upload";
    }

    //this must be the same base URL that you will give to users of the BLADE System
    public String getHostWebsite() {
        return hostWebsite;
    }
}
