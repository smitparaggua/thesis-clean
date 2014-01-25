package com.csg.warrior.raydotcom;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.*;
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
            return readResponseAsString(response);
        } catch(Exception e) {
            e.printStackTrace();
            return "An error occurred";
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
