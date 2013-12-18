package com.csg.warrior.service;

import com.csg.warrior.domain.MobileKey;
import com.csg.warrior.domain.User;

public interface UserMobileKeyService {
    void save(User user, MobileKey mobileKey);

    MobileKey getMobileKeyOfUser(User user);
}
