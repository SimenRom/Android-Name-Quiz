package com.example.thenamequiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class TheQuizGameActivity extends Activity {
    public int score = 0;
    private Profil noverendeProfil;
    minApplication app;
    Random ran;
    int nr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        app = (minApplication) getApplication();
        if(app.profiler.size() < 2){
            Toast.makeText(this, getResources().getString(R.string.tooFewError), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            ran = new Random();
            score = 0;
            startNyRunde();
        }

    }

    /**
     * Funksjon som starter ny runde av quiz-spelet. Går til ny tilfeldig profil i databasen.
     */
    public void startNyRunde(){
        int newnr;
        Log.i("tag", "starter ny runde. Antal profiler: " + app.profiler.size());
        do{
            newnr = ran.nextInt(app.profiler.size());
            Log.i("tag", "nummer er: " + newnr);
        } while(newnr == nr);
        nr = newnr;
        noverendeProfil = app.profiler.get(nr);
        ImageView bilde = findViewById(R.id.quizBilde);
        bilde.setImageBitmap(noverendeProfil.getBilde());
    }

    /**
     * Funksjon som sjekker om gjettet navn i quiz er korrekt og gir tilbakemelding.
     * @param v
     */
    public void sjekkNavn(View v){
        TextView tilbakemelding = (TextView) findViewById(R.id.quizTilbakemelding);
        TextView guess = (TextView) findViewById(R.id.nameGuess);
        String userAnswer = guess.getText().toString();
        String correctAnswer = noverendeProfil.getNavn();

        if(userAnswer.length() < 1){
            tilbakemelding.setText("Skriv inn eit navn.");
        } else if (checkAndModifyScore(userAnswer, correctAnswer)){
            guess.setText("");
            tilbakemelding.setText("Korrekt, din score: " + score);
            startNyRunde();
        } else {
            tilbakemelding = (TextView) findViewById(R.id.quizTilbakemelding);
            tilbakemelding.setText("Feil. Korrekt navn er: " + correctAnswer);
        }
    }

    public boolean checkAndModifyScore(String userAnswer, String correctAnswer) {
        if (userAnswer.toLowerCase().equals(correctAnswer.toLowerCase())) {
            score = score + 1;
            return true;
        } else {
            return false;
        }

    }

    public int getScore() { return score; }
}
