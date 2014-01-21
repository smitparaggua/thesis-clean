package com.csg.warrior.raydotcom.service;

import com.csg.warrior.raydotcom.domain.WarriorKeyStatus;
import com.csg.warrior.raydotcom.exception.WarriorKeyRequestException;

public interface WarriorService {
    WarriorKeyStatus getWarriorKeyStatus(String username, String requestSourceUrl);

    boolean isSignUpSuccessFromReply(String response);
    
    void createQuadrupleOnWarrior(String username, String gcm_device_id) throws WarriorKeyRequestException;
}
