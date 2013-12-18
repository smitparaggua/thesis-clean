package com.csg.warrior.dao.hibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class ParentDaoHibernateImpl<T> {
    @Autowired
    private SessionFactory sessionFactory;

    public void merge(T objectToMerge) {
        getCurrentSession().merge(objectToMerge);
    }

    public void save(T objectToSave) {
        getCurrentSession().save(objectToSave);
    }

    public T queryUniqueResult(String hql, Map<String, Object> queryParameters) {
        Session session = getCurrentSession();
        Query query = session.createQuery(hql);
        addQueryParameters(query, queryParameters);
        return (T) query.uniqueResult();
    }

    private void addQueryParameters(Query query, Map<String, Object> queryParameters) {
        for(Map.Entry<String, Object> parameter : queryParameters.entrySet()) {
            query.setParameter(parameter.getKey(), parameter.getValue());
        }
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
