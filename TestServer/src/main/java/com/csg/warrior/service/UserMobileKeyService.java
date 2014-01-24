package com.csg.warrior.service;

import com.csg.warrior.core.WarriorKeyStatus;
import com.csg.warrior.domain.MobileKey;
import com.csg.warrior.domain.User;

public interface UserMobileKeyService {
    void save(User user, MobileKey mobileKey);

    MobileKey getMobileKeyOfUser(User user);

    WarriorKeyStatus reportMobileKeyStatusOf(User keyOwner, boolean invalidateForLogin);
}
