package com.csg.warrior.service;

public interface UnlinkMobileService {
    String generateUnlinkUrl(String username, String website);

    void unlinkMobile(String username, String website, String unlinkKey);
}
