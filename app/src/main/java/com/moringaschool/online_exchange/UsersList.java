package com.moringaschool.online_exchange;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moringaschool.online_exchange.auth.OurUsers;
import com.moringaschool.online_exchange.auth.UserUpload;
import com.moringaschool.online_exchange.auth.Users;

import java.util.ArrayList;
import java.util.List;

public class UsersList extends AppCompatActivity {
    ListView userList;
    List<Users> ourUsers = new ArrayList<>();
    DatabaseReference databaseSwap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);
        userList = findViewById(R.id.usersList);
        databaseSwap = FirebaseDatabase.getInstance().getReference("users");
//        OurUsers adapter = new OurUsers(UsersList.this, ourUsers);
//
//                userList.setAdapter(adapter);

    }
    @Override
    protected void onStart() {
        super.onStart();
        databaseSwap.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ourUsers.clear();

                for (DataSnapshot userSnap : dataSnapshot.getChildren()) {

                    Users users = userSnap.getValue(Users.class);
                    ourUsers.add(users);
                }
                OurUsers adapter = new OurUsers(UsersList.this, ourUsers);

                userList.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
