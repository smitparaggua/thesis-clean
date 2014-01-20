//dummy commit

package com.csg.warrior.GCM;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import com.csg.warrior.ToastUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;

public final class GCMUtilities {
	static final String SERVER_URL = null;
	static final String SENDER_ID = "41226186172";
	static final String SHAREDPREF_regID = "registration_ID";
	static final String TAG = "DAN";
	
	//hindi ko alam ginagawa nito hahahahahaha
	static final int PLAY_SERVICES_RESOLUTION_REQUEST = 99999;
	
	public static boolean checkPlayServices(Activity activity, Context context) {
		int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);
	    if (resultCode != ConnectionResult.SUCCESS) {
	        if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
	            GooglePlayServicesUtil.getErrorDialog(resultCode, activity,
	                    PLAY_SERVICES_RESOLUTION_REQUEST).show();
	        } 
	        else {
	            Log.i(TAG + "GCMUtilities.checkPlayServices()", "This device is not supported.");
	        }
	        return false;
	    }
	    return true;
	}
	
	public static void registerInBackground(final Context context) {
		new AsyncTask<Void, Void, Void>() {
			@Override
			protected Void doInBackground(Void... params){
				try {
					GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(context);
					String regID = gcm.register(SENDER_ID);
					sendRegID(regID);
					storeRegID(context, regID);
				}
				catch (Exception e) {
					//TODO: next time na 'tong exception handling hahahahaha
				}
				return null;
			}
		}.execute(null, null, null);
	}
	
	private static void sendRegID(String regID){
		//TODO: implement on server		
		Log.i(TAG+".sendRegID",regID);
		
		
	}
	
	private static void storeRegID(Context context, String regID){
		final SharedPreferences prefs = getGCMPreferences(context);
		SharedPreferences.Editor editor = prefs.edit();
	    editor.putString(SHAREDPREF_regID, regID);
	    editor.commit();
	}
	
	private static SharedPreferences getGCMPreferences(Context context){
		return context.getSharedPreferences("warriorGCMregID",
	            Context.MODE_PRIVATE);
	}
	
	public static String getRegistrationID(Context context){
		final SharedPreferences prefs = getGCMPreferences(context);
	    String regID = prefs.getString(SHAREDPREF_regID, "");
	    if (regID.isEmpty()) {
	        Log.i("GCMUtilities.getRegistrationID()", "Registration not found.");
	        return "";
	    }
		return regID;
	}
	
	
}
