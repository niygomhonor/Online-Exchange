package com.moringaschool.online_exchange.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.moringaschool.online_exchange.Model.ChatModel;
import com.moringaschool.online_exchange.R;

import java.util.List;

public class CustomAdapter extends BaseAdapter {
private List<ChatModel> list_chat_models;
private Context context;
private LayoutInflater layoutInflater;
    public CustomAdapter(List<ChatModel> list_chat_models, Context context) {
        this.list_chat_models = list_chat_models;
        this.context = context;
    layoutInflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE
    );

    }

    @Override
    public int getCount() {
        return list_chat_models.size();
    }

    @Override
    public Object getItem(int position) {
        return list_chat_models.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
View view=convertView;
if (view==null){
if (list_chat_models.get(position).isSend)
view=layoutInflater.inflate(R.layout.list_item_message_send,null);

else
    view=layoutInflater.inflate(R.layout.list_item_message_received,null);
    TextView text_message=view.findViewById(R.id.text_message);
text_message.setText(list_chat_models.get(position).message);
}
return  view;
    }
}
