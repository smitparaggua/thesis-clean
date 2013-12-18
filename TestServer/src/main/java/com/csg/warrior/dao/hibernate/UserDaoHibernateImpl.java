package com.csg.warrior.dao.hibernate;

import com.csg.warrior.dao.UserDao;
import com.csg.warrior.domain.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository("userDao")
public class UserDaoHibernateImpl extends ParentDaoHibernateImpl<User> implements UserDao {

    @Override
    public User getUserByUsername(String username, String website) {
        String hql = "FROM User WHERE username = :username AND website = :website";
        Map<String, Object> queryParameters = new HashMap<String, Object>();
        queryParameters.put("username", username);
        queryParameters.put("website", website);
        return queryUniqueResult(hql, queryParameters);
    }
}
