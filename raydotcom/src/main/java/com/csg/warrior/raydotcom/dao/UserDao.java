package com.csg.warrior.raydotcom.dao;

import com.csg.warrior.raydotcom.domain.User;

public interface UserDao {
    void save(User user);

    User getUserByUsername(String username);
}
