package com.android.bakingapp.UI;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.android.bakingapp.Fragments.IngredientsDetailsFrameLayout;
import com.android.bakingapp.Fragments.SettingOptionsFragment;
import com.android.bakingapp.Fragments.StepDetailsFrameLayout;
import com.android.bakingapp.Models.IngredientData;
import com.android.bakingapp.Models.StepData;
import com.android.bakingapp.R;
import com.android.bakingapp.Widgets.SugarPopWidget;

import java.util.List;

/**
 * Created by kamalshree on 8/5/2018.
 */

public class IngredientsActivityLayout extends AppCompatActivity implements SettingOptionsFragment.OnOptionClickListener {

    private boolean isTwoPane;
    private FragmentManager fragmentManager;
    Toolbar back_toolbar;
    private int position;

    TextView iv_toolbar_text;
    String recipeName;
    private List<StepData> stepsList;
    private List<IngredientData> ingredientsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingredient_framelayout);
        fragmentManager = getSupportFragmentManager();
        position = getIntent().getIntExtra("position", -1);

        stepsList = getIntent().getParcelableArrayListExtra("stepdetails");
        ingredientsList = getIntent().getParcelableArrayListExtra("ingredientsList");


        /** Toolbar **/
        recipeName = getIntent().getStringExtra("recipeName");
        back_toolbar=(Toolbar)findViewById(R.id.back_toolbar);
        iv_toolbar_text=findViewById(R.id.tv_toolbar_title);
        setSupportActionBar(back_toolbar);
        iv_toolbar_text.setText(recipeName);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        SharedPrefsDetails(recipeName, ingredientsList);

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
            Bundle arguments = new Bundle();
            arguments.putBoolean("isTwoPane", isTwoPane);

            SettingOptionsFragment fragment = new SettingOptionsFragment();
            fragment.setArguments(arguments);
            fragmentManager.beginTransaction()
                    .add(R.id.container, fragment)
                    .commit();
        }

        //Load Ingredients Fragment by default in the details pane
        if (isTwoPane) {
            fragmentManager.beginTransaction()
                    .replace(R.id.detailContainer, new IngredientsDetailsFrameLayout())
                    .commit();
        }
    }

    @Override
    public void onOptionSelected(String option) {
        if (isTwoPane) {
            switch (option) {
                case "ingredients": {
                    fragmentManager.beginTransaction()
                            .replace(R.id.detailContainer, new IngredientsDetailsFrameLayout())
                            .commit();
                    break;
                }

                case "step": {

                    fragmentManager.beginTransaction()
                            .replace(R.id.detailContainer, new StepDetailsFrameLayout())
                            .commit();
                    break;
                }

            }
        } else {
            fragmentManager.beginTransaction()
                    .replace(R.id.container, new IngredientsDetailsFrameLayout())
                    .commit();
        }
    }


    public void SharedPrefsDetails(String recipeName, List<IngredientData> ingredientsList){

        Intent intent = new Intent(this, SugarPopWidget.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < ingredientsList.size(); i++) {
            builder.append(ingredientsList.get(i).getIngredient()).append("\n");
        }

        SharedPreferences sharedPref = getSharedPreferences("RecipeDetails", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString( "SharedPrefrecipeName", recipeName);
        editor.putString( "SharedPrefingredientslist", builder.toString());
        editor.apply();
    }
}