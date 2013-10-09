package com.csg.warrior.filechooser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.csg.warrior.R;

import java.util.List;

public class FileArrayAdapter extends ArrayAdapter<Option> {

    private Context context;
    private int id;
    private List<Option> items;

    public FileArrayAdapter(Context context, int textViewResourceId, List<Option> objects) {
        super(context, textViewResourceId, objects);
        this.context = context;
        id = textViewResourceId;
        items = objects;
    }

    public Option getItem(int index) {
        return items.get(index);
    }

    // TODO refactor
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(id, null);
        }
        final Option option = items.get(position);
        if (option != null) {
            TextView nameLabel = (TextView) view.findViewById(R.id.name_label);
            TextView dataLabel = (TextView) view.findViewById(R.id.data_label);
            changeText(nameLabel, option.getData());
            changeText(dataLabel, option.getName());
        }
        return view;
    }

    private void changeText(TextView textView, CharSequence text) {
        if (textView != null) {
            textView.setText(text);
        }
    }
}
