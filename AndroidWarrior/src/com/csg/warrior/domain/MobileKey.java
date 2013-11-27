package com.csg.warrior.domain;

import android.content.Context;
import android.widget.Toast;
import com.csg.warrior.exception.InvalidAssociatedFileException;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MobileKey implements Serializable {
    private long databaseId;
    private String keyOwner;
    private File associatedFile;
    private String urlForUpload;

    public MobileKey setDatabaseId(long id) {
        this.databaseId = id;
        return this;
    }

    public MobileKey setKeyOwner(String username) {
        this.keyOwner = username;
        return this;
    }

    public MobileKey setAssociatedFile(File associatedFile) {
        this.associatedFile = associatedFile;
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

    public File getAssociatedFile() {
        return associatedFile;
    }

    public String getUrlForUpload() {
        return urlForUpload;
    }

    public String getAssociatedFilePath() {
        if (associatedFile != null) {
            return associatedFile.getPath();
        }
        return null;
    }

    // TODO not sure if this is reliable way to determine if the value is not yet saved in the database
    public boolean isTransient() {
        return databaseId == 0;
    }
}
