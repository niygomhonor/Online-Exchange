package com.moringaschool.online_exchange;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.GridView;

public class GadgetsActivity extends AppCompatActivity {

    GridView gadgetsGridView;

    String[] gadgetsNames ={ "Samsung","Tecno","Iphone","Itel"};
    int[]    gadgetsPics={ R.drawable.widg1,R.drawable.widg2,R.drawable.widg4,R.drawable.widg2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gadgets);

        gadgetsGridView=(GridView) findViewById(R.id.gadgetsgrid);

        GadgetsAdapter gadgetsAdapter=new GadgetsAdapter(this,gadgetsNames,gadgetsPics);

        gadgetsGridView.setAdapter(gadgetsAdapter);
    }
}
