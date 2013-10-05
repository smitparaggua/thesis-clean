package com.csg.warrior.dao;

import com.csg.warrior.domain.User;

public interface UserDao {
    User getUserByUsername(String username);

    void merge(User user);
}
