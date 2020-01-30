package com.example.thenamequiz;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;
import java.util.List;

public class minApplication extends Application {
    public List<Profil> profiler = new ArrayList<>();
    public boolean firstTime = true;

    public minApplication(){
        super();

    }
    public void populate(){
        if (profiler.size() == 0){
            Bitmap defaultBilde = BitmapFactory.decodeResource(getResources(), R.drawable.mario);
            Bitmap defaultBilde2 = BitmapFactory.decodeResource(getResources(), R.drawable.luigi);
            profiler.add(new Profil("Mario", defaultBilde));
            profiler.add(new Profil("Luigi", defaultBilde2));
        }
    }
}
