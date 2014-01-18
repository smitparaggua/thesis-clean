package com.csg.warrior.raydotcom;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class HttpUtils {
    private List<NameValuePair> urlParameters;

    public HttpUtils() {
        urlParameters = new ArrayList<NameValuePair>();
    }

    public String sendPost(String url) {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(url);
            // add header
            post.setHeader("User-Agent", "Mozilla/5.0");
            post.setEntity(new UrlEncodedFormEntity(urlParameters));
            HttpResponse response = client.execute(post);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        }
        catch(Exception e) {
            e.printStackTrace();
            return "An error occurred";
        }
    }

    public void addParameter(String paramName, String paramValue) {
        NameValuePair parameter = new BasicNameValuePair(paramName, paramValue);
        urlParameters.add(parameter);
    }
}
