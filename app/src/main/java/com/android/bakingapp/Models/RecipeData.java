package com.android.bakingapp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kamalshree on 8/3/2018.
 */

public class RecipeData{

        @SerializedName("id")
        private Integer id;
        @SerializedName("name")
        private String name;
        @SerializedName("servings")
        private Integer servings;

        @SerializedName("ingredients")
        private List<IngredientData> ingredients = null;

    public RecipeData(Integer id, String name, Integer servings) {
        this.id = id;
        this.name = name;
        this.servings = servings;
    }

    public List<IngredientData> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientData> ingredients) {
        this.ingredients = ingredients;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }
}
