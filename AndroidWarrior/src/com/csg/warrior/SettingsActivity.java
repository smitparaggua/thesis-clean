package com.csg.warrior;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.csg.warrior.filechooser.FileChooserActivity;

public class SettingsActivity extends Activity {
    private static final int FILE_CHOOSER_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_view);
    }

    public void setAssociatedFile(View clickedButton) {
        Intent fileChooserIntent = new Intent(this, FileChooserActivity.class);
        this.startActivityForResult(fileChooserIntent, FILE_CHOOSER_REQUEST_CODE);
        // TODO Send current settings
    }

    public void saveSettings(View clickedButton) {
        // TODO implement saving
    }

    public void cancelSettings(View clickedButton) {
        // TODO implement cancel settings
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
