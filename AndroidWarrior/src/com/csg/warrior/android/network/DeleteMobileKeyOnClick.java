package com.csg.warrior.android.network;

import com.csg.warrior.android.SettingsActivity;
import com.csg.warrior.android.domain.MobileKey;
import com.csg.warrior.android.exception.FailedUploadException;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;

public class DeleteMobileKeyOnClick implements View.OnClickListener {
	MobileKey mobileKey;
	Context context;
	
	public DeleteMobileKeyOnClick(MobileKey mobileKey, Context context) {
        this.mobileKey = mobileKey;
        this.context = context;
    }
	
	@Override
	public void onClick(View view) {
		String username = mobileKey.getKeyOwner();
		//TODO: pop up for password
		String password = "q";
		String bladeKey = mobileKey.getKey();
		
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
		String bladeUUID = sharedPref.getString("BLADE_UUID", "No BLADE UUID upon installation?");
		
		requestQuadDelete_sendPOST(username, password, bladeKey, bladeUUID);
	}
	
	private void requestQuadDelete_sendPOST(String username, String password, String bladeKey, String bladeUUID) {
		HttpPOSTHelper httpPOST = new HttpPOSTHelper();
    	httpPOST.addParameter("username", username);
    	httpPOST.addParameter("password", password);
    	httpPOST.addParameter("bladeKey", bladeKey);
    	httpPOST.addParameter("bladeUUID", bladeUUID);
    	
    	String response = "";
    	try {
    		//flag = false;
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
			//TODO: ray padelete sa database nung mobilekey na associated sa button na 'to
			Log.i("DAN DeleteMobileKeyOnClick.requestQuadDelete_sendPOST", "Deleting quad");
		}
		
	}
	

}
