package com.csg.warrior.service.impl;

import com.csg.warrior.core.WarriorKeyStatus;
import com.csg.warrior.dao.MobileKeyDao;
import com.csg.warrior.dao.UserDao;
import com.csg.warrior.domain.MobileKey;
import com.csg.warrior.domain.User;
import com.csg.warrior.exception.NoMobileKeyForUserException;
import com.csg.warrior.service.UserService;
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
    public boolean updateMobileKey(String username, String website, String keyUploaded) throws NoMobileKeyForUserException {
        MobileKey mobileKey = getMobileKeyOfUser(username, website);
        if (mobileKey == null) {
            throw new NoMobileKeyForUserException();
        }
        if (mobileKey.getKeyString().equals(keyUploaded)) {
            mobileKeyDao.updateMobileKeyUploadTime(mobileKey, DateTime.now());
            return true;
        }
        return false;
    }

    @Override
    public WarriorKeyStatus reportMobileKeyStatusOf(User keyOwner, boolean invalidateForLogin) {
        if(keyOwner == null) {
            return new WarriorKeyStatus(false, false);
        }
        return new WarriorKeyStatus(true, keyOwner.isMobileKeyValid());
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
