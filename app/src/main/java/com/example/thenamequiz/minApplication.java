package com.example.thenamequiz;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;
import java.util.List;

public class minApplication extends Application {
    public List<Profil> profiler = new ArrayList<>();

    public minApplication(){
        super();

    }

    public void fjern(String navn){
        for(Profil p : profiler){
            if(p.getNavn().equals(navn)){
                profiler.remove(p);
                return;
            }
        }
    }

    public void populate(){
        if (profiler.size() == 0){
            Bitmap defaultBilde = BitmapFactory.decodeResource(getResources(), R.drawable.mario);
            profiler.add(new Profil("Simen", defaultBilde));
            profiler.add(new Profil("Knut", defaultBilde));
            profiler.add(new Profil("A", defaultBilde));
            profiler.add(new Profil("B", defaultBilde));
            profiler.add(new Profil("C", defaultBilde));
        }
    }
}
