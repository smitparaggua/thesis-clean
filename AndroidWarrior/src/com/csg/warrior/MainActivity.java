package com.csg.warrior;

import android.app.ListActivity;
import android.os.Bundle;
import com.csg.warrior.domain.MobileKey;

import java.util.List;

public class MainActivity extends ListActivity {
    private MobileKeyAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showMobileKeys();
    }

    public void showMobileKeys() {
        List<MobileKey> mobileKeys = retrieveMobileKeys();
        adapter = new MobileKeyAdapter(this, R.layout.mobile_key_list, mobileKeys);
        this.setListAdapter(adapter);
    }

    // TODO Call upload on click of button
    // TODO call settings on click of ListAdapter

    private List<MobileKey> retrieveMobileKeys() {
        // TODO implement retrieving of mobile keys from DB
    }
}
