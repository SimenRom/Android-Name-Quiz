package com.example.thenamequiz;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListAllActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listall);


        ListView liste = (ListView) findViewById(R.id.minListe);
        // Bitmap.createBitmap(R.mipmap.ic_launcher);
        Bitmap.Config conf = Bitmap.Config.ARGB_8888;
        Bitmap defaultBilde = Bitmap.createBitmap(50, 50, conf);
        for(int i = 0; i < 50; i++){
            defaultBilde.setPixel(i, i, 0);
        }
        //Bitmap defaultBilde = Bitmap.createBitmap("data/data/res/drawable/mario.png", 10,10, conf);
        ArrayList<Profil> profiler = new ArrayList<Profil>();

        profiler.add(new Profil("Simen", defaultBilde));
        profiler.add(new Profil("Knut", defaultBilde));
        profiler.add(new Profil("A", defaultBilde));
        profiler.add(new Profil("B", defaultBilde));
        profiler.add(new Profil("C", defaultBilde));
        profiler.add(new Profil("D", defaultBilde));
        profiler.add(new Profil("E", defaultBilde));
        profiler.add(new Profil("F", defaultBilde));
        profiler.add(new Profil("G", defaultBilde));
        profiler.add(new Profil("H", defaultBilde));
        profiler.add(new Profil("I", defaultBilde));
        profiler.add(new Profil("J", defaultBilde));
        profiler.add(new Profil("K", defaultBilde));
        profiler.add(new Profil("L", defaultBilde));

        MinAdapter minAdapter = new MinAdapter(this, R.layout.listeentity, profiler); //R.layout.listeEntity
        liste.setAdapter(minAdapter);
    }

}
