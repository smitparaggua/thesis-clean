package com.csg.warrior.raydotcom.service;

import com.csg.warrior.core.WarriorKeyStatus;
import com.csg.warrior.raydotcom.exception.WarriorKeyRequestException;
import com.csg.warrior.raydotcom.exception.WarriorRequestException;

public interface WarriorService {
    WarriorKeyStatus getWarriorKeyStatus(String username, String requestSourceUrl) throws WarriorRequestException;

    String forwardKeyRequestToWARServer(String username, String device_id);
    
    String forwardQuadDeleteRequestToBLADEServer(String username, String bladeKey, String bladeUUID);
    
    String getUnlinkMobileUrl(String username, String hostWebsite);

    String sendMobileKey(String username, String key, String website);
}
