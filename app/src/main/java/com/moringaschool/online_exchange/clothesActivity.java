package com.moringaschool.online_exchange;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

public class clothesActivity extends AppCompatActivity {
    GridView grideView;
    String[] values = {"classic1", "classic2", "classic3", "classic4", "wedding1", "wedding2", "wedding3", "wedding4","baby1", "baby2", "baby3", "baby4", "casual1", "casual2", "casual3", "casual4"};
    int[] images = {R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4, R.drawable.imagea, R.drawable.imageb, R.drawable.imagec, R.drawable.imaged, R.drawable.imagew, R.drawable.imagex, R.drawable.imagey, R.drawable.imagez, R.drawable.imagel, R.drawable.imagem, R.drawable.imagen, R.drawable.imageo};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothes);
        grideView = (GridView) findViewById(R.id.gridview);
        GridAdapter gridAdapter = new GridAdapter(this,values, images);
        grideView.setAdapter(gridAdapter);

    }
}
