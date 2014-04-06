package com.csg.blade.dao.hibernate;

import com.csg.blade.dao.MobileKeyDao;
import com.csg.blade.dao.UnlinkKeyDao;
import com.csg.blade.dao.UserDao;
import com.csg.blade.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository("userDao")
public class UserDaoHibernateImpl extends ParentDaoHibernateImpl<User> implements UserDao {
    @Autowired MobileKeyDao mobileKeyDao;
    @Autowired UnlinkKeyDao unlinkKeyDao;

    @Override
    public void save(User user) {
        if(user.isMobileKeyTransient()) {
            mobileKeyDao.save(user.getMobileKey());
        }
        super.save(user);
    }

    @Override
    public void delete(User user) {
        mobileKeyDao.delete(user.getMobileKey());
        if(user.getUnlinkKey() != null) unlinkKeyDao.delete(user.getUnlinkKey());
        super.delete(user);
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
