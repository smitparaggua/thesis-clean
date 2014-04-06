package com.csg.blade.dao.hibernate;

import com.csg.blade.dao.UnlinkKeyDao;
import com.csg.blade.domain.UnlinkKey;
import org.springframework.stereotype.Repository;

@Repository("unlinkKeyDao")
public class UnlinkKeyDaoHibernateImpl extends ParentDaoHibernateImpl<UnlinkKey>  implements UnlinkKeyDao {
}
