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

import javax.naming.AuthenticationException;

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
        if (user == null) {
            throw new UsernameNotFoundException("Invalid email or password");
        }
        UserDetails springSecurityUser = user.toSpringSecurityUser();
        user.invalidateUploadedKey();
        userDao.merge(user);
        return springSecurityUser;
    }

    // TODO: adjust to take care of hashes
    @Override
    public boolean updateMobileKey(String username, String keyUploaded) {
        MobileKey mobileKey = mobileKeyDao.getMobileKeyByUsername(username);
        if (mobileKey.getKeyString().equals(keyUploaded)) {
            mobileKeyDao.updateMobileKeyUploadTime(mobileKey, DateTime.now());
            return true;
        }
        return false;
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    // TODO create tests for this and finish this
    public void unlinkMobileKey(String username, String password) throws AuthenticationException {
        validateCredentials(username, password);
    }

    private void validateCredentials(String username, String password) throws AuthenticationException {
        User user = userDao.getUserByUsername(username);
        if(user == null || user.getPassword() != password) {
            throw new AuthenticationException("Invalid username or password");
        }
    }
}
