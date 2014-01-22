package com.csg.warrior.service.impl;

import com.csg.warrior.ServerConstants;
import com.csg.warrior.dao.MobileKeyDao;
import com.csg.warrior.dao.UserDao;
import com.csg.warrior.domain.MobileKey;
import com.csg.warrior.domain.User;
import com.csg.warrior.service.UserMobileKeyService;
import com.csg.warrior.service.UserService;
import org.apache.commons.lang.RandomStringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.Inet4Address;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

@Transactional
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired private UserDao userDao;
    @Autowired private MobileKeyDao mobileKeyDao;
    @Autowired private UserMobileKeyService userMobileKeyService;

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
    public String reportKeyLoginStatus(String username, String website, boolean invalidateForLogin) {
        return null;
    }

    private MobileKey getMobileKeyOfUser(String username, String website) {
        User user = userDao.getUser(username, website);
        return userMobileKeyService.getMobileKeyOfUser(user);
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }

//    @Override
//    // TODO create tests for this and finish this
//    public void unlinkMobileKey(String username, String password) throws AuthenticationException, FailedUrlGenerationException {
//        validateCredentials(username, password);
//        URL generatedUrl = generateUnlinkUrl(username);
//    }
//
//    private void validateCredentials(String username, String password) throws AuthenticationException {
//        User user = userDao.getUser(username, website);
//        if (user == null || user.getPassword() != password) {
//            throw new AuthenticationException("Invalid username or password");
//        }
//    }

    // TODO create a better approach for host address
    private URL generateUnlinkUrl(String username) throws FailedUrlGenerationException {
        String unlinkUrlId = RandomStringUtils.randomAlphanumeric(32);
        String hostAddress = getCurrentHostAddress();
        String url = String.format("%s/%s/%s", ServerConstants.UNLINK_MOBILE_KEY_URL, username, unlinkUrlId);
        URL unlinkUrl = assembleUrl(hostAddress, url);
        return unlinkUrl;
    }

    private URL assembleUrl(String hostAddress, String url) throws FailedUrlGenerationException {
        try {
            return new URL(ServerConstants.PROTOCOL, hostAddress, ServerConstants.PORT, url);
        } catch (MalformedURLException e) {
            throw new FailedUrlGenerationException("Invalid protocol in assembling URL", e);
        }
    }

    private String getCurrentHostAddress() throws FailedUrlGenerationException {
        // todo note: this returns LAN address so this have to be changed
        // besides this is different per website
        String address;
        try {
            address = Inet4Address.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new FailedUrlGenerationException("Cannot identify host address", e);
        }
        return address;
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
    public void setUserMobileKeyService(UserMobileKeyService userMobileKeyService) {
    }

    @Override
    public User getUser(String username, String website) {
        return userDao.getUser(username, website);
    }

}
