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

public class StepTest {

    @Rule
    public ActivityTestRule<MainActivity> StepTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void openStepActivity(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                if (Looper.myLooper() == null)
                {
                    Looper.prepare();
                }
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        onData(anything()).inAdapterView(withId(R.id.rv_recipe)).atPosition(0).perform(click());
                        onData(anything()).inAdapterView(withId(R.id.rv_ingredients)).atPosition(0).perform(click());
                        onView(withId(R.id.tv_step_description)).check(matches(withText("Recipe Details")));
                    }
                }, 20000);
            }
        }).run();
    }
}
