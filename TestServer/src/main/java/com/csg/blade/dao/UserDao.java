package com.csg.blade.dao;

import com.csg.blade.domain.User;

public interface UserDao extends ParentDao<User> {
    User getUser(String username, String website);
    
    void save(User user);
    
    void delete(User user);
}
