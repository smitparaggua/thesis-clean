package com.csg.warrior.raydotcom.service;

import com.csg.warrior.raydotcom.domain.User;

public interface UserService {

    void save(User user);

    void signUpToWarrior(User user);
}
