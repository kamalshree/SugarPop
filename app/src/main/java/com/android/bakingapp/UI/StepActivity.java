package com.android.bakingapp.UI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import com.android.bakingapp.Fragments.StepDetailsFrameLayout;
import com.android.bakingapp.R;

import java.util.Objects;

public class StepActivity extends AppCompatActivity {

    private Bundle arguments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_step);

        arguments = new Bundle();
        arguments.putParcelable("stepInfoFromActivity", Objects.requireNonNull(getIntent().getExtras()).getParcelable("stepInfo"));

        StepDetailsFrameLayout fragment = new StepDetailsFrameLayout();
        fragment.setArguments(arguments);
        this.getSupportFragmentManager().beginTransaction()
                .replace(R.id.my_step_frame_layout, fragment)
                .commit();

    }
}
