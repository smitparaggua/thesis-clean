package com.csg.warrior;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

//    public void uploadKey(View clickedButton) {
//        EditText editText = (EditText) findViewById(R.id.address_bar);
//        HttpClient client = new DefaultHttpClient();
//        HttpPost httpPost = new HttpPost(editText.getText().toString());
//        List<NameValuePair> params = new ArrayList<NameValuePair>();
//        params.add(new BasicNameValuePair("key", "123"));
//        try {
//            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
//            httpPost.setEntity(entity);
//            ResponseHandler<String> handler = new BasicResponseHandler();
////            String content = client.execute(httpPost, handler);
//            TextView bottomTextPanel = (TextView) findViewById(R.id.bottom_text_panel);
////            bottomTextPanel.setText(content);
////        } catch (IOException e) {
////            e.printStackTrace();
//        } catch (Exception e) {
//            Toast toast = new Toast(this);
//            toast.setDuration(Toast.LENGTH_SHORT);
////            toast.setText(e.getClass().toString());
//            toast.setText("hehe");
//            toast.show();
//        }
//    }

    public void uploadKey(View clickedButton) {
        try {
            EditText editText = (EditText) findViewById(R.id.address_bar);
            HttpPost httpPost = new HttpPost(editText.getText().toString());
            HttpClient client = new DefaultHttpClient();
            ResponseHandler<String> handler = new BasicResponseHandler();
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("key", "123"));
            params.add(new BasicNameValuePair("username", "user"));
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
            httpPost.setEntity(entity);
            String content = client.execute(httpPost, handler);
            TextView textView = (TextView) findViewById(R.id.bottom_text_panel);
            textView.setText(content);
        } catch (ClientProtocolException e) {
            showPrompt("Bad URL");
        } catch (IOException e) {
            showPrompt("Connection Error");
        } catch (Exception e) {
            showPrompt(e.getClass().toString());
        }
    }

    private void showPrompt(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}
