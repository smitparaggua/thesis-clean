package com.csg.blade.dao;

import com.csg.blade.domain.MobileKey;
import org.joda.time.DateTime;

public interface MobileKeyDao {
    void updateMobileKeyUploadTime(MobileKey mobileKey, DateTime now);

    void save(MobileKey mobileKey);

    void delete(MobileKey mobileKey);
}
