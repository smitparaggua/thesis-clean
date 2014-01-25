package com.csg.warrior.service;

import com.csg.warrior.dao.MobileKeyDao;
import com.csg.warrior.dao.UserDao;
import com.csg.warrior.domain.User;
import com.csg.warrior.exception.NoMobileKeyForUserException;

public interface UserService {

    void save(User user);

    // TODO: implement unlink mobile. currently commented out to avoid compilation errors
//    void unlinkMobileKey(String username, String password) throws AuthenticationException, FailedUrlGenerationException;

    // TODO: adjust to take care of hashes
    boolean updateMobileKey(String username, String website, String keyUploaded) throws NoMobileKeyForUserException;

    void setUserDao(UserDao userDao);

    void setMobileKeyDao(MobileKeyDao mobileKeyDao);

    void setUserMobileKeyService(UserMobileKeyService userMobileKeyService);

    User getUser(String username, String website);
}
