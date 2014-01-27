package com.csg.warrior.service.impl;

import com.csg.warrior.core.WarriorKeyStatus;
import com.csg.warrior.domain.MobileKey;
import com.csg.warrior.domain.User;
import com.csg.warrior.service.MobileKeyService;
import com.csg.warrior.service.UserMobileKeyService;
import com.csg.warrior.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userMobileKeyService")
public class UserMobileKeyServiceImpl implements UserMobileKeyService {
    @Autowired private UserService userService;
    @Autowired private MobileKeyService mobileKeyService;

    @Override
    public void save(User user, MobileKey mobileKey) {
        user.setMobileKey(mobileKey);
        userService.save(user);
    }

    @Override
    public MobileKey getMobileKeyOfUser(User user) {
        return user.getMobileKey();
    }

    @Override
    public WarriorKeyStatus reportMobileKeyStatusOf(User keyOwner, boolean invalidateForLogin) {
        MobileKey mobileKey = getMobileKeyOfUser(keyOwner);
        if(mobileKey == null) {
            return new WarriorKeyStatus(false, false);
        } else {
            if(invalidateForLogin) {
                mobileKeyService.invalidateForLogin(mobileKey);
            }
            return new WarriorKeyStatus(true, mobileKey.isValid());
        }
    }
}
