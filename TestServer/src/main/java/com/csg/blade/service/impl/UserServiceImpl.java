package com.csg.blade.service.impl;

import com.csg.blade.core.BladeKeyStatus;
import com.csg.blade.dao.MobileKeyDao;
import com.csg.blade.dao.UserDao;
import com.csg.blade.domain.MobileKey;
import com.csg.blade.domain.User;
import com.csg.blade.exception.NoMobileKeyForUserException;
import com.csg.blade.service.UserService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired private UserDao userDao;
    @Autowired private MobileKeyDao mobileKeyDao;

    // TODO: adjust to take care of hashes
    @Override
    public boolean updateMobileKey(String username, String website, String keyUploaded, String bladeUUID) throws NoMobileKeyForUserException {
        User queriedUser = userDao.getUser(username, website);
        MobileKey mobileKey = queriedUser.getMobileKey();
        
        if (mobileKey == null) {
            throw new NoMobileKeyForUserException();
        }
        if (mobileKey.getKeyString().equals(keyUploaded) 
        		&& queriedUser.getDeviceID().equals(bladeUUID)) {    	
            mobileKeyDao.updateMobileKeyUploadTime(mobileKey, DateTime.now());
            return true;
        }
        return false;
    }

    @Override
    public BladeKeyStatus reportMobileKeyStatusOf(User keyOwner, boolean invalidateForLogin) {
        if(keyOwner == null) {
            return new BladeKeyStatus(false, false);
        }
        return new BladeKeyStatus(true, !keyOwner.isMobileKeyExpired());
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void setMobileKeyDao(MobileKeyDao mobileKeyDao) {
        this.mobileKeyDao = mobileKeyDao;
    }

    @Override
    public User getUser(String username, String website) {
        return userDao.getUser(username, website);
    }

    @Override
    public MobileKey getMobileKeyOfUser(String username, String website) {
        User user = getUser(username, website);
        return user.getMobileKey();
    }
}
