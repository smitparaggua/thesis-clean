package com.csg.warrior.raydotcom.service;

import com.csg.warrior.raydotcom.dao.UserDao;
import com.csg.warrior.raydotcom.domain.User;

public interface UserService {

    void signUp(User user);

    void setUserDao(UserDao userDao);

    void setWarriorService(WarriorService warriorService);
    
    User getUser(String username, String password);
}
