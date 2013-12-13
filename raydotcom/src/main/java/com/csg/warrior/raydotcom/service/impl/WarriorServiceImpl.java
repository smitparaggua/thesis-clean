package com.csg.warrior.raydotcom.service.impl;

import com.csg.warrior.raydotcom.service.WarriorService;
import org.springframework.stereotype.Service;

@Service("warriorService")
public class WarriorServiceImpl implements WarriorService {
    @Override
    public boolean isUserLocked(String username) {
        return false;
    }
}
