package com.csg.blade.raydotcom.service;

import com.csg.blade.core.BladeService;
import com.csg.blade.raydotcom.dao.UserDao;
import com.csg.blade.raydotcom.domain.User;

public interface UserService {

    void signUp(User user);

    void setUserDao(UserDao userDao);

    void setBladeService(BladeService bladeService);
    
    User getUser(String username, String password);
    
    void unlinkMobileOf(User user);
}
