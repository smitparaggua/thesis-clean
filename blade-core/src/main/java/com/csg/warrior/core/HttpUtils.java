package com.csg.warrior.core;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class HttpUtils {
    private List<NameValuePair> urlParameters;

    public HttpUtils() {
        urlParameters = new ArrayList<NameValuePair>();
    }

    // todo: separate sending request and receiving response
    public String sendPost(String url) {
        try {
            HttpPost postRequest = createPostRequest(url);
            HttpResponse response = sendRequest(postRequest);
            return check404(response);
        } catch (IOException e) {
            return "BLADE server down";
        } catch(Exception e) {
            e.printStackTrace();
            return "An error occured";
        }
    }

    private String check404(HttpResponse response) {
        if (response.getStatusLine().getStatusCode() == 404) return "BLADE server down";
        else {
            try {
                return readResponseAsString(response);
            }
            catch(Exception e) {
                e.printStackTrace();
                return "An error occured";
            }
        }
    }

    private HttpPost createPostRequest(String url) throws UnsupportedEncodingException {
        HttpPost post = new HttpPost(url);
        post.setHeader("User-Agent", "Mozilla/5.0");
        post.setEntity(new UrlEncodedFormEntity(urlParameters));
        return post;
    }

    private HttpResponse sendRequest(HttpPost request) throws IOException {
        return new DefaultHttpClient().execute(request);
    }

    private String readResponseAsString(HttpResponse response) throws IOException {
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuffer result = new StringBuffer();
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        return result.toString();
    }

    public void addParameter(String paramName, String paramValue) {
        NameValuePair parameter = new BasicNameValuePair(paramName, paramValue);
        urlParameters.add(parameter);
    }
}