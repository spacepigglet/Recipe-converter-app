package com.example.recipe_converter_app.logic;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
    private String name;
    //private String description;
    private List<Ingredient> ingredients;

    public Recipe(String name) {
        this.name = name;
        ingredients = new ArrayList<>();
    }

    public boolean addIngredient(Ingredient ingredient) {
        return ingredients.add(ingredient);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRecipeName() {
        return name;
    }
}
