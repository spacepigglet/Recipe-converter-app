package com.example.recipe_converter_app.logic;

import java.util.List;

public class Recipe {
    private String name;
    //private String description;
    private List<Ingredient> ingredients;

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    public void saveToDatabase(DatabaseHelper dbHelper) {
        // Save the recipe and its ingredients to the database using the DatabaseHelper
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
