package com.csg.warrior.raydotcom.service.impl;

import com.csg.warrior.raydotcom.domain.MobileKey;
import com.csg.warrior.raydotcom.service.MobileKeyService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("mobileKeyService")
public class MobileKeyServiceImpl implements MobileKeyService {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(MobileKey mobileKey) {
        sessionFactory.getCurrentSession().save(mobileKey);
    }
}
