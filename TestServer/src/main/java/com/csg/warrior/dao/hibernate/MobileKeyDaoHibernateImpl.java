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
public class MobileKeyDaoHibernateImpl implements MobileKeyDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private UserDao userDao;

    @Override
    public MobileKey getMobileKeyByUsername(String username) {
        User user = userDao.getUserByUsername(username);
        return user == null? null :user.getMobileKey();
    }

    @Override
    public void updateMobileKeyUploadTime(MobileKey mobileKey, DateTime uploadTime) {
        mobileKey.setUploadTime(uploadTime);
        sessionFactory.getCurrentSession().merge(mobileKey);
    }
}
