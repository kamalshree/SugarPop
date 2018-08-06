package com.android.bakingapp.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.bakingapp.Adapters.IngredientsAdapter;
import com.android.bakingapp.Interface.GetDataService;
import com.android.bakingapp.Models.IngredientData;
import com.android.bakingapp.R;
import com.android.bakingapp.Retrofit.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by kamalshree on 8/3/2018.
 */

public class StepDetailsFrameLayout extends Fragment {

    private List<IngredientData> ingredientsList;

    private IngredientsAdapter adapter;
    private RecyclerView recyclerView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_ingredient_details, container, false);

        ingredientsList = getActivity().getIntent().getParcelableArrayListExtra("ingredientsList");
        recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_ingredients);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

         /*Create handle for the RetrofitInstance interface*/
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<IngredientData>> call = service.getAllIngredients();
        call.enqueue(new Callback<List<IngredientData>>() {
            @Override
            public void onResponse(Call<List<IngredientData>> call, Response<List<IngredientData>> response) {
                adapter = new IngredientsAdapter(getContext(), ingredientsList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<IngredientData>> call, Throwable t) {
                Toast.makeText(getContext(), getResources().getString(R.string.retrofit_error), Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }
}