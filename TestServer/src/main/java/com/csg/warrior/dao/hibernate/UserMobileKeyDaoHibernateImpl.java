package com.csg.warrior.dao.hibernate;

import com.csg.warrior.dao.UserMobileKeyDao;
import com.csg.warrior.domain.UserMobileKey;
import org.springframework.stereotype.Repository;

@Repository("userMobileKeyDao")
public class UserMobileKeyDaoHibernateImpl extends ParentDaoHibernateImpl<UserMobileKey> implements UserMobileKeyDao {
}
