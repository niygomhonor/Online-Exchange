package com.moringaschool.online_exchange;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BargainingChat extends AppCompatActivity {

    private Toolbar sToolBar;
    private DatabaseReference mydata;
    EditText myMsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bargaining_chat);
//        sToolBar= findViewById(R.id.);
mydata= FirebaseDatabase.getInstance().getReference("Messages");
        TextView msgTesxt=findViewById(R.id.userMessage);

mydata.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

        String [] messages=dataSnapshot.getValue().toString().split(",");

   msgTesxt.setText("");// Clear text

        for (int i=0;i<messages.length;i++){

           String[] finalMessage=messages[i].split("-");
           msgTesxt.append(finalMessage[0]+"\n");
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

        msgTesxt.setText("Cancelled");
    }
});
    }

    public void sendMessages(View view){

myMsg=findViewById(R.id.userTypeMessage);
mydata.child("userMessage").push().setValue(myMsg.getText().toString());
//mydata.child(Long.toString(System.currentTimeMillis())).setValue(myMsg.getText().toString());
myMsg.setText("");
        Intent intent=new Intent(BargainingChat.this,ReceiveMsgs.class);
        startActivity(intent);


    }
}
