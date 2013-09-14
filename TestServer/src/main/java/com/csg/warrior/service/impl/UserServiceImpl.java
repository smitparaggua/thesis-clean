package com.csg.warrior.service.impl;

import com.csg.warrior.dao.MobileKeyDao;
import com.csg.warrior.dao.UserDao;
import com.csg.warrior.domain.MobileKey;
import com.csg.warrior.domain.User;
import com.csg.warrior.service.UserService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("userService")
public class UserServiceImpl implements UserDetailsService, UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private MobileKeyDao mobileKeyDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.getUserByUsername(username);
        return user.toSpringSecurityUser();
    }

    // TODO: adjust to take care of hashes
    @Override
    public boolean updateMobileKey(String username, String keyUploaded) {
        MobileKey mobileKey = mobileKeyDao.getMobileKeyByUsername(username);
        System.out.println("Required Key: " + keyUploaded);
        System.out.println("Uploaded Key: " + mobileKey.getKeyUploaded());
        if (mobileKey.getKeyUploaded().equals(keyUploaded)) {
            System.out.println("I was here");
            mobileKeyDao.updateMobileKeyUploadTime(mobileKey, DateTime.now());
            return true;
        }
        return false;
    }
}
