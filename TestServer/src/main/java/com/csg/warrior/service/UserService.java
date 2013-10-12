package com.csg.warrior.service;

import com.csg.warrior.domain.User;

public interface UserService {
    public boolean updateMobileKey(String username, String keyUploaded);
    public void save(User user);
}
