package com.android.bakingapp.UI;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.bakingapp.Adapters.RecipeAdapter;
import com.android.bakingapp.Interface.GetDataService;
import com.android.bakingapp.Models.RecipeData;
import com.android.bakingapp.R;
import com.android.bakingapp.Retrofit.RetrofitClientInstance;

import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kamalshree on 8/3/2018.
 */

public class RecipeActivity extends AppCompatActivity {


    private RecipeAdapter adapter;
    private RecyclerView recyclerView;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        context =  this;

        recyclerView=(RecyclerView)findViewById(R.id.rv_recipe) ;
        LinearLayoutManager  layoutManager = new LinearLayoutManager(RecipeActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        /*Create handle for the RetrofitInstance interface*/
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<RecipeData>> call = service.getAllRecipe();
        call.enqueue(new Callback<List<RecipeData>>() {
            @Override
            public void onResponse(Call<List<RecipeData>> call, Response<List<RecipeData>> response) {
                adapter = new RecipeAdapter(context, response.body());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<RecipeData>> call, Throwable t) {
                Toast.makeText(RecipeActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}