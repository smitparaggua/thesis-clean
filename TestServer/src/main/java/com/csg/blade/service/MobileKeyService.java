package com.csg.blade.service;

import com.csg.blade.domain.MobileKey;

public interface MobileKeyService {
    void invalidateForLogin(MobileKey mobileKey);
}
