package com.csg.warrior.dao.hibernate;

import com.csg.warrior.dao.MobileKeyDao;
import com.csg.warrior.dao.UserDao;
import com.csg.warrior.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository("userDao")
public class UserDaoHibernateImpl extends ParentDaoHibernateImpl<User> implements UserDao {
    @Autowired MobileKeyDao mobileKeyDao;

    @Override
    public void save(User user) {
        if(user.isMobileKeyTransient()) {
            mobileKeyDao.save(user.getMobileKey());
        }
        super.save(user);
    }

    @Override
    public User getUser(String username, String website) {
        String hql = "FROM User WHERE username = :username AND website = :website";
        Map<String, Object> queryParameters = new HashMap<String, Object>();
        queryParameters.put("username", username);
        queryParameters.put("website", website);
        return queryUniqueResult(hql, queryParameters);
    }
}
