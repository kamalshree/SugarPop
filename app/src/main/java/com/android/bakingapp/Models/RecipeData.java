package com.android.bakingapp.Models;

import com.google.gson.annotations.SerializedName;

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

    public RecipeData(Integer id, String name, Integer servings) {
        this.id = id;
        this.name = name;
        this.servings = servings;
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
