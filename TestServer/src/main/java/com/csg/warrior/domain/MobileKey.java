package com.csg.warrior.domain;

import com.csg.warrior.KeyStringGenerator;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.Duration;

import javax.persistence.*;

@Entity
public class MobileKey {
    static final int KEY_EXPIRE_SECONDS = 60;
    public static final int KEY_STRING_LENGTH = 2048;

    @Id
    @GeneratedValue
    private Long id;
    @Column(length = KEY_STRING_LENGTH)
    private String keyString;
    @Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
    private DateTime uploadTime;

    public MobileKey() {}

    public MobileKey(String keyString) {
        this.keyString = keyString;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKeyString() {
        return keyString;
    }

    public void setKeyString(String keyString) {
        this.keyString = keyString;
    }

    public DateTime getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(DateTime uploadTime) {
        this.uploadTime = uploadTime;
    }

    @Transient
    public boolean isExpired() {
        DateTime currentTime = DateTime.now();
        Duration keyUploadDuration = new Duration(uploadTime, currentTime);
        return keyUploadDuration.getStandardSeconds() >= KEY_EXPIRE_SECONDS || uploadTime == null;
    }

    @Override
    public String toString() {
        return "MobileKey{" + "keyString='" + keyString + "', uploadTime=" + uploadTime + '}';
    }

    public static MobileKey generateKey() {
        MobileKey generatedKey = new MobileKey(KeyStringGenerator.generateKeyString());
        return generatedKey;
    }

    public boolean isValid() {
        return keyString != null && keyString.length() == KEY_STRING_LENGTH;
    }
}
