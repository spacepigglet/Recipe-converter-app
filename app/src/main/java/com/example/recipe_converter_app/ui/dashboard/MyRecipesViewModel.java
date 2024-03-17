package com.example.recipe_converter_app.ui.dashboard;

import android.content.Context;
import android.media.Image;

import androidx.lifecycle.ViewModel;

import com.example.recipe_converter_app.logic.DatabaseHelper;
import com.example.recipe_converter_app.logic.Recipe;
import com.example.recipe_converter_app.ui.RecipeCardModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeMap;

public class MyRecipesViewModel extends ViewModel {

    private ArrayList<Recipe> recipeCardModels = new ArrayList<>();
    private ArrayList<Image> images;

    public MyRecipesViewModel() {

    }

    public void getFromDatabase(String recipeName, Context context){
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        dbHelper.getRecipeFromDatabase(recipeName);
    }
    public ArrayList<Recipe> getAllRecipesFromDatabase(Context context){
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        ArrayList<Recipe> recipes = dbHelper.getAllRecipeNamesFromDatabase();
        //recipe is incomplete, only name and id
        return recipes;
    }

    private void getAllImagesFromDb(){

    }


}