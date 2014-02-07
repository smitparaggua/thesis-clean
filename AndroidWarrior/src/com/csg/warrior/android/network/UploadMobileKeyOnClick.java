package com.csg.warrior.android.network;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import com.csg.warrior.android.ToastUtils;
import com.csg.warrior.android.domain.MobileKey;
import com.csg.warrior.android.exception.FailedUploadException;

public class UploadMobileKeyOnClick implements View.OnClickListener{
    MobileKey mobileKey;
    Context context;

    public UploadMobileKeyOnClick(MobileKey mobileKey, Context context) {
        this.mobileKey = mobileKey;
        this.context = context;
    }

    @Override
    public void onClick(View view) {
    	//TODO: resolve these variables
    	//TODO: pop up for password
    	String username = mobileKey.getKeyOwner();
    	String password = "q";
    	String website = mobileKey.getUrlForUpload() + "/blade/quad-delete" ;
    	String bladeKey = mobileKey.getKey();
    	String bladeUUID = "dummy_BLADE_UUID";
    	
        String response = "";
        try {
        	HttpPOSTHelper httpPOST = new HttpPOSTHelper();
        	httpPOST.addParameter("username", username);	//HAHA SI RAY MAY ALAM NITO
        	httpPOST.addParameter("website", website);
        	httpPOST.addParameter("bladeKey",  bladeKey);
        	httpPOST.addParameter("bladeUUID", bladeUUID);
            response = httpPOST.sendPOST(mobileKey.getUrlForUpload());
        } catch (FailedUploadException e) {
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
    }
}
