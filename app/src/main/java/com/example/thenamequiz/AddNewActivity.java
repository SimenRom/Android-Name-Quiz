package com.example.thenamequiz;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AddNewActivity extends AppCompatActivity {
    private Bitmap imageBitmap;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leggtilny);
        imageView = findViewById(R.id.profilBildeRamme);

    }

    /**
     * Funksjon som køyres ved "legg-til-knapp" for å legge til ny profil.
     * @param view
     */
    public void leggTilProfil(View view){
        TextView navnInput = findViewById(R.id.navnInput);
        String navnString = navnInput.getText().toString();
        //dispatchTakePictureIntent(view);
        if(imageBitmap != null && navnString.length() > 2){
            Log.i("navn: ", "" + navnString);
            Log.i("navnlengde: ", "" + navnString.length());
            Profil nyProfil = new Profil(navnString, imageBitmap);
            minApplication app = (minApplication) getApplication();
            app.profiler.add(nyProfil);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            TextView feilmelding = findViewById(R.id.feilMelding);
            feilmelding.setText("Har du tatt bilde og skrevet inn navn? Minst tre bokstaver.");
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
