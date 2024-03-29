package com.moringaschool.online_exchange;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class shoesActivity extends AppCompatActivity {
    GridView grideView1;
    String[] values1 = {"classic1", "classic2", "classic3", "classic4", "wedding1", "wedding2", "wedding3", "wedding4","baby1", "baby2", "baby3", "baby4", "casual1", "casual2", "casual3", "casual4"};
    int[] images1 = {R.drawable.picture1, R.drawable.picture2, R.drawable.picture3, R.drawable.picture4 , R.drawable.picturea, R.drawable.pictureb, R.drawable.picturec, R.drawable.pictured, R.drawable.picturew, R.drawable.picturex, R.drawable.picturey, R.drawable.picturez, R.drawable.picturel, R.drawable.picturem, R.drawable.picturen, R.drawable.pictureo};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoes);
        grideView1 = (GridView) findViewById(R.id.gridview1);
        GridAdapter1 gridAdapter1 = new GridAdapter1(this,values1, images1);
        grideView1.setAdapter(gridAdapter1);

        grideView1.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent , View view , int position , long id) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setData( Uri.parse("mailto:"));
                String[] to = {"sylviekimbagira@gmail.com", ""};
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
