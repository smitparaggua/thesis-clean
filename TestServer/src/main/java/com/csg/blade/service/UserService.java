package com.csg.blade.service;

import com.csg.blade.core.BladeKeyStatus;
import com.csg.blade.dao.MobileKeyDao;
import com.csg.blade.dao.UserDao;
import com.csg.blade.domain.MobileKey;
import com.csg.blade.domain.User;
import com.csg.blade.exception.NoMobileKeyForUserException;

public interface UserService {

    void save(User user);

    // TODO: implement unlink mobile. currently commented out to avoid compilation errors
//    void unlinkMobileKey(String username, String password) throws AuthenticationException, FailedUrlGenerationException;

    // TODO: adjust to take care of hashes
    boolean updateMobileKey(String username, String website, String keyUploaded, String bladeUUID) throws NoMobileKeyForUserException;

    void setUserDao(UserDao userDao);

    void setMobileKeyDao(MobileKeyDao mobileKeyDao);

    User getUser(String username, String website);

    MobileKey getMobileKeyOfUser(String username, String website);

    BladeKeyStatus reportMobileKeyStatusOf(User keyOwner, boolean invalidateForLogin);
}
