package com.example.thenamequiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class TheQuizGameActivity extends AppCompatActivity {
    private int score;
    private Profil noverendeProfil;
    minApplication app;
    Random ran;
    int nr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        app = (minApplication) getApplication();
        if(app.profiler.size() < 1){
            Toast.makeText(this, getResources().getString(R.string.tooFewError), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            ran = new Random();
            score = 0;
            startNyRunde();
        }

    }

    public void startNyRunde(){
        int newnr;
        do{
            newnr = ran.nextInt(app.profiler.size());
        } while(newnr == nr);
        nr = newnr;
        noverendeProfil = app.profiler.get(nr);
        ImageView bilde = findViewById(R.id.quizBilde);
        bilde.setImageBitmap(noverendeProfil.getBilde());
    }
    public void sjekkNavn(View v){
        TextView tilbakemelding = (TextView) findViewById(R.id.quizTilbakemelding);
        TextView guess = (TextView) findViewById(R.id.nameGuess);
        if(guess.getText().toString().toLowerCase().equals(noverendeProfil.getNavn().toLowerCase())){
            score++;
            tilbakemelding.setText("Korrekt, din score: " + score);
            startNyRunde();
        } else {
            tilbakemelding = (TextView) findViewById(R.id.quizTilbakemelding);
            tilbakemelding.setText("Feil. Korrekt navn er: " + noverendeProfil.getNavn());
        }
    }
}
