package com.csg.blade.domain;

import org.apache.commons.lang.RandomStringUtils;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class UnlinkKey {
    @Id
    @GeneratedValue
    private Long id;
    private String keyString;
    @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    private DateTime linkGenerationTime;

    private static final int UNLINK_URL_LENGTH = 32;

    @Transient
    public static UnlinkKey generateKey() {
        UnlinkKey key = new UnlinkKey();
        key.setKeyString(RandomStringUtils.randomAlphanumeric(UNLINK_URL_LENGTH));
        key.setLinkGenerationTime(new DateTime());
        return key;
    }

    private Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getKeyString() {
        return keyString;
    }

    private void setKeyString(String key) {
        this.keyString = key;
    }

    private DateTime getLinkGenerationTime() {
        return linkGenerationTime;
    }

    private void setLinkGenerationTime(DateTime linkGenerationTime) {
        this.linkGenerationTime = linkGenerationTime;
    }
}
