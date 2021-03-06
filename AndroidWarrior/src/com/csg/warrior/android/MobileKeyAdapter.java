package com.csg.warrior.android;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.csg.warrior.android.domain.MobileKey;
import com.csg.warrior.android.network.DeleteMobileKeyOnClick;
import com.csg.warrior.android.network.UploadMobileKeyOnClick;
import com.csg.warrior.android.R;

import java.util.List;

public class MobileKeyAdapter extends ArrayAdapter<MobileKey> {
    private Context context;
    private int viewResourceId;
    private List<MobileKey> mobileKeys;

    public MobileKeyAdapter(Context context, int viewResourceId, List<MobileKey> mobileKeys) {
        super(context, viewResourceId, mobileKeys);
        this.context = context;
        this.viewResourceId = viewResourceId;
        this.mobileKeys = mobileKeys;
    }

    @Override
    public MobileKey getItem(int index) {
        return mobileKeys.get(index);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(viewResourceId, null);
        }
        final MobileKey mobileKey = mobileKeys.get(position);
        updateMobileKeyLabels(view, mobileKey);
        setUploadBehavior(view, mobileKey);
        setDeleteBehavior(view, mobileKey);
        return view;
    }

    private void updateMobileKeyLabels(View view, MobileKey mobileKey) {
        if(mobileKey != null) {
           // TextView associatedKeyView = (TextView) view.findViewById(R.id.associated_file_label);
            TextView urlView = (TextView) view.findViewById(R.id.url_label);
            TextView keyOwnerView = (TextView) view.findViewById(R.id.key_owner_label);
            //associatedKeyView.setText(mobileKey.getKey());
            keyOwnerView.setText(mobileKey.getKeyOwner());
            urlView.setText(mobileKey.getUrlForUpload());
        }
    }

    private void setUploadBehavior(View view, final MobileKey mobileKey) {
        ImageButton uploadButton = (ImageButton) view.findViewById(R.id.upload_button);
        uploadButton.setOnClickListener(new UploadMobileKeyOnClick(mobileKey, this.context));
    }
    
    private void setDeleteBehavior(View view, final MobileKey mobileKey) {
    	Button deleteButton = (Button) view.findViewById(R.id.delete_button);
    	deleteButton.setOnClickListener(new DeleteMobileKeyOnClick(mobileKey, this.context));

    }
}
