package com.example.thenamequiz;

import androidx.test.espresso.ViewInteraction;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

public class TheQuizGameActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void startNyRundeTest() {
        ViewInteraction pwView = onView(withId(R.id.start_quiz));
        pwView.perform(click());
        ViewInteraction pwViewB = onView(withId(R.id.nameGuessButton));
        //ViewInteraction pwViewC = onView(withId(R.id.quizTilbakemelding));
        //pwViewC.check(matches(withText("Skriv inn eit navn.")));
        //withId(R.id.quizTilbakemelding).
        onView(withText("Skriv inn eit navn.")).check(matches(isDisplayed()));
    }
}