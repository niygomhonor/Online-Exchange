package com.moringaschool.online_exchange.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.moringaschool.online_exchange.Model.AllWays;
import com.moringaschool.online_exchange.Model.Message;
import com.moringaschool.online_exchange.R;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageAdapterViewHolder> {
Context context;
List<Message> messages;
DatabaseReference myDataRef;

    public MessageAdapter(Context context, List<Message> messages, DatabaseReference myDataRef) {
        this.context = context;
        this.messages = messages;
        this.myDataRef = myDataRef;
    }

    @NonNull
    @Override
    public MessageAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
   View view= LayoutInflater.from(context).inflate(R.layout.list_item_message,parent,false);
    return new MessageAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapterViewHolder holder, int position) {

        Message message=messages.get(position);
        if (message.getName().equals(AllWays.name)){

            holder.title.setText("You:"+message.getMessage());
            holder.title.setGravity(Gravity.START);
holder.li.setBackgroundColor(Color.parseColor("#F8FB78"));
        }
        else {

            holder.title.setText(message.getName()+":"+message.getMessage());
            holder.deleteMsg.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class MessageAdapterViewHolder extends RecyclerView.ViewHolder {
TextView title;
ImageButton deleteMsg;
LinearLayout li;
        public MessageAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            deleteMsg=itemView.findViewById(R.id.deleteMsg);
            li=itemView.findViewById(R.id.liMessage);
            deleteMsg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
myDataRef.child(messages.get(getAdapterPosition()).getKey()).removeValue();


                }
            });
        }

    }
}
