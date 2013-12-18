package com.csg.warrior.dao;

import com.csg.warrior.domain.User;

public interface UserDao extends ParentDao<User> {
    User getUserByUsername(String username, String website);
}
