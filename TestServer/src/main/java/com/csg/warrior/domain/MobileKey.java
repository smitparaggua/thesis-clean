package com.csg.warrior.domain;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.Duration;

import javax.persistence.*;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.SecureRandom;

@Entity
public class MobileKey {
    private static final long KEY_EXPIRE_SECONDS = 60;

    @Id
    @GeneratedValue
    private Long id;
    @Column(length = 2048)
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
    public boolean isValid() {
        DateTime currentTime = DateTime.now();
        Duration keyUploadDuration = new Duration(uploadTime, currentTime);
        return keyUploadDuration.getStandardSeconds() < KEY_EXPIRE_SECONDS && uploadTime != null;
    }

    @Override
    public String toString() {
        return "MobileKey{" + "keyString='" + keyString + "', uploadTime=" + uploadTime + '}';
    }

    public static MobileKey generateKey() {
        try {

            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
            keyGen.initialize(2048, random);
            KeyPair pair = keyGen.generateKeyPair();
            PrivateKey priv = pair.getPrivate();

            //print as hex string
            byte[] publicKey = priv.getEncoded();
            StringBuffer retString = new StringBuffer();
            for (int i = 0; i < publicKey.length; ++i) {
                retString.append(Integer.toHexString(0x0100 + (publicKey[i] & 0x00FF)).substring(1));
            }
            return new MobileKey(retString.toString());
        } catch (Exception e) {
            System.err.println("Caught exception " + e.toString());
            return null;
        }
    }
}
