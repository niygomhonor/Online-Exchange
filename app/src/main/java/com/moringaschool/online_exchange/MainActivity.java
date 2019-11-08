package com.moringaschool.online_exchange;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.moringaschool.online_exchange.auth.UserUpload;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button ViewStoreButton;
    private Button UploadButton;
    private Button UserBotton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewStoreButton = findViewById( R.id.ViewStore );
        ViewStoreButton.setOnClickListener( this );
        UploadButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == ViewStoreButton) {
            Intent ViewStoreIntent = new Intent(MainActivity.this, ViewStoreActivity.class);
            startActivity(ViewStoreIntent);
        }
        if (v== UploadButton){
            Intent uploadIntent = new Intent(MainActivity.this, UserUpload.class);
            startActivity(uploadIntent);

        }

    }
}
