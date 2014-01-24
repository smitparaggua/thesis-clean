//dummy commit

package com.csg.warrior.GCM;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.csg.warrior.ToastUtils;
import com.csg.warrior.persistence.DatabaseHandler;

public class KeyReceiver extends BroadcastReceiver {
	
	@Override
	public void onReceive (Context context, Intent intent){
		String username = intent.getStringExtra("username");
		String website = intent.getStringExtra("website");
		String warriorkey = intent.getStringExtra("warriorkey");
		
		
		//dito ilalagay yung username, url and key
		
		DatabaseHandler db = new DatabaseHandler(context);
		
		db.addData(username, website, warriorkey);
		
		Log.i("DAN KeyReceiver", "GCM success! Received message: "
								+ username + " " + website + " " + warriorkey);
	}
}
