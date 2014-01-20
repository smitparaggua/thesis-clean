//testing out git!!!!1
//dummy commit

package com.csg.warrior;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.csg.warrior.domain.MobileKey;
import com.csg.warrior.persistence.DatabaseHandler;
import com.csg.warrior.GCM.*;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.util.List;

public class MainActivity extends ListActivity {
	
	//GCM variables

    
	//end GCM variables
	
    private static final int SETTINGS_REQUEST_CODE = 1;
    public static final String EXTRAS_KEY_MOBILE_KEY = "mobileKey";

    private MobileKeyAdapter adapter;
    private DatabaseHandler db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        
        //GCM stuff        
        
        Activity activity = this;
        Context context = this;
        if (GCMUtilities.checkPlayServices(activity, context)){
        	String regID = GCMUtilities.getRegistrationID(context);        	
        	if(regID.isEmpty()) {
        		Log.i("MainActivity.onCreate", "Commencing registration");
        		ToastUtils.showPromptLong(context,"Commencing registration");
        		GCMUtilities.registerInBackground(context);        		
        	}
        }
        else finish(); //finish kagad ba kapag walang appropriate Google Play Services APK?
		
        //end GCM stuff

        db = new DatabaseHandler(this);
        createAddButtonFooter();
        showMobileKeys();
    }
    
    @Override
    public void onResume() {
    	super.onResume();
    	
    	Activity activity = this;
        Context context = getApplicationContext();
    	if (!GCMUtilities.checkPlayServices(activity, context)){
        	finish(); //finish kagad ba kapag walang appropriate Google Play Services APK?
        }
    	
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
    
    
}
