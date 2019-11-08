package com.moringaschool.online_exchange.auth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.moringaschool.online_exchange.BargainingChat;
import com.moringaschool.online_exchange.R;
import com.moringaschool.online_exchange.UsersList;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class UserUpload extends AppCompatActivity {
    public static final String TAG = UserUpload.class.getSimpleName();
    private static final int CHOOSE_IMAGE = 101;
    public Uri imgUri;
    EditText userBrand;
    EditText userObjectNaame;
    EditText userName;
    ImageView objectImage;
    Button saveDetails;
    String brand;
    String name;
    String object;
    Uri objImage;
    Button navgateToBargain;
    DatabaseReference databaseSwap;
    ListView userList;
    StorageReference mStorageRef;
    List<Users> ourUsers = new ArrayList<>();
    FloatingActionButton upLoadPicture;
    FloatingActionButton chooseFile;
    DatabaseReference mDatabaseRef;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private StorageTask uploadTask;
    private CardView cardUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_upload);
        mAuth = FirebaseAuth.getInstance();
        databaseSwap = FirebaseDatabase.getInstance().getReference("users");
        userName = findViewById(R.id.userName);
        userBrand = findViewById(R.id.userBrand);
        userObjectNaame = findViewById(R.id.password);
        saveDetails = findViewById(R.id.saveButton);
        userList = findViewById(R.id.usersList);
//        navgateToBargain = findViewById(R.id.toBargain);
        objectImage = findViewById(R.id.imageView);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("ObjectImages");
        mStorageRef = FirebaseStorage.getInstance().getReference("ObjectImages");
        upLoadPicture = findViewById(R.id.fabSend);
        chooseFile = findViewById(R.id.chooseImage);
        cardUser = findViewById(R.id.card1);
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    getSupportActionBar().setTitle("Welcome, " + user.getDisplayName() + "!");
                }
            }
        };
        saveDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUserInfo();
                Intent intent = new Intent(UserUpload.this, UsersList.class);
                startActivity(intent);
            }
        });

        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setData(Uri.parse("mailto:"));
                String[] to = {"muyenziraissa@gmail.com", ""};
                intent.putExtra(Intent.EXTRA_EMAIL, to);
                intent.putExtra(Intent.EXTRA_SUBJECT, "subject to your app");
                intent.putExtra(Intent.EXTRA_TEXT, "text inside email");
                intent.setType("message/rfc822");
                Intent chooser = Intent.createChooser(intent, "Send email");
                startActivity(chooser);
            }

        });


        chooseFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileChooser();
            }
        });
        upLoadPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uploadTask != null && uploadTask.isInProgress()) {

                    Toast.makeText(UserUpload.this, "In progress", Toast.LENGTH_SHORT).show();
                } else
                    FileupLoad();
            }
        });
    }

    private String getExtension(Uri uri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }

    private void FileupLoad() {

        StorageReference riversRef = mStorageRef.child(System.currentTimeMillis() + "." + getExtension(imgUri));


        uploadTask = riversRef.putFile(imgUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
//                        Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        Toast.makeText(UserUpload.this, "Image Uploaded successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                    }
                });


    }

    private void FileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(intent.createChooser(intent,"Select image"),CHOOSE_IMAGE);

        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imgUri = data.getData();
            objectImage.setImageURI(imgUri);
            Picasso.get().load(imgUri).into(objectImage);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.logoutMenu) {

            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(UserUpload.this, LogIn.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
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
//                OurUsers adapter = new OurUsers(UserUpload.this, ourUsers);
//
//                userList.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void addUserInfo() {

        brand = userBrand.getText().toString();
        name = userName.getText().toString();
        object = userObjectNaame.getText().toString();
        objectImage.setImageURI(imgUri);

        if (!TextUtils.isEmpty(name)) {

            String id = databaseSwap.push().getKey();
            Users users = new Users(id, object, brand, name, imgUri);
            databaseSwap.child(id).setValue(users);

            Toast.makeText(this, "User added", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Enter your name please", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

}
