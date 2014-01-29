package com.csg.warrior.android;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.text.InputType;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;



import android.widget.Toast;

import com.csg.warrior.android.persistence.DatabaseHandler;
import com.csg.warrior.android.R;
import com.csg.warrior.android.domain.MobileKey;
import com.csg.warrior.android.exception.FailedUploadException;
import com.csg.warrior.android.network.HttpPOSTHelper;


public class SettingsActivity extends Activity {
    private static final int FILE_CHOOSER_REQUEST_CODE = 1;
    public static final String RESULT_KEY_NEW_SETTINGS = "newSettings";
    private static String uniqueID = null;
	private static final String PREF_UNIQUE_ID = "PREF_UNIQUE_ID";

    private MobileKey currentMobileKeySettings;
    private TextView addressBarView;
    private TextView associatedKeyView;
    private EditText keyOwnerView;
    private String password;
    private SharedPreferences sharedPref;
    private String response;
    
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
    	/*
    	 * TODO: loading screen habang hinihintay yung key :))
    	 */
    	
    	Log.i("DAN", "Clicked request button!");
 
    	
    	
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setTitle("Input Password");

    	// Set up the input
    	final EditText input = new EditText(this);
    	// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
    	input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
    	builder.setView(input);

    	// Set up the buttons
    	builder.setPositiveButton("OK", new DialogInterface.OnClickListener() { 
    	    @Override
    	    public void onClick(DialogInterface dialog, int which) {
    	    	String username = keyOwnerView.getText().toString();
    	        password = input.getText().toString();
    	        String url = addressBarView.getText().toString();
    	        url = url + "/warrior/key-request";
    	    	sharedPref = PreferenceManager.getDefaultSharedPreferences(SettingsActivity.this);    	
    	    	String device_id = sharedPref.getString("BLADE_UUID", "No BLADE UUID upon installation?");
    	    	
    	    	requestWarriorKey_sendPOST(username, password, device_id, url);
    	    }
    	});
    	builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
    	    @Override
    	    public void onClick(DialogInterface dialog, int which) {
    	        dialog.cancel();
    	    }
    	});

    	builder.show();
    }
    
    private void requestWarriorKey_sendPOST(String username, String password, String device_id, String url) {
    	HttpPOSTHelper httpPOST = new HttpPOSTHelper();
    	httpPOST.addParameter("username", username);
    	httpPOST.addParameter("password", password);
    	httpPOST.addParameter("device_id", device_id);
    	
    	response = "";
    	try {
    		response = httpPOST.sendPOST(url);
    	}
    	catch (FailedUploadException e) {
    		response = "Please check the URL. If the URL is correct, the server might be down or something is wrong with your internet connection.";
    	}
    	
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(response);
		
		builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {}
		});
		
		AlertDialog dialog = builder.create();
		dialog.show();
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
	            	
	            	Toast.makeText(getApplicationContext(),
	                        "You clicked on YES", Toast.LENGTH_SHORT)
	                        .show();
	            }
	        });
	        builder1.setNegativeButton("No",
	                new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int id) {
	                dialog.cancel();
	            	
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
        String url = addressBarView.getText().toString();
     
        currentMobileKeySettings.setKey(response).setUrlForUpload(url).setKeyOwner(keyOwner);

    }

    public void cancelSettings(View clickedButton) {
        setResult(RESULT_CANCELED);
        finish();
    }

    
    public String getIMEI(Context context){

		TelephonyManager mngr = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE); 
		String imei = mngr.getDeviceId();
		Log.i("DAN", imei);
		return imei;

	}
    
    public static String getMACAddress(Context context) {
    	WifiManager manager = (WifiManager) context.getSystemService(context.WIFI_SERVICE);
    	WifiInfo info = manager.getConnectionInfo();
    	String address = info.getMacAddress();
    	Log.i("DAN", address);
    	return address;
	}
    
    

	public synchronized static String id(Context context) {
		if (uniqueID == null) {
		    SharedPreferences sharedPrefs = context.getSharedPreferences(
		            PREF_UNIQUE_ID, Context.MODE_PRIVATE);
		    uniqueID = sharedPrefs.getString(PREF_UNIQUE_ID, null);
		        if (uniqueID == null) {
		            uniqueID = UUID.randomUUID().toString();
		            Editor editor = sharedPrefs.edit();
		            editor.putString(PREF_UNIQUE_ID, uniqueID);
		            editor.commit();
		        }
		}
		
		Log.i("DAN", uniqueID);
		return uniqueID;
	}
	
	public static String SHA256(String text) throws NoSuchAlgorithmException {

	    MessageDigest md = MessageDigest.getInstance("SHA-256");

	    md.update(text.getBytes());
	    byte[] digest = md.digest();

	    return Base64.encodeToString(digest, Base64.DEFAULT);
	}
}
