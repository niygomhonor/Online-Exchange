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
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            view = new View(context);
            view = layoutInflater.inflate(R.layout.single_gadget, null);
            ImageView gadgetImageView=(ImageView) view.findViewById(R.id.gadgetImage);
            TextView gadgetNameView=(TextView) view.findViewById(R.id.gadgetName);
            gadgetImageView.setImageResource(gadgetsPics[position]);
            gadgetNameView.setText(gadgetsName[position]);
        }

        return view;
    }
}
