package com.moringaschool.online_exchange;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moringaschool.online_exchange.Model.AllWays;
import com.moringaschool.online_exchange.Model.Message;
import com.moringaschool.online_exchange.Model.User;
import com.moringaschool.online_exchange.adapter.MessageAdapter;
import com.moringaschool.online_exchange.auth.LogIn;
import com.moringaschool.online_exchange.auth.UserUpload;

import java.util.ArrayList;
import java.util.List;
public class BargainingChat extends AppCompatActivity implements View.OnClickListener {

    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference messageDb;
    MessageAdapter messageAdapter;
    User u;
    List<Message> messages;
    RecyclerView rvMessage;
    EditText etMessage;
    FloatingActionButton btn_send_message;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bargaining_chat);
        init();
    }

    private void init() {
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        u = new User();

        rvMessage = (RecyclerView) findViewById(R.id.userNewMessage);
        etMessage = (EditText) findViewById(R.id.userTypeMessage);
        btn_send_message =(FloatingActionButton) findViewById(R.id.fabSend);
        btn_send_message.setOnClickListener(this);
        messages = new ArrayList<>();
    }
            @Override
            public void onClick(View v) {
                if (v==btn_send_message){
                    if(!TextUtils.isEmpty(etMessage.getText().toString())){
                        Message message = new Message(etMessage.getText().toString(),u.getName());
                        etMessage.setText("");
                        messageDb.push().setValue(message);
                    }

                    else {

                    Toast.makeText(BargainingChat.this, "You can not send blank message", Toast.LENGTH_SHORT).show();
                }
    }
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.menu,menu);
       return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    if (item.getItemId()== R.id.logoutMenu){

logout();
        return true;
    }
return super.onOptionsItemSelected(item);
    }
    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(BargainingChat.this, LogIn.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        final FirebaseUser currentUser= auth.getCurrentUser();

       u.setUid(currentUser.getUid());

        u.setEmail(currentUser.getEmail());

        database.getReference("Users").child(currentUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                u =  dataSnapshot.getValue(User.class);

                u.setUid(currentUser.getUid());

                AllWays.name = u.getName();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        messageDb = database.getReference("messages");
       messageDb.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Message message = dataSnapshot.getValue(Message.class);
                message.setKey(dataSnapshot.getKey());
                messages.add(message);
                displayMessage(messages);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Message message = dataSnapshot.getValue(Message.class);
                message.setKey(dataSnapshot.getKey());

                List<Message> newMessages = new ArrayList<Message>();
                for (Message m: messages){
                    if (m.getKey().equals(message.getKey())){
                        newMessages.add(message);
                    }
                    else {
                        newMessages.add(m);

                    }
                }
                messages = newMessages;

                displayMessage(messages);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                Message message = dataSnapshot.getValue(Message.class);
                message.setKey(dataSnapshot.getKey());
                List<Message> newMessages = new ArrayList<Message>();

                for (Message m : messages){
                    if (!m.getKey().equals(message.getKey())){
                        newMessages.add(m);
                    }
                }
                messages = newMessages;
                displayMessage(messages);
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        messages = new ArrayList<>();

    }
    private void displayMessage(List<Message> messages) {
      rvMessage.setLayoutManager(new LinearLayoutManager(BargainingChat.this));
      messageAdapter = new MessageAdapter(BargainingChat.this, messages, messageDb);
    rvMessage.setAdapter(messageAdapter);
    }
}










