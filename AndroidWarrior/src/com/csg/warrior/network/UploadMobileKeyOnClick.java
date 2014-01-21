package com.csg.warrior.network;

import android.content.Context;
import android.view.View;
import com.csg.warrior.ToastUtils;
import com.csg.warrior.domain.MobileKey;
import com.csg.warrior.exception.FailedUploadException;

public class UploadMobileKeyOnClick implements View.OnClickListener{
    MobileKey mobileKey;
    Context context;

    public UploadMobileKeyOnClick(MobileKey mobileKey, Context context) {
        this.mobileKey = mobileKey;
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        // todo this is what should be async
        String response = "";
        try {
        	HttpPOSTHelper httpPOST = new HttpPOSTHelper();
        	httpPOST.addParameter("key", "magic");	//HAHA SI RAY MAY ALAM NITO
        	httpPOST.addParameter("username", mobileKey.getKeyOwner());
            response = httpPOST.sendPOST(mobileKey.getUrlForUpload());
        } catch (FailedUploadException e) {
            response = e.getMessage();
        } finally {
            ToastUtils.showPrompt(context, response);
        }
    }
}
