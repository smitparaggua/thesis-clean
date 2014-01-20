package com.csg.warrior.raydotcom.service.impl;

import com.csg.warrior.raydotcom.HttpUtils;
import com.csg.warrior.raydotcom.WarriorConfig;
import com.csg.warrior.raydotcom.domain.WarriorKeyStatus;
import com.csg.warrior.raydotcom.exception.WarriorSignUpException;
import com.csg.warrior.raydotcom.service.WarriorService;
import org.springframework.stereotype.Service;

@Service("warriorService")
public class WarriorServiceImpl implements WarriorService {
    WarriorConfig warriorConfig = new WarriorConfig();

    @Override
    public WarriorKeyStatus getWarriorKeyStatus(String username, String requestSourceUrl) {
        return null;
    }

    @Override
    public void signUpToWarrior(String username) throws WarriorSignUpException {
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.addParameter("username", username);
        httpUtils.addParameter("website", "ray.com");
        String response = httpUtils.sendPost(warriorConfig.getSignUpRL());
        if(!isSignUpSuccessFromReply(response)) {
            throw new WarriorSignUpException("Sign up failed");
        };
        // TODO separate causes of exception (failed connection, already registered, ...)
    }

    @Override
    public boolean isSignUpSuccessFromReply(String response) {
        if(response == null && response.equals(warriorConfig.getSignUpSuccessReply())) {
            return false;
        }
        return true;
    }
}
