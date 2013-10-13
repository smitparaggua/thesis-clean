package com.csg.warrior.filechooser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.csg.warrior.R;

import java.io.File;
import java.util.List;

public class FileArrayAdapter extends ArrayAdapter<File> {

    private Context context;
    private int id;
    private List<File> files;

    private static final int PARENT_FILE_POSITION = 0;

    public FileArrayAdapter(Context context, int textViewResourceId, List<File> files) {
        super(context, textViewResourceId, files);
        this.context = context;
        id = textViewResourceId;
        this.files = files;
    }

    @Override
    public File getItem(int index) {
        return files.get(index);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(id, null);
        }
        final File file = files.get(position);
        changeViewLabels(view, file, position);
        return view;
    }

    // TODO Change hardcoded parent position
    private void changeViewLabels(View view, File file, int position) {
        String data = file.isDirectory() ? "Folder" : "File Size: " + file.length();
        String name = position == PARENT_FILE_POSITION ? ".." : file.getName();
        if (file != null) {
            TextView nameLabel = (TextView) view.findViewById(R.id.name_label);
            TextView dataLabel = (TextView) view.findViewById(R.id.data_label);
            nameLabel.setText(name);
            dataLabel.setText(data);
        }
    }
}
