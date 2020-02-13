package com.example.thenamequiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.Set;

public class AddNewActivity extends AppCompatActivity {
    private Bitmap imageBitmap;
    private ImageView imageView;
    private SharedPreferences minePrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leggtilny);
        imageView = findViewById(R.id.profilBildeRamme);

        minePrefs = PreferenceManager.getDefaultSharedPreferences(this);

    }

    /**
     * Funksjon som køyres ved "legg-til-knapp" for å legge til ny profil.
     * @param view
     */
    public void leggTilProfil(View view){
        TextView navnInput = findViewById(R.id.navnInput);
        String navnString = navnInput.getText().toString();
        //dispatchTakePictureIntent(view);
        TextView feilmelding = findViewById(R.id.feilMelding);
        SharedPreferences.Editor minPrefsEditor = minePrefs.edit();
        if(imageBitmap == null){
            feilmelding.setText("Har du tatt bilde?");
        } else if (!(navnString.length() > 2)){
            feilmelding.setText("Oppgi navn på minst 3 bokstaver.");
        } else if(imageBitmap != null && navnString.length() > 2){
            Log.i("navn: ", "" + navnString);
            Log.i("navnlengde: ", "" + navnString.length());
            Profil nyProfil = new Profil(navnString, imageBitmap);
            minApplication app = (minApplication) getApplication();
            app.profiler.add(nyProfil);
            Log.i("tag", "lagde ny profil. Legger den til...");
            //legger til navnet i databasen som tar vare på alle navn og lagrer PB lokalt.
            Set<String> set = new HashSet<>(minePrefs.getStringSet("navneBase", new HashSet<String>()));
            int i = 0;
            do {
                i++;
                Log.i("tag", "Forsøker å finne riktig nummer. i=" + i);
                if(!set.contains(navnString + "_" + i)){
                    set.add(navnString + "_" + i);
                    app.saveToInternalStorage(imageBitmap, navnString, i);
                    break;
                }
            } while (true);
            minPrefsEditor.putStringSet("navneBase", set);
            boolean saved = minPrefsEditor.commit();
            Log.i("tag: ", minePrefs.getStringSet("navneBase", null).toString());
            //Log.i("tag: ", "AddNew: set-strls: " + set.size());
            //Log.i("tag", "set: " + set.toString());

            //sender deg tilbake til hovedmenyen.
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            feilmelding.setText("Ukjent feilmelding...");
        }
    }

    /**
     * Funksjon som åpner kamera for å ta snapshot-bilde.
     */
    static final int REQUEST_IMAGE_CAPTURE = 1;
    public void dispatchTakePictureIntent(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    /**
     * Funksjon som henter bilde som blir sendt tilbake fra kamera.
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");

            imageView.setImageBitmap(imageBitmap);
        }
    }
}
