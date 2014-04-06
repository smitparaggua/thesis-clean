package com.csg.blade.raydotcom.dao;

import com.csg.blade.raydotcom.domain.User;

public interface UserDao {
    void save(User user);

    User getUserByUsername(String username);
}
