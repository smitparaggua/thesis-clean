package com.csg.blade.dao.hibernate;

import com.csg.blade.dao.MobileKeyDao;
import com.csg.blade.domain.MobileKey;
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
