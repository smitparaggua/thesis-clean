package com.csg.warrior.service.impl;

import com.csg.warrior.dao.MobileKeyDao;
import com.csg.warrior.domain.MobileKey;
import com.csg.warrior.domain.User;
import com.csg.warrior.service.MobileKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

// TODO Refactor save method for this and UserService
@Service("mobileKeyService")
public class MobileKeyServiceImpl implements MobileKeyService {
    @Autowired
    private MobileKeyDao mobileKeyDao;

    @Override
    public void save(MobileKey mobileKey) {
        mobileKeyDao.save(mobileKey);
    }

    @Override
    public void invalidateForLogin(MobileKey mobileKey) {
        mobileKeyDao.updateMobileKeyUploadTime(mobileKey, null);
    }
}
