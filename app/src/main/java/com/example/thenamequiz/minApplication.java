package com.example.thenamequiz;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.preference.PreferenceManager;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class minApplication extends Application {
    public List<Profil> profiler = new ArrayList<>();
    public boolean firstTime = true;
    private SharedPreferences minePrefs;

    public minApplication(){
        super();

    }
    public boolean fjern(Profil p){
        Log.i("minTag", "prøver å fjerne " + p.getNavn());
        boolean bleiFjerna = false;
        if(!profiler.remove(p)){
            Log.i("minTag", "Blei ikkje fjerna korrekt. Kan ikkje bruke denne metoden.");
        }
        minePrefs = PreferenceManager.getDefaultSharedPreferences(this);
        Set<String> set = new HashSet<>(minePrefs.getStringSet("navneBase",  new HashSet<String>()));
        String filnavn = "";
        if(set == null){
            Log.i("minTag", "Prøver å fjerne, men navneBase er null");
        } else {
            Iterator<String> it = set.iterator();
            while(it.hasNext()){
                filnavn = it.next();
                if(filnavn.contains(p.getNavn())){
                    set.remove(filnavn);
                    if(deleteFromInternalStorage(filnavn)){
                        Log.i("minTag", filnavn + " blei sletta. ");
                        bleiFjerna = true;
                    } else {
                        Log.i("minTag", filnavn + " blei ikkje sletta. ");
                    }
                    break;
                }
            }
            Log.i("minTag", "strls set: " + set.size());
        }
        minePrefs.edit().putStringSet("navneBase", set).commit();
        return bleiFjerna;

    }
    public void populate(){
        //legger til profiler fra storage
        minePrefs = PreferenceManager.getDefaultSharedPreferences(this);
        Set<String> set = new HashSet<>(minePrefs.getStringSet("navneBase",  new HashSet<String>()));
        Log.i("debug: ", "Populate: set-strls: " + (set == null ? "null" : set.toString()));
        if(set != null) {
            for (String n : set) {
                String[] filNavn = n.split("_");
                //int nr = Integer.parseInt(filNavn[1].split(".")[0]);
                profiler.add(new Profil(filNavn[0], loadImageFromStorage(filNavn[0], Integer.parseInt(filNavn[1]))));
            }
        }


        //legger til eksempel-profiler om det var ingen fra storage
        if (profiler.size() == 0){
            Bitmap defaultBilde = BitmapFactory.decodeResource(getResources(), R.drawable.mario);
            Bitmap defaultBilde2 = BitmapFactory.decodeResource(getResources(), R.drawable.luigi);
            profiler.add(new Profil("Mario", defaultBilde));
            profiler.add(new Profil("Luigi", defaultBilde2));

        }
    }
    public boolean deleteFromInternalStorage(String filnavn){
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        Log.i("tag", "cw: " + cw.toString());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath = new File(directory,filnavn + ".jpg");
        Log.i("tag", "mypath: " + mypath.toString());
        return mypath.delete();
    }
    public String saveToInternalStorage(Bitmap bitmapImage, String navn, int nr){
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        Log.i("tag", "cw: " + cw.toString());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath = new File(directory,navn + "_" + nr + ".jpg");
        Log.i("tag", "mypath: " + mypath.toString());

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }
    public Bitmap loadImageFromStorage(String navn, int nr)
    {
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);

        try {
            File f = new File(directory, navn + "_" + nr + ".jpg");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            return b;
            //ImageView img=(ImageView)findViewById(R.id.imgPicker);
            //img.setImageBitmap(b);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
