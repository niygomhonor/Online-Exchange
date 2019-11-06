package com.moringaschool.online_exchange;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridAdapter1 extends BaseAdapter {
    Context context;
    private final String [] values1;
    private final int [] images1;
    View view;
    LayoutInflater layoutInflater;

    public GridAdapter1(Context context, String[] values1, int[] images1) {
        this.context = context;
        this.values1 = values1;
        this.images1 = images1;
    }


    @Override
    public int getCount() {
        return values1.length;
    }

    @Override
    public Object getItem(int position) {
        return values1[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.activity_gride_item1,null);


        }
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView1);
        TextView textView = (TextView) convertView.findViewById(R.id.griddata1);
        imageView.setImageResource(images1[position]);
        textView.setText(values1[position]);
        return convertView;
    }
}
