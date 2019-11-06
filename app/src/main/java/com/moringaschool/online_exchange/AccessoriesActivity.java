package com.moringaschool.online_exchange;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

public class AccessoriesActivity extends AppCompatActivity {

    GridView accessoriesGridView;

    String[] accessoriesNames ={ "Decoor","Decor","Decor","Decor","Decor","Decor","Decor","Decor"};
    int[]    accessoriesPics={ R.drawable.hom1,R.drawable.hom2,R.drawable.hom3,R.drawable.hom4,R.drawable.hom5,R.drawable.home6,R.drawable.hom7,R.drawable.hom8};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accessories);

        accessoriesGridView=(GridView) findViewById(R.id.accessoriesgrid);

       AccessoriesAdapter accessoriesAdapter=new AccessoriesAdapter(this,accessoriesNames,accessoriesPics);

        accessoriesGridView.setAdapter(accessoriesAdapter);
    }
}