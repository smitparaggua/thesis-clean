package com.csg.warrior.android.network;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import com.csg.warrior.android.domain.MobileKey;
import com.csg.warrior.android.exception.FailedUploadException;
import com.csg.warrior.android.persistence.DatabaseHandler;

public class DeleteMobileKeyOnClick implements View.OnClickListener {
	private MobileKey mobileKey;
	private Context context;
    private DatabaseHandler dbHandler;

	public DeleteMobileKeyOnClick(MobileKey mobileKey, Context context) {
        this.mobileKey = mobileKey;
        this.context = context;
        this.dbHandler = new DatabaseHandler(context);
    }
	
	@Override
	public void onClick(View view) {
		String username = mobileKey.getKeyOwner();
		//TODO: pop up for password
		String password = mobileKey.getPassword();
		String bladeKey = mobileKey.getKey();
		
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
		String bladeUUID = sharedPref.getString("BLADE_UUID", "No BLADE UUID upon installation?");
		
		requestQuadDelete_sendPOST(username, password, bladeKey, bladeUUID, view);
        dbHandler.deleteMobileKey(mobileKey);
	}
	
	private void requestQuadDelete_sendPOST(String username, String password, String bladeKey, String bladeUUID, View view) {
		HttpPOSTHelper httpPOST = new HttpPOSTHelper();
    	httpPOST.addParameter("username", username);
    	httpPOST.addParameter("password", password);
    	httpPOST.addParameter("bladeKey", bladeKey);
    	httpPOST.addParameter("bladeUUID", bladeUUID);
    	
    	String response = "";
    	try {
    		response = httpPOST.sendPOST(mobileKey.getUrlForUpload() + "/blade/quad-delete");
    	}
    	catch (FailedUploadException e) {
    		response = "Please check the URL. If the URL is correct, the server might be down or something is wrong with your internet connection.";
    	}
    	
    	AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setMessage(response);
		
		builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {}
		});
		
		AlertDialog dialog = builder.create();
		dialog.show();
		
		
		if (response.equals("SUCCESS")) {
			
			dbHandler.deleteData(username, password);
			
			//put delete here
			
		}
		
		
		
	}

	
}
