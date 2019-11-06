package com.moringaschool.online_exchange;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GadgetsAdapter extends BaseAdapter {

    Context context;
    private final String[] gadgetsName;
    private final int[] gadgetsPics;
    View view;
    LayoutInflater layoutInflater;

    public GadgetsAdapter(Context context, String[] gadgetsName, int[] gadgetsPics) {
        this.context = context;
        this.gadgetsName = gadgetsName;
        this.gadgetsPics = gadgetsPics;
    }

    @Override
    public int getCount() {
        return gadgetsName.length;
    }

    @Override
    public Object getItem(int position) {
        return gadgetsName[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.single_gadget, null);

        }
            ImageView gadgetImageView=(ImageView) convertView.findViewById(R.id.gadgetImage);
            TextView gadgetNameView=(TextView) convertView.findViewById(R.id.gadgetName);
            gadgetImageView.setImageResource(gadgetsPics[position]);
            gadgetNameView.setText(gadgetsName[position]);

        return convertView;
    }
}
