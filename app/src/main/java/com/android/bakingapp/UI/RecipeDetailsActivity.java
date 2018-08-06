package com.android.bakingapp.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.android.bakingapp.R;

/**
 * Created by kamalshree on 8/4/2018.
 */

public class RecipeDetailsActivity extends AppCompatActivity {

    private Context context;
    Toolbar back_toolbar;

    TextView iv_toolbar_text;
    String recipeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reciepe_details);
        context = this;

        /*Toolbar */
        recipeName = getIntent().getStringExtra("recipeName");
        back_toolbar=(Toolbar)findViewById(R.id.back_toolbar);
        iv_toolbar_text=findViewById(R.id.tv_toolbar_title);

        back_toolbar.setNavigationIcon(R.drawable.left_arrow);
        setSupportActionBar(back_toolbar);

        iv_toolbar_text.setText(recipeName);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        back_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void showIngredients(View view){
        Intent intent = new Intent(this, IngredientsDetailsActivity.class);
        intent.putParcelableArrayListExtra("ingredientsList", getIntent().getParcelableArrayListExtra("ingredientsList"));
        intent.putExtra("recipeName", recipeName);
        startActivity(intent);
    }

}
