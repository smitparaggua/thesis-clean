package com.csg.warrior.raydotcom.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MobileKey {
    static final int KEY_EXPIRE_SECONDS = 60;
    public static final int KEY_STRING_LENGTH = 2048;

    @Id
    @GeneratedValue
    private Long id;
    @Column(length = KEY_STRING_LENGTH)
    private String keyString;

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

    @Override
    public String toString() {
        return "MobileKey{" + "keyString='" + keyString + "', uploadTime=" + '}';
    }
}
