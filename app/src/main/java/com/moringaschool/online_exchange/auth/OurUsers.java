package com.moringaschool.online_exchange.auth;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.moringaschool.online_exchange.R;

import java.util.List;

public class OurUsers extends ArrayAdapter<Users> {
private Context context;
List<Users> ourUsers;

    public OurUsers( Context context, List<Users> ourUsers) {
super(context, R.layout.our_users,ourUsers);
        this.context = context;
        this.ourUsers = ourUsers;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        View listView=LayoutInflater.from(parent.getContext()).inflate(R.layout.our_users,parent,false);

        TextView viewName=listView.findViewById(R.id.textViewName);
        TextView viewBrand=listView.findViewById(R.id.textViewBrand);
        TextView viewObject=listView.findViewById(R.id.textViewObject);
        Users users=ourUsers.get(position);

        viewName.setText(users.getUserName());
        viewBrand.setText(users.getUserBrand());
        viewObject.setText(users.getUserObject());


        return listView;
    }
}
