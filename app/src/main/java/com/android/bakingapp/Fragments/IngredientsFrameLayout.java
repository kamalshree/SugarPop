package com.android.bakingapp.Fragments;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.bakingapp.R;
import com.android.bakingapp.UI.IngredientsDetailsActivity;

/**
 * Created by kamalshree on 8/5/2018.
 */

public class IngredientsFrameLayout extends AppCompatActivity implements SettingOptionsFragment.OnOptionClickListener {

    private boolean isTwoPane;
    private FragmentManager fragmentManager;
    Toolbar back_toolbar;

    TextView iv_toolbar_text;
    String recipeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingredient_framelayout);
        fragmentManager = getSupportFragmentManager();

        /** Toolbar **/
        recipeName = getIntent().getStringExtra("recipeName");
        back_toolbar=(Toolbar)findViewById(R.id.back_toolbar);
        iv_toolbar_text=findViewById(R.id.tv_toolbar_title);
        setSupportActionBar(back_toolbar);
        iv_toolbar_text.setText(recipeName);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        if (findViewById(R.id.detailContainer) != null) {
            isTwoPane = true;
        } else {
            isTwoPane = false;
            back_toolbar.setNavigationIcon(R.drawable.left_arrow);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            back_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }

        if (savedInstanceState == null) {
            fragmentManager.beginTransaction()
                    .add(R.id.container, new SettingOptionsFragment())
                    .commit();
        }

        //Load Ingredients Fragment by default in the details pane
        if (isTwoPane) {
            fragmentManager.beginTransaction()
                    .replace(R.id.detailContainer, new IngredientsDetailsActivity())
                    .commit();
        }

    }

    @Override
    public void onOptionSelected(String option) {

        if (isTwoPane) {
            switch (option) {
                case "ingredients": {
                    fragmentManager.beginTransaction()
                            .replace(R.id.detailContainer, new IngredientsDetailsActivity())
                            .commit();
                    break;
                }

            }
        } else {
            fragmentManager.beginTransaction()
                    .replace(R.id.container, new IngredientsDetailsActivity())
                    .commit();
        }
    }
}