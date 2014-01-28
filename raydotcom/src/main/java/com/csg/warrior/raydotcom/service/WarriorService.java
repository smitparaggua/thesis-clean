package com.csg.warrior.raydotcom.service;

import com.csg.warrior.core.WarriorKeyStatus;
import com.csg.warrior.raydotcom.exception.WarriorKeyRequestException;
import com.csg.warrior.raydotcom.exception.WarriorRequestException;

public interface WarriorService {
    WarriorKeyStatus getWarriorKeyStatus(String username, String requestSourceUrl) throws WarriorRequestException;

    boolean isSignUpSuccessFromReply(String response);
    
    String forwardKeyRequestToWARServer(String username, String device_id);
    
    void forwardUnlinkRequestToWARServer(String username, String website);
}
