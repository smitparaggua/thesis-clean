package com.csg.warrior.domain;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class UserResetUrl {
    @Id
    private Long id;
    @OneToOne
    private User user;
    private String url;
    @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    private DateTime linkGenerationTime;

    public UserResetUrl(User user, String url) {
        this.user = user;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DateTime getLinkGenerationTime() {
        return linkGenerationTime;
    }

    public void setLinkGenerationTime(DateTime linkGenerationTime) {
        this.linkGenerationTime = linkGenerationTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserResetUrl userResetUrl1 = (UserResetUrl) o;

        if (!url.equals(userResetUrl1.url)) return false;
        if (!user.equals(userResetUrl1.user)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = user.hashCode();
        result = 31 * result + url.hashCode();
        return result;
    }
}
