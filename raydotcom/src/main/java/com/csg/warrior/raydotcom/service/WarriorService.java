package com.csg.warrior.raydotcom.service;

import com.csg.warrior.raydotcom.exception.WarriorSignUpException;

public interface WarriorService {
    String requestForMobileKey(String username, String requestSourceUrl);

    boolean isWarriorLockedFromReply(String warriorReply);

    void signUpToWarrior(String username) throws WarriorSignUpException;

    boolean isSignUpSuccessFromReply(String response);
}
