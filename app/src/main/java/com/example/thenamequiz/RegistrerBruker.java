package com.example.thenamequiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrerBruker extends AppCompatActivity {
    private SharedPreferences minePrefs;
    private SharedPreferences.Editor minPrefsEditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrernavn);

        minePrefs = PreferenceManager.getDefaultSharedPreferences(this);
        minPrefsEditor = minePrefs.edit();

    }
    public void lagreNavn(View view){
        TextView navnInput = (TextView) findViewById(R.id.inputOwnerName);
        String navn = navnInput.getText().toString();
        if(navn.length() > 1) {
            minPrefsEditor.putString(getString(R.string.owner_name), navn);
            minPrefsEditor.commit();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, getResources().getString(R.string.tooFewLetters), Toast.LENGTH_SHORT).show();
        }
    }
}
