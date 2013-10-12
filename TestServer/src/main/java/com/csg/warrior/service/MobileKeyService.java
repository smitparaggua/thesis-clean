package com.csg.warrior.service;

import com.csg.warrior.domain.MobileKey;
import com.csg.warrior.domain.User;

public interface MobileKeyService {
    void save(MobileKey mobileKey);

    void writeKeyToFile(User owner, MobileKey mobileKey);
}
