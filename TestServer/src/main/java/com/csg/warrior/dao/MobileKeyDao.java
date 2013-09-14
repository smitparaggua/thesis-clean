package com.csg.warrior.dao;

import com.csg.warrior.domain.MobileKey;
import org.joda.time.DateTime;

public interface MobileKeyDao {
    MobileKey getMobileKeyByUsername(String username);

    void updateMobileKeyUploadTime(MobileKey mobileKey, DateTime now);
}
