package com.example.thenamequiz;

import org.junit.Test;

import static org.junit.Assert.*;

public class TheQuizGameActivityTest {



    @Test
    public void checkProfileManagement() {
        minApplication app = new minApplication();
        app.isUsingSharedPreferenes = false;
        app.populate();
        for (int i = 0; i < app.profiler.size(); i++) {
            System.out.println(app.profiler.get(i).getNavn());
        }
        int currCount = app.profiler.size();
        Profil profile = new Profil("Andrew", null);
        app.add(profile);
        app.fjern(profile);
        assertEquals(app.profiler.size(), currCount);

    }

    @Test
    public void checkScore() {
        TheQuizGameActivity activity = new TheQuizGameActivity();
        String[] correctAnswers = {"Jim", "Andrew", "Phil"};
        String[] userAnswers = {"Jim", "Nick", "Phil"};

        int correctScore = 2;
        for (int i = 0; i < correctAnswers.length; i++) {
            activity.checkAndModifyScore(userAnswers[i], correctAnswers[i]);
        }
        assertEquals("Score is not equal to correct score", activity.getScore(), correctScore);
    }
}