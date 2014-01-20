package com.csg.warrior.raydotcom.service;

import com.csg.warrior.raydotcom.domain.WarriorKeyStatus;
import com.csg.warrior.raydotcom.exception.WarriorSignUpException;

public interface WarriorService {
    WarriorKeyStatus getWarriorKeyStatus(String username, String requestSourceUrl);

    void signUpToWarrior(String username) throws WarriorSignUpException;

    boolean isSignUpSuccessFromReply(String response);
}
