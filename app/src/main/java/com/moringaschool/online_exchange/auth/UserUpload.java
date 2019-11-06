package com.moringaschool.online_exchange.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moringaschool.online_exchange.R;

public class UserUpload extends AppCompatActivity {
    public static final String TAG = UserUpload.class.getSimpleName();
    private static final int CHOOSE_IMAGE = 101;
    EditText userBrand;
    EditText userObjectNaame;
    EditText userName;
    ImageView objectImage;
    Button saveDetails;
    String brand;
    String name;
    String object;
    int objImage;
    DatabaseReference databaseSwap;
    ListView userList;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onStart() {
        super.onStart();
        databaseSwap.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot userSnap:dataSnapshot.getChildren()){

                    Users users=userSnap.getValue(Users.class);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_upload);
        mAuth = FirebaseAuth.getInstance();
databaseSwap= FirebaseDatabase.getInstance().getReference("users");
        userName=findViewById(R.id.userName);
        userBrand=findViewById(R.id.userBrand);
        userObjectNaame=findViewById(R.id.password);
saveDetails=findViewById(R.id.saveButton);
userList=findViewById(R.id.usersList);

saveDetails.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
addUserInfo();
    }
});




    }

private  void addUserInfo(){

    brand=userBrand.getText().toString();
    name=userName.getText().toString();
    object=userObjectNaame.getText().toString();
    objImage=objectImage.getVisibility();
if(!TextUtils.isEmpty(name)){

String id= databaseSwap.push().getKey();
Users users=new Users(id,object,brand,name,objImage);
databaseSwap.child(id).setValue(users);

    Toast.makeText(this, "User added", Toast.LENGTH_SHORT).show();
}else {
    Toast.makeText(this, "Enter your name please", Toast.LENGTH_SHORT).show();
}
}
    private  void showImageChooser(){
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(intent.createChooser(intent,"Select image"),CHOOSE_IMAGE);

    }


}
