package com.csg.warrior.dao;

import com.csg.warrior.domain.User;

public interface UserDao extends ParentDao<User> {
    User getUser(String username, String website);
}
