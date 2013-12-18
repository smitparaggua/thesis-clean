package com.csg.warrior.dao.hibernate;

import com.csg.warrior.dao.MobileKeyDao;
import com.csg.warrior.dao.UserDao;
import com.csg.warrior.domain.MobileKey;
import com.csg.warrior.domain.User;
import org.hibernate.SessionFactory;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("mobileKeyDao")
public class MobileKeyDaoHibernateImpl extends ParentDaoHibernateImpl<MobileKey> implements MobileKeyDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void updateMobileKeyUploadTime(MobileKey mobileKey, DateTime uploadTime) {
        mobileKey.setUploadTime(uploadTime);
        sessionFactory.getCurrentSession().merge(mobileKey);
    }
}
