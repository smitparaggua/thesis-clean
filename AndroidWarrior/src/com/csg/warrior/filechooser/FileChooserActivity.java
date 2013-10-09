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
        showContent();
    }

    private void showContent() {
        this.setTitle("Current Dir: " + currentDirectory.getName());
        List<Option> contentsList = getDirectoryContentsDirectoryFirst();
        adapter = new FileArrayAdapter(this, R.layout.file_chooser_view, contentsList);
        this.setListAdapter(adapter);
    }

    private List<Option> getDirectoryContentsDirectoryFirst() {
        List<Option> contentsList = new ArrayList<Option>();
        List<Option> directoryList = new ArrayList<Option>();
        List<Option> fileList = new ArrayList<Option>();
        classifyFiles(directoryList, fileList);
        Collections.sort(directoryList);
        Collections.sort(fileList);
        if(!currentDirectory.getName().equalsIgnoreCase("sdcard")) {
            contentsList.add(0,new Option("..","Parent Directory",currentDirectory.getParent()));
        }
        contentsList.addAll(directoryList);
        contentsList.addAll(fileList);
        return contentsList;
    }

    private void classifyFiles(List<? super Option> directoryList, List<? super Option> fileList) {
        File[]filesInDirectory = currentDirectory.listFiles();
        try{
            for(File file: filesInDirectory) {
                if(file.isDirectory()) {
                    directoryList.add(new Option(file.getName(),"Folder",file.getAbsolutePath()));
                } else {
                    fileList.add(new Option(file.getName(), "File Size: " + file.length(), file.getAbsolutePath()));
                }
            }
        } catch(Exception e) {
            // Nothing can be done
        }
    }

    @Override
    protected void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);
        Option selectedOption = adapter.getItem(position);
        if(selectedOption.getData().equalsIgnoreCase("folder")||selectedOption.getData().equalsIgnoreCase("parent directory")){
            currentDirectory = new File(selectedOption.getPath());
            showContent();
        } else {
            onFileClick(selectedOption);
        }
    }

    private void onFileClick(Option selectedOption)
    {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("selectedFilePath", selectedOption.getPath());
        setResult(RESULT_OK, returnIntent);
        finish();
    }
}
