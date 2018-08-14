package com.android.bakingapp.Models;

import android.util.Base64;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by kamalshree on 8/3/2018.
 */

public class RecipeData {

    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("servings")
    private Integer servings;

    @SerializedName("ingredients")
    private List<IngredientData> ingredients = null;

    @SerializedName("steps")
    @Expose
    private List<StepData> steps = null;

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

    public List<StepData> getSteps() {
        return steps;
    }

    public void setSteps(List<StepData> steps) {
        this.steps = steps;
    }
}
