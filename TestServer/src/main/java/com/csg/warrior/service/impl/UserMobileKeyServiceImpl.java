package com.csg.warrior.service.impl;

import com.csg.warrior.dao.UserMobileKeyDao;
import com.csg.warrior.domain.MobileKey;
import com.csg.warrior.domain.User;
import com.csg.warrior.domain.UserMobileKey;
import com.csg.warrior.service.MobileKeyService;
import com.csg.warrior.service.UserMobileKeyService;
import com.csg.warrior.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("userMobileKeyService")
public class UserMobileKeyServiceImpl implements UserMobileKeyService {
    @Autowired private UserService userService;
    @Autowired private MobileKeyService mobileKeyService;
    @Autowired private UserMobileKeyDao userMobileKeyDao;

    @Override
    public void save(User user, MobileKey mobileKey) {
        userService.save(user);
        mobileKeyService.save(mobileKey);
        userMobileKeyDao.save(new UserMobileKey(user, mobileKey));
    }

    @Override
    public MobileKey getMobileKeyOfUser(User user) {
        String hql = "FROM UserMobileKey WHERE user := user";
        Map<String, Object> queryParameters = new HashMap<String, Object>();
        queryParameters.put("user", user);
        UserMobileKey userMobileKey = userMobileKeyDao.queryUniqueResult(hql, queryParameters);
        return userMobileKey.getMobileKey();
    }
}
