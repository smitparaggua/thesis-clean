//dummy commit

package com.csg.warrior.GCM;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.csg.warrior.ToastUtils;

public class KeyReceiver extends BroadcastReceiver {
	
	@Override
	public void onReceive (Context context, Intent intent){
		Log.i("GCMUtilities.KeyReceiver", "GCM success!");
		ToastUtils.showPromptLong(context, "GCM success!");
		// implement code that will receive key and launch an activity?
		// di ko pa alam ano gagawin mismo pag nakuha na yung key
	}
}
