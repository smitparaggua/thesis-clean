package com.csg.blade.dao;

import com.csg.blade.domain.UnlinkKey;

public interface UnlinkKeyDao extends ParentDao<UnlinkKey> {
    void delete(UnlinkKey unlinkKey);
}
