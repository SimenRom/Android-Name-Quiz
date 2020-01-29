package com.example.thenamequiz;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
        ran = new Random();
        app = (minApplication) getApplication();
        score = 0;
        startNyRunde();
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
        if(guess.getText().toString().equals(noverendeProfil.getNavn())){
            score++;
            tilbakemelding.setText("Korrekt, din score: " + score);
            startNyRunde();
        } else {
            tilbakemelding = (TextView) findViewById(R.id.quizTilbakemelding);
            tilbakemelding.setText("Feil navn.");
        }
    }
}
