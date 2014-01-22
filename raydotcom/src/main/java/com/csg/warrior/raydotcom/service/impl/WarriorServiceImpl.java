package com.csg.warrior.raydotcom.service.impl;

import com.csg.warrior.raydotcom.HttpUtils;
import com.csg.warrior.raydotcom.WarriorConfig;
import com.csg.warrior.core.WarriorKeyStatus;
import com.csg.warrior.raydotcom.exception.WarriorRequestException;
import com.csg.warrior.raydotcom.exception.WarriorSignUpException;
import com.csg.warrior.raydotcom.service.WarriorService;
import org.apache.http.HttpResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service("warriorService")
public class WarriorServiceImpl implements WarriorService {
    WarriorConfig warriorConfig = new WarriorConfig();

    @Override
    public WarriorKeyStatus getWarriorKeyStatus(String username, String website) throws WarriorRequestException {
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.addParameter("username", username);
        httpUtils.addParameter("website", website);
        try {
            HttpResponse response = httpUtils.sendPostRequest(warriorConfig.getKeyStatusUrl());
            return (WarriorKeyStatus) httpUtils.receiveReplyAsObject(response);
        } catch (IOException e) {
            throw new WarriorRequestException("Error connecting to warrior server", e);
        } catch (ClassNotFoundException e) {
            throw new WarriorRequestException("Error in warrior server response", e);
        }
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
