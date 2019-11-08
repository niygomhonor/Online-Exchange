package com.moringaschool.online_exchange;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class AccessoriesActivity extends AppCompatActivity {

    GridView accessoriesGridView;

    String[] accessoriesNames ={"Decor","Decor","Decor","Decor","Lamp","Lamp","Chair","Chair","Pan","Pan"};
    int[]    accessoriesPics={ R.drawable.hom3,R.drawable.home2,R.drawable.hom1,R.drawable.hom4,R.drawable.hom5,R.drawable.hom6,R.drawable.hom7,R.drawable.hom8,R.drawable.hom9,R.drawable.hom10};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accessories);

        accessoriesGridView=(GridView) findViewById(R.id.accessoriesgrid);

       AccessoriesAdapter accessoriesAdapter=new AccessoriesAdapter(this,accessoriesNames,accessoriesPics);

        accessoriesGridView.setAdapter(accessoriesAdapter);

        accessoriesGridView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent , View view , int position , long id) {

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
        } );



    }

}