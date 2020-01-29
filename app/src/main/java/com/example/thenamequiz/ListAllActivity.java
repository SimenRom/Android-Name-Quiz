package com.example.thenamequiz;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListAllActivity extends AppCompatActivity {
    minApplication app = (minApplication) getApplication();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listall);






        ListView liste = (ListView) findViewById(R.id.minListe);
/*
        // Bitmap.createBitmap(R.mipmap.ic_launcher);
        Bitmap.Config conf = Bitmap.Config.ARGB_8888;
        Bitmap defaultBilde = Bitmap.createBitmap(50, 50, conf);
        for(int i = 0; i < 50; i++){
            defaultBilde.setPixel(i, i, 0);
        }
        //Bitmap defaultBilde = Bitmap.createBitmap("data/data/res/drawable/mario.png", 10,10, conf);
        ArrayList<Profil> profiler = new ArrayList<Profil>();
 */
        minApplication app = (minApplication) getApplication();
        MinAdapter minAdapter = new MinAdapter(this, R.layout.listeentity, app.profiler); //R.layout.listeEntity
        liste.setAdapter(minAdapter);


    }
    public void fjern(View v){
        Toast.makeText(this, v.getTag().toString(), Toast.LENGTH_SHORT).show();
        //String navn = v.getTag(1).toString();
        //app.fjern(v.getTag().toString());

    }

}
