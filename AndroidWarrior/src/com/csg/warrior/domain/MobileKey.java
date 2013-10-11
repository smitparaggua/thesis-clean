package com.csg.warrior.domain;

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

public class MobileKey {
    private String keyOwner;
    private File associatedFile;
    private String urlForUpload;

    public MobileKey setKeyOwner(String username) {
        this.keyOwner = username;
        return this;
    }

    public MobileKey setAssociatedFilePath(File associatedFile) {
        this.associatedFile = associatedFile;
        return this;
    }

    public MobileKey setUrlForUpload(String url) {
        this.urlForUpload = url;
        return this;
    }

    public void uploadKey() {
        // TODO Make this asynchronous
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost httpPostRequest = new HttpPost(urlForUpload);
            httpPostRequest.setEntity(createKeyFormEntity());
            String serverReply = client.execute(httpPostRequest, new BasicResponseHandler());
            showPrompt(serverReply);
        } catch (ClientProtocolException e) {
            showPrompt("Bad URL");
        } catch (IOException e) {
            showPrompt("Connection Error");
        } catch (InvalidAssociatedFileException e) {
            showPrompt("Invalid associated file");
        } catch (Exception e) {
            showPrompt(e.getClass().toString());
        }
    }

    private void showPrompt(String message) {
        // TODO Create a way to inform user
    }

    private UrlEncodedFormEntity createKeyFormEntity() throws UnsupportedEncodingException, InvalidAssociatedFileException {
        try {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("key", extractKey()));
            params.add(new BasicNameValuePair("username", keyOwner));
            return new UrlEncodedFormEntity(params, "UTF-8");
        } catch (IOException e) {
            throw new InvalidAssociatedFileException("Caused by IOException: ", e);
        }
    }

    private String extractKey() throws IOException {
        String key;
        BufferedReader reader = new BufferedReader(new FileReader(associatedFile));
        key = reader.readLine();
        return key;
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
}
