package com.moringaschool.online_exchange;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridLayout;
import android.widget.GridView;

public class GadgetsActivity extends AppCompatActivity {

    GridView gadgetsGridView;

    String[] gadgetsNames ={ "Ipod","Headphones","Phone Case","Watch","Speaker","Speaker","Printer","Camera","Phone case","Phone Case"};
    int[]    gadgetsPics={ R.drawable.widg1,R.drawable.widg2,R.drawable.widg9,R.drawable.widg4,R.drawable.widg8,R.drawable.widg6,R.drawable.widg7,R.drawable.widg5,R.drawable.widg3,R.drawable.widg10};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gadgets);

        gadgetsGridView=(GridView) findViewById(R.id.gadgetsgrid);

        GadgetsAdapter gadgetsAdapter=new GadgetsAdapter(this,gadgetsNames,gadgetsPics);

        gadgetsGridView.setAdapter(gadgetsAdapter);

        gadgetsGridView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent , View view , int position , long id) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setData( Uri.parse("mailto:"));
                String[] to = {"ruzindanawendy@gmail.com", ""};
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
