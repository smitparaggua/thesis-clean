package com.csg.blade.service.impl;

import com.csg.blade.dao.MobileKeyDao;
import com.csg.blade.domain.MobileKey;
import com.csg.blade.service.MobileKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// TODO Refactor save method for this and UserService
@Service("mobileKeyService")
public class MobileKeyServiceImpl implements MobileKeyService {
    @Autowired
    private MobileKeyDao mobileKeyDao;

    @Override
    public void invalidateForLogin(MobileKey mobileKey) {
        mobileKeyDao.updateMobileKeyUploadTime(mobileKey, null);
    }
}
