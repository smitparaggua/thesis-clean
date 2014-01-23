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
		String warkey = intent.getStringExtra("warriorkey");	
		//dito ilalagay yung username, url and key
		
		DatabaseHandler db = new DatabaseHandler(context);
		
		db.addData("ray", "localhost.com", "21312-293lsdma");
		
		Log.i("GCMUtilities.KeyReceiver", "GCM success! Received key: " + warkey);
		ToastUtils.showPromptLong(context, "GCM success!");
	}
}
