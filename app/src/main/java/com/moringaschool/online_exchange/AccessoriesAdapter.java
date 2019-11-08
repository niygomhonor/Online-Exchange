package com.moringaschool.online_exchange;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AccessoriesAdapter extends BaseAdapter {

    Context context;
    private final String[] accessoriesNames;
    private final int[] accessoriesPics;
    View view;
    LayoutInflater layoutInflater;

    public AccessoriesAdapter(Context context, String[] accessoriesNames, int[] accessoriesPics) {
        this.context = context;
        this.accessoriesNames = accessoriesNames;
        this.accessoriesPics = accessoriesPics;
    }


    @Override
    public int getCount() {
        return accessoriesNames.length;
    }

    @Override
    public Object getItem(int position) {
        return accessoriesNames[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.single_accessories, null);

        }
        ImageView accessoriesImageView=(ImageView) convertView.findViewById(R.id.accessoriesImage);
        TextView accessoriesNameView=(TextView) convertView.findViewById(R.id.accessoriesName);
        accessoriesImageView.setImageResource(accessoriesPics[position]);
        accessoriesNameView.setText(accessoriesNames[position]);

        return convertView;
    }
}