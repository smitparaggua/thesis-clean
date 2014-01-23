package com.csg.warrior.raydotcom.service;

import com.csg.warrior.core.WarriorKeyStatus;
import com.csg.warrior.raydotcom.exception.WarriorRequestException;
import com.csg.warrior.raydotcom.exception.WarriorSignUpException;

public interface WarriorService {
    WarriorKeyStatus getWarriorKeyStatus(String username, String requestSourceUrl) throws WarriorRequestException;

    void signUpToWarrior(String username) throws WarriorSignUpException;

    boolean isSignUpSuccessFromReply(String response);
}
