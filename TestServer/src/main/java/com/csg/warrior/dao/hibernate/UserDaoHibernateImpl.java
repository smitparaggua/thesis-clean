package com.csg.warrior.dao.hibernate;

import com.csg.warrior.dao.UserDao;
import com.csg.warrior.domain.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoHibernateImpl implements UserDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User getUserByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM User WHERE username = :username";
        Query query = session.createQuery(hql);
        query.setParameter("username", username);
        return null;
    }
}
