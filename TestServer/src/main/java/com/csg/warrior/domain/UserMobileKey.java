package com.csg.warrior.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class UserMobileKey {
    @Id
    @GeneratedValue private Long id;
    @ManyToOne private User user;
    @ManyToOne private MobileKey mobileKey;

    public UserMobileKey(User user, MobileKey mobileKey) {
        this.user = user;
        this.mobileKey = mobileKey;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public MobileKey getMobileKey() {
        return mobileKey;
    }

    public void setMobileKey(MobileKey mobileKey) {
        this.mobileKey = mobileKey;
    }
}
