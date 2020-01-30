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

        minApplication app = (minApplication) getApplication();
        MinAdapter minAdapter = new MinAdapter(this, R.layout.listeentity, app.profiler); //R.layout.listeEntity
        liste.setAdapter(minAdapter);


    }
}
