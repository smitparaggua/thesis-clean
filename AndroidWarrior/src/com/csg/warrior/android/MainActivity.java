//testing out git!!!!1
//dummy commit

package com.csg.warrior.android;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.csg.warrior.android.domain.MobileKey;
import com.csg.warrior.android.persistence.DatabaseHandler;
import com.csg.warrior.android.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class MainActivity extends ListActivity {
	
    private static final int SETTINGS_REQUEST_CODE = 1;
    public static final String EXTRAS_KEY_MOBILE_KEY = "mobileKey";

    private MobileKeyAdapter adapter;
    private DatabaseHandler db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
    	boolean isFirstRun = sharedPref.getBoolean("FIRSTRUN", true);
    	if (isFirstRun)
    	{
    		String android_id = getMACAddress(this) + getIMEI(this);
            
            
            try{
            	
            	android_id = SHA256(android_id);
            	Log.i("DAN", "First install, generating BLADE UUID");
        		SharedPreferences.Editor editor = sharedPref.edit();
        		editor.putString("BLADE_UUID",  android_id);
        		
        	    editor.putBoolean("FIRSTRUN", false);
        	    editor.commit();
            
            }catch(Exception e){ }
    	    // generate BLADE UUID
    		
    	}    	
        super.onCreate(savedInstanceState);
        db = new DatabaseHandler(this);
        createAddButtonFooter();
        showMobileKeys();
    }
    
    @Override
    public void onResume() {
    	super.onResume();	
    }

    private void createAddButtonFooter() {
        LayoutInflater layoutInflater = getLayoutInflater();
        ListView listView = getListView();
        listView.addFooterView(layoutInflater.inflate(R.layout.mobile_key_list_footer, null));
    }

    private void showMobileKeys() {
        List<MobileKey> mobileKeys = retrieveMobileKeys();
        adapter = new MobileKeyAdapter(this, R.layout.mobile_key_list, mobileKeys);
        this.setListAdapter(adapter);
    }

    private List<MobileKey> retrieveMobileKeys() {
    	return db.getMobileKeys();    	
    }

    @Override
    protected void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);
        MobileKey mobileKey = adapter.getItem(position);
        callSettingsActivity(mobileKey);
    }

    public void addNewMobileKey(View clickedButton) {
        // TODO Add new mobile key
        MobileKey newKey = new MobileKey();
        callSettingsActivity(newKey);
    }

    private void callSettingsActivity(MobileKey mobileKey) {
        Intent settingsIntent = new Intent(this, SettingsActivity.class);
        settingsIntent.putExtra("mobileKey", mobileKey);
        this.startActivityForResult(settingsIntent, SETTINGS_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case SETTINGS_REQUEST_CODE:
                    showMobileKeys();
                    break;
            }
        }
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
    
    public static String SHA256(String text) throws NoSuchAlgorithmException {

	    MessageDigest md = MessageDigest.getInstance("SHA-256");

	    md.update(text.getBytes());
	    byte[] digest = md.digest();

	    return Base64.encodeToString(digest, Base64.DEFAULT);
	}
    
    
}
