package com.csg.warrior.raydotcom;

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
            HttpResponse response = sendPostRequest(url);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        } catch(Exception e) {
            e.printStackTrace();
            return "An error occurred";
        }
    }

    public HttpResponse sendPostRequest(String url) throws IOException {
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        // add header
        post.setHeader("User-Agent", "Mozilla/5.0");
        post.setEntity(new UrlEncodedFormEntity(urlParameters));
        return client.execute(post);
    }

    public Object receiveReplyAsObject(HttpResponse response) throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream = new ObjectInputStream(response.getEntity().getContent());
        return inputStream.readObject();

    }

    public void addParameter(String paramName, String paramValue) {
        NameValuePair parameter = new BasicNameValuePair(paramName, paramValue);
        urlParameters.add(parameter);
    }
}
