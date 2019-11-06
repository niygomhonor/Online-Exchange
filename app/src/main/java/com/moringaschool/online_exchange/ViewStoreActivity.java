package com.moringaschool.online_exchange;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ViewStoreActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView card1;
    private CardView card2;
    private CardView card3;
    private CardView card4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_view_store );

        card1 = findViewById( R.id.card1 );
        card2 = findViewById( R.id.card2 );
        card3 = findViewById( R.id.card3 );
        card4 = findViewById( R.id.card4 );

        card1.setOnClickListener( this );
        card2.setOnClickListener( this );
        card3.setOnClickListener( this );
        card4.setOnClickListener( this );

    }

    @Override
    public void onClick(View v) {
        if(v == card1){
            Intent HouseAccIntent = new Intent(ViewStoreActivity.this, ViewStoreActivity.class);
            startActivity(HouseAccIntent);
        }

        if(v == card2){
            Intent GadgetsIntent = new Intent(ViewStoreActivity.this, ViewStoreActivity.class);
            startActivity(GadgetsIntent);
        }
//
//        if(v == card3){
//            Intent HouseAccIntent = new Intent(ViewStoreActivity.this, ViewStoreActivity.class);
//            startActivity(HouseAccIntent);
//        }
//
//        if(v == card4){
//            Intent HouseAccIntent = new Intent(ViewStoreActivity.this, ViewStoreActivity.class);
//            startActivity(HouseAccIntent);
//        }

    }
}
