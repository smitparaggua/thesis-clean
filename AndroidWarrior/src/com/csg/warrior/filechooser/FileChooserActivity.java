package com.csg.warrior.filechooser;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import com.csg.warrior.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileChooserActivity extends ListActivity {
    private File currentDirectory;
    private FileArrayAdapter adapter;
    private static final String ROOT_DIRECTORY = "/sdcard/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundledData = getIntent().getExtras();
        if(bundledData == null) {
            currentDirectory = new File(ROOT_DIRECTORY);
        } else {
            currentDirectory = (File) bundledData.getSerializable("currentDirectory");
        }
        this.setTitle("Current Dir: " + currentDirectory.getName());
        showContent();
    }

    private void showContent() {
        List<File> contentsList = getDirectoryContents();
        Collections.sort(contentsList, new DirectoryFirstComparator());
        adapter = new FileArrayAdapter(this, R.layout.file_chooser_view, contentsList);
        this.setListAdapter(adapter);
    }

    private List<File> getDirectoryContents() {
        List<File> contentsList;
        contentsList = arrayToList(currentDirectory.listFiles());
        if(!currentDirectory.getName().equalsIgnoreCase("sdcard")) {
            contentsList.add(0, new File(currentDirectory.getParent()));
        }
        return contentsList;
    }

    private <T> List<T> arrayToList(T[] array) {
        List<T> returnValue = new ArrayList<T>();
        for (T item : array) {
            returnValue.add(item);
        }
        return returnValue;
    }

    @Override
    protected void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);
        File selectedFile = adapter.getItem(position);
        if(selectedFile.isDirectory()||selectedFile.equals(currentDirectory.getParentFile())){
            currentDirectory = new File(selectedFile.getPath());
            showContent();
        } else {
            onFileClick(selectedFile);
        }
    }

    private void onFileClick(File selectedFile)
    {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("selectedFilePath", selectedFile.getPath());
        setResult(RESULT_OK, returnIntent);
        finish();
    }
}
