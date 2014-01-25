package com.csg.warrior;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;



import android.widget.Toast;

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
    	
    	Log.i("DAN", "Clicked request button!");
    	//TODO: resolve these variables
    	//String keyOwner = keyOwnerView.getText().toString(); -> para sa username
        //dito ifefetch yung key 
        //
         //String url = addressBarView.getText().toString(); -> para sa password
    	//TODO: pop-up prompt for password
    	String username = "ray";
    	String password = "pass"; 
    	String url = "http://172.16.1.117:8080/raydotcom/warrior/key-request";
    	
    	//TODO: pop up pag pinindot yung request "Request WAR Key for username ray at website http://172.16.1.117 ?" [yes,no]
    	
    	String device_id = "APA91bEFG_TmtG_iFlNJ842Y8uaonnoGD29zKor7rykQg0D6XSlTXzLbWAZdvjisgJOTtHpkJ9J5hT6Mzmr3xMIwKwHoOim8tdLJP_xRnsjjtsswP05CwGbripGkeFWPTxiWS8wXbDD_o4x_4B8ATbZeBzy-y_r_VQ";
    	
    	HttpPOSTHelper httpPOST = new HttpPOSTHelper();
    	httpPOST.addParameter("username", username);
    	httpPOST.addParameter("password", password);
    	httpPOST.addParameter("device_id", device_id);
    	
    	String response = "";
    	try {
    		response = httpPOST.sendPOST(url);
    		Log.i("DAN.SettingsActivity.requestWarriorKey", "7Received key: " + response);
    	}
    	catch (FailedUploadException e) {
    		Log.i("DAN.SettingsActivity.requestWarriorKey", e.getMessage());
    	}
    	
    	//TODO: handling of http response
    }
    
    

    public void saveSettings(View clickedButton) {
        // TODO check if some values are not filled
        final DatabaseHandler dbHandler = new DatabaseHandler(this);
        fetchSettingsFromView();
      
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Are you sure?");
	        builder1.setCancelable(false);
	        builder1.setPositiveButton("Yes",
	                new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int id) {
	            	if (currentMobileKeySettings.isTransient()) {
	                    dbHandler.addMobileKey(currentMobileKeySettings);
	                } else {
	                    dbHandler.updateMobileKey(currentMobileKeySettings);
	                }
	            	Intent resultIntent = new Intent();
	                resultIntent.putExtra(RESULT_KEY_NEW_SETTINGS, currentMobileKeySettings);
	                setResult(RESULT_OK);
	            	finish();
	               // dialog.cancel();
	            	Toast.makeText(getApplicationContext(),
	                        "You clicked on YES", Toast.LENGTH_SHORT)
	                        .show();
	            }
	        });
	        builder1.setNegativeButton("No",
	                new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int id) {
	                //dialog.cancel();
	            	Toast.makeText(getApplicationContext(),
	                        "You clicked on NO", Toast.LENGTH_SHORT)
	                        .show();
	                dialog.cancel();
	            }
	        });

        AlertDialog alert11 = builder1.create();
        alert11.show();
        
	     
        
        
        
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
