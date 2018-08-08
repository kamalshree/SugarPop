package com.android.bakingapp;

import android.os.Handler;
import android.os.Looper;
import android.support.test.rule.ActivityTestRule;

import com.android.bakingapp.UI.MainActivity;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;

/**
 * Created by kamalshree on 8/8/2018.
 */

public class MainTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule
            = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void openStepActivity(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        onData(anything()).inAdapterView(withId(R.id.rv_recipe)).atPosition(0).perform(click());
                        onView(withId(R.id.rl_ingredients)).check(matches(withText("Ingredients")));
                    }
                }, 20000);
            }
        }).run();

    }
}
