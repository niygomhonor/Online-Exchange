package com.moringaschool.online_exchange.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.moringaschool.online_exchange.BargainingChat;
import com.moringaschool.online_exchange.MainActivity;
import com.moringaschool.online_exchange.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LogIn extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = LogIn.class.getSimpleName();
    private static final String FIREBASE_CHILD_DAY = "UserUploads" ;
    @BindView(R.id.loginButton)Button mLoginButton;
    @BindView(R.id.email)EditText mEmails;
    @BindView(R.id.password)EditText mPasswords;
    @BindView(R.id.login) TextView mLogin;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ProgressDialog mAuthProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        ButterKnife.bind(this);
        mLogin.setOnClickListener(this);
        mLoginButton.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();




        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(LogIn.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };


        createAuthProgressDialog();
    }

    private void createAuthProgressDialog() {
        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle("Loading...");
        mAuthProgressDialog.setMessage("Authenticating with Firebase...");
        mAuthProgressDialog.setCancelable(false);
    }

    @Override
    public void onClick(View view) {
        if (view == mLogin) {
            Intent intent = new Intent(LogIn.this, CreateAcount.class);
            startActivity(intent);
            finish();
        }

        if (view == mLoginButton) {
            loginWithPassword();

//                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//                String uid = user.getUid();
//
//                DatabaseReference eventRef = FirebaseDatabase
//                        .getInstance()
//                        .getReference(FIREBASE_CHILD_DAY).child(uid);
//                DatabaseReference pushRef = eventRef.push();
//                String pushId = pushRef.getKey();
////                dayEvent.setPushId(pushId);
////                pushRef.setValue(dayEvent);
////              eventRef.push().setValue(dayEvent);
//                Toast.makeText(LogIn.this, "HAPPY DAY", Toast.LENGTH_SHORT).show();
//                System.out.println("Jesus love you");



        }
    }

    private void loginWithPassword() {
        String email = mEmails.getText().toString().trim();
        String password = mPasswords.getText().toString().trim();
        if (email.equals("")) {
            mEmails.setError("Please enter your email");
            return;
        }
        if (password.equals("")) {
            mPasswords.setError("Password cannot be blank");
            return;
        }
        mAuthProgressDialog.show();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        mAuthProgressDialog.dismiss();
                        Intent intent = new Intent(LogIn.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                        if (!task.isSuccessful()) {
                            Log.d(TAG, "signInWithEmail" ,task.getException());
                            Toast.makeText(LogIn.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


}
