package com.csg.warrior.domain;

import org.joda.time.DateTime;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MobileKeyTest {
    @Test
    public void invalidExpiredKey() {
        DateTime uploadTime = DateTime.now();
        uploadTime = uploadTime.minusSeconds(61);
        MobileKey mobileKey = new MobileKey();
        mobileKey.setUploadTime(uploadTime);
        assertFalse(mobileKey.isValid());
    }

    @Test
    public void validUploadTime() {
        MobileKey mobileKey = new MobileKey();
        mobileKey.setUploadTime(DateTime.now());
        assertTrue(mobileKey.isValid());
    }
}
