package com.android.bakingapp.Interface;

import com.android.bakingapp.Models.IngredientData;
import com.android.bakingapp.Models.RecipeData;
import com.android.bakingapp.Utils.BakingConstants;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by kamalshree on 8/3/2018.
 */

public interface GetDataService {
    @GET(BakingConstants.BAKING_JSON_URL)
    Call<List<RecipeData>> getAllRecipe();

    @GET(BakingConstants.BAKING_JSON_URL)
    Call<List<IngredientData>> getAllIngredients();
}
