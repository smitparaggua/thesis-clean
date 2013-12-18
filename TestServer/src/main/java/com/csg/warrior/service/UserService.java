package com.csg.warrior.service;

import com.csg.warrior.domain.User;
import com.csg.warrior.service.impl.FailedUrlGenerationException;
import com.csg.warrior.service.impl.NoMobileKeyForUserException;

import javax.naming.AuthenticationException;

public interface UserService {

    void save(User user);

    // TODO: implement unlink mobile. currently commented out to avoid compilation errors
//    void unlinkMobileKey(String username, String password) throws AuthenticationException, FailedUrlGenerationException;

    // TODO: adjust to take care of hashes
    boolean updateMobileKey(String username, String website, String keyUploaded) throws NoMobileKeyForUserException;
}
