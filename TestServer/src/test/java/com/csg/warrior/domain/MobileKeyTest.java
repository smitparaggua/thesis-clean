package com.csg.warrior.domain;

import com.csg.warrior.KeyStringGenerator;
import org.joda.time.DateTime;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MobileKeyTest {
    MobileKey mobileKey = new MobileKey();

    @Test
    public void expiredKey() {
        DateTime uploadTime = DateTime.now();
        uploadTime = uploadTime.minusSeconds(MobileKey.KEY_EXPIRE_SECONDS + 1);
        mobileKey.setUploadTime(uploadTime);
        assertTrue(mobileKey.isExpired());
    }

    @Test
    public void recentUploadTime() {
        mobileKey.setUploadTime(DateTime.now());
        assertFalse(mobileKey.isExpired());
    }

    @Test
    public void nullUploadTime() {
        mobileKey.setUploadTime(null);
        assertFalse(mobileKey.isValid());
    }

    @Test
    public void failUninitializedKeyString() {
         assertFalse(mobileKey.isValid());
    }

    @Test
    public void failNullKeyString() {
        mobileKey.setKeyString(null);
        assertFalse(mobileKey.isValid());
    }

    @Test
    public void invalidateEmptyKeyString() {
        mobileKey.setKeyString("");
        assertFalse(mobileKey.isValid());
    }

    @Test
    public void acceptOnlyKeysWithCorrectLength() {
        KeyStringGenerator generator = new KeyStringGenerator();
        String generatedKeyString = generator.generateKeyString();
        mobileKey.setKeyString(generatedKeyString);
        assertTrue("Generated key's length: " + generatedKeyString.length(), mobileKey.isValid());
        mobileKey.setKeyString("a");
        assertFalse("Too short key", mobileKey.isValid());
        String keyString = generatedKeyString + "a";
        mobileKey.setKeyString(keyString);
        assertFalse("Too long key", mobileKey.isValid());
    }
}
