package com.csg.warrior;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.csg.warrior.domain.MobileKey;
import com.csg.warrior.exception.FailedUploadException;
import com.csg.warrior.network.HttpPOSTHelper;
import com.csg.warrior.persistence.DatabaseHandler;

import java.io.File;

public class SettingsActivity extends Activity {
    private static final int FILE_CHOOSER_REQUEST_CODE = 1;
    public static final String RESULT_KEY_NEW_SETTINGS = "newSettings";

    private MobileKey currentMobileKeySettings;
    private TextView addressBarView;
    private TextView associatedKeyView;
    private EditText keyOwnerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_view);
        currentMobileKeySettings = (MobileKey) getIntent().getSerializableExtra(MainActivity.EXTRAS_KEY_MOBILE_KEY);
        initializeViewValues(currentMobileKeySettings);
    }

    private void initializeViewValues(MobileKey currentMobileKeySettings) {
        if (currentMobileKeySettings != null) {
            keyOwnerView = (EditText) findViewById(R.id.key_owner);
            keyOwnerView.setText(currentMobileKeySettings.getKeyOwner());
            addressBarView = (TextView) findViewById(R.id.address_bar);
            addressBarView.setText(currentMobileKeySettings.getUrlForUpload());
       
            associatedKeyView = (TextView) findViewById(R.id.associated_file_name);
            associatedKeyView.setText(currentMobileKeySettings.getKey());
        }
    }

    public void requestWarriorKey(View clickedButton) {
    	//send (user, pass, device_id) to ray.com
    	
    	/*
    	 * TODO: detect if enrolled na sa warrior (may entry na sa database) --> pop-up [yes,no] for key regeneration
    	 * 
    	 * DatabaseHandler db = new DatabaseHandler(this);
    	 * db.doesUsernameExist(username) -> nirereturn niya kung may laman na or wala;
    	 *
    	 * TODO: loading screen habang hinihintay yung key :))
    	 */
    	
    	//TODO: resolve these variables
    	String username = null;
    	String password = null;
    	String url = "localhost:8080/settings/key-request";
    	String gcm_device_id = null;
    	
    	HttpPOSTHelper httpPOST = new HttpPOSTHelper();
    	httpPOST.addParameter("username", username);
    	httpPOST.addParameter("password", password);
    	httpPOST.addParameter("gcm_device_id", gcm_device_id);
    	try {
    		httpPOST.sendPOST(url);
    	}
    	catch (FailedUploadException e) {
    		ToastUtils.showPromptLong(this, e.getMessage());
    	}
    	
    	//TODO: handling of http response
    	
    	
    }
    
    

    public void saveSettings(View clickedButton) {
        // TODO check if some values are not filled
        DatabaseHandler dbHandler = new DatabaseHandler(this);
        fetchSettingsFromView();
        if (currentMobileKeySettings.isTransient()) {
            dbHandler.addMobileKey(currentMobileKeySettings);
        } else {
            dbHandler.updateMobileKey(currentMobileKeySettings);
        }
        Intent resultIntent = new Intent();
        resultIntent.putExtra(RESULT_KEY_NEW_SETTINGS, currentMobileKeySettings);
        setResult(RESULT_OK);
        finish();
    }

    // TODO Check for empty attributes
    private void fetchSettingsFromView() {
        String keyOwner = keyOwnerView.getText().toString();
        //dito ifefetch yung key
        String key = "lalalla";
      
        String url = addressBarView.getText().toString();
        currentMobileKeySettings.setKey(key).setUrlForUpload(url).setKeyOwner(keyOwner);
    }

    public void cancelSettings(View clickedButton) {
        setResult(RESULT_CANCELED);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case FILE_CHOOSER_REQUEST_CODE:
                  //  TextView associatedFileLabel = (TextView) findViewById(R.id.associated_file_name);
                  //  associatedFileLabel.setText(data.getStringExtra("selectedFilePath"));
            }
        }
    }
}
