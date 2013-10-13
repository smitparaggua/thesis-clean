package com.csg.warrior;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.csg.warrior.domain.MobileKey;
import com.csg.warrior.filechooser.FileChooserActivity;
import com.csg.warrior.persistence.DatabaseHandler;

import java.io.File;

public class SettingsActivity extends Activity {
    private static final int FILE_CHOOSER_REQUEST_CODE = 1;
    public static final String RESULT_KEY_NEW_SETTINGS = "newSettings";

    private MobileKey currentMobileKeySettings;
    private TextView addressBarView;
    private TextView associatedFilePathView;
    private EditText keyOwnerView;
    // TODO Make other fields editable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_view);
        currentMobileKeySettings = (MobileKey) getIntent().getSerializableExtra(MainActivity.EXTRAS_KEY_MOBILE_KEY);
        initializeViewValues(currentMobileKeySettings);
    }

    private void initializeViewValues(MobileKey currentMobileKeySettings) {
        if (currentMobileKeySettings != null) {
            keyOwnerView = (EditText) findViewById(R.id.key_owner);
            keyOwnerView.setText(currentMobileKeySettings.getKeyOwner());
            addressBarView = (TextView) findViewById(R.id.address_bar);
            addressBarView.setText(currentMobileKeySettings.getUrlForUpload());
            associatedFilePathView = (TextView) findViewById(R.id.associated_file_name);
            associatedFilePathView.setText(currentMobileKeySettings.getAssociatedFilePath());
        }
    }

    public void setAssociatedFile(View clickedButton) {
        Intent fileChooserIntent = new Intent(this, FileChooserActivity.class);
        this.startActivityForResult(fileChooserIntent, FILE_CHOOSER_REQUEST_CODE);
    }

    public void saveSettings(View clickedButton) {
        // TODO check if some values are not filled
        DatabaseHandler dbHandler = new DatabaseHandler(this);
        fetchSettingsFromView();
        if (currentMobileKeySettings.isTransient()) {
            dbHandler.addMobileKey(currentMobileKeySettings);
        } else {
            dbHandler.updateMobileKey(currentMobileKeySettings);
        }
        Intent resultIntent = new Intent();
        resultIntent.putExtra(RESULT_KEY_NEW_SETTINGS, currentMobileKeySettings);
        setResult(RESULT_OK);
        finish();
    }

    // TODO Check for empty attributes
    private void fetchSettingsFromView() {
        String keyOwner = keyOwnerView.getText().toString();
        File associatedFile = new File(associatedFilePathView.getText().toString());
        String url = addressBarView.getText().toString();
        currentMobileKeySettings.setAssociatedFile(associatedFile).setUrlForUpload(url).setKeyOwner(keyOwner);
    }

    public void cancelSettings(View clickedButton) {
        setResult(RESULT_CANCELED);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case FILE_CHOOSER_REQUEST_CODE:
                    TextView associatedFileLabel = (TextView) findViewById(R.id.associated_file_name);
                    associatedFileLabel.setText(data.getStringExtra("selectedFilePath"));
            }
        }
    }
}
