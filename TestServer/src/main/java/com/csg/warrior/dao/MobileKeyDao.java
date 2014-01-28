package com.csg.warrior.dao;

import com.csg.warrior.domain.MobileKey;
import org.joda.time.DateTime;

public interface MobileKeyDao {
    void updateMobileKeyUploadTime(MobileKey mobileKey, DateTime now);

    void save(MobileKey mobileKey);

    void delete(MobileKey mobileKey);
}
