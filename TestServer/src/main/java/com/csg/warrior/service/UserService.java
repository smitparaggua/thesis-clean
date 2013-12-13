package com.csg.warrior.service;

import com.csg.warrior.domain.User;
import com.csg.warrior.service.impl.FailedUrlGenerationException;

import javax.naming.AuthenticationException;

public interface UserService {
    public boolean updateMobileKey(String username, String keyUploaded);

    public void save(User user);

    void unlinkMobileKey(String username, String password) throws AuthenticationException, FailedUrlGenerationException;
}
