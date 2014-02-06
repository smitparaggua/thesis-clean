package com.csg.warrior.dao;

import com.csg.warrior.domain.UnlinkKey;

public interface UnlinkKeyDao extends ParentDao<UnlinkKey> {
    void delete(UnlinkKey unlinkKey);
}
