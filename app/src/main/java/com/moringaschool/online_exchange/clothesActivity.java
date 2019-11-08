package com.moringaschool.online_exchange;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class clothesActivity extends AppCompatActivity {
    GridView grideView;
    String[] values = {"classic1", "classic2", "classic3", "classic4", "wedding1", "wedding2", "wedding3", "wedding4","baby1", "baby2", "baby3", "baby4", "casual1", "casual2", "casual3", "casual4"};
    int[] images = {R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4 , R.drawable.imagea, R.drawable.imageb, R.drawable.imagec, R.drawable.imaged, R.drawable.imagew, R.drawable.imagex, R.drawable.imagey, R.drawable.imagez, R.drawable.imagel, R.drawable.imagem, R.drawable.imagen, R.drawable.imageo};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothes);
        grideView = (GridView) findViewById(R.id.gridview);
        GridAdapter gridAdapter = new GridAdapter(this,values, images);
        grideView.setAdapter(gridAdapter);

        grideView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent , View view , int position , long id) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setData( Uri.parse("mailto:"));
                String[] to = {"niygomhonor@gmail.com", ""};
                intent.putExtra(Intent.EXTRA_EMAIL, to);
                intent.putExtra(Intent.EXTRA_SUBJECT, "subject to your app");
                intent.putExtra(Intent.EXTRA_TEXT, "text inside email");
                intent.setType("message/rfc822");
                Intent chooser = Intent.createChooser(intent, "Send email");
                startActivity(chooser);
            }
        } );

    }
}
