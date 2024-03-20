package com.example.recipe_converter_app.logic;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Recipe {
    private String name;
    //private String description;
    private List<Ingredient> ingredients;
    private long id = 0;
    //for future implementation of users adding their own image
    private Bitmap image ;

    public Recipe(String name) {
        if(name != null){
            this.name = name;
        }

        ingredients = new ArrayList<>();
    }
    public Recipe(String name, long id) {
        this.name = name;
        ingredients = new ArrayList<>();
        this.id = id;
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

    public List<Ingredient> getIngredients() {
        return Collections.unmodifiableList(ingredients);
    }
    public Ingredient getIngredient(String name) {
        for (Ingredient i:ingredients) {
            if(i.getName().equals(name)){
                return i;
            }
        }
        //no ingredient by that name found in the recipe
        return null;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public long getId() {
        return id;
    }
    public String getIngredientsString(){
        StringBuilder sb = new StringBuilder();
        for (Ingredient ingredient : ingredients) {
            sb.append(ingredient).append("\n");
        }
        return sb.toString();
    }

    @NonNull
    @Override
    public String toString() {
        if (ingredients.isEmpty()) {
            return name + ": id " + id;
        } else {
            StringBuilder sb = new StringBuilder(name).append("\n");
            sb.append(getIngredientsString());
            return sb.toString();
        }
    }
}