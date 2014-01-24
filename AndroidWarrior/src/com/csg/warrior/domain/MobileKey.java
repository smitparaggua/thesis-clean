package com.csg.warrior.domain;

import java.io.File;
import java.io.Serializable;

public class MobileKey implements Serializable {
    private long databaseId;
    private String keyOwner;
    private String Key;
    private String urlForUpload;

    public MobileKey setDatabaseId(long id) {
        this.databaseId = id;
        return this;
    }

    public MobileKey setKeyOwner(String username) {
        this.keyOwner = username;
        return this;
    }

    public MobileKey setKey(String Key) {
        this.Key = Key;
        return this;
    }

    public MobileKey setUrlForUpload(String url) {
        this.urlForUpload = url;
        return this;
    }

    public long getDatabaseId() {
        return databaseId;
    }

    public String getKeyOwner() {
        return keyOwner;
    }

    public String getKey() {
        return Key;
    }

    public String getUrlForUpload() {
        return urlForUpload;
    }

   

    // TODO not sure if this is reliable way to determine if the value is not yet saved in the database
    public boolean isTransient() {
        return databaseId == 0;
    }
}
