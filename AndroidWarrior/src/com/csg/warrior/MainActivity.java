package com.csg.warrior;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private static final int SETTINGS_REQUEST_CODE = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void toSettingsActivity(View clickedButton) {
        Intent settingsIntent = new Intent(this, SettingsActivity.class);
        this.startActivityForResult(settingsIntent, SETTINGS_REQUEST_CODE);
    }

    public void uploadKey(View clickedButton) {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost httpPostRequest = createHttpPostRequest();
            httpPostRequest.setEntity(createKeyFormEntity());
            String serverReply = client.execute(httpPostRequest, new BasicResponseHandler());
            showPrompt(serverReply);
        } catch (ClientProtocolException e) {
            showPrompt("Bad URL");
        } catch (IOException e) {
            showPrompt("Connection Error");
        } catch (Exception e) {
            showPrompt(e.getClass().toString());
        }
    }

    private HttpPost createHttpPostRequest() {
        EditText editText = (EditText) findViewById(R.id.address_bar);
        Editable inputUrl = editText.getText();
        String urlString = inputUrl == null ? null : inputUrl.toString();
        return new HttpPost(urlString);
    }

    private UrlEncodedFormEntity createKeyFormEntity() throws UnsupportedEncodingException {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("key", "123"));
        params.add(new BasicNameValuePair("username", "user"));
        return new UrlEncodedFormEntity(params, "UTF-8");
    }

    private void showPrompt(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {

        }
    }
}
