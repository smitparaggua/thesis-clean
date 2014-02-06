package com.csg.warrior.dao.hibernate;

import com.csg.warrior.dao.UnlinkKeyDao;
import com.csg.warrior.domain.UnlinkKey;
import org.springframework.stereotype.Repository;

@Repository("unlinkKeyDao")
public class UnlinkKeyDaoHibernateImpl extends ParentDaoHibernateImpl<UnlinkKey>  implements UnlinkKeyDao {
}
