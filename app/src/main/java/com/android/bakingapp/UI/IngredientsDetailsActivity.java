package com.android.bakingapp.UI;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.bakingapp.Adapters.IngredientsAdapter;
import com.android.bakingapp.Adapters.RecipeAdapter;
import com.android.bakingapp.Interface.GetDataService;
import com.android.bakingapp.Models.IngredientData;
import com.android.bakingapp.Models.RecipeData;
import com.android.bakingapp.R;
import com.android.bakingapp.Retrofit.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by kamalshree on 8/3/2018.
 */

public class IngredientsDetailsActivity extends AppCompatActivity {

    private List<IngredientData> ingredientsList;

    private IngredientsAdapter adapter;
    private RecyclerView recyclerView;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_details);
        context =  this;

        ingredientsList = getIntent().getParcelableArrayListExtra("ingredientsList");

        recyclerView=(RecyclerView)findViewById(R.id.rv_ingredients) ;
        LinearLayoutManager layoutManager = new LinearLayoutManager(IngredientsDetailsActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        /*Create handle for the RetrofitInstance interface*/
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<IngredientData>> call = service.getAllIngredients();
        call.enqueue(new Callback<List<IngredientData>>() {
            @Override
            public void onResponse(Call<List<IngredientData>> call, Response<List<IngredientData>> response) {
                adapter = new IngredientsAdapter(context, ingredientsList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<IngredientData>> call, Throwable t) {
                Toast.makeText(IngredientsDetailsActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}