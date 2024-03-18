package com.example.recipe_converter_app.ui.my_recipes;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.recipe_converter_app.logic.DatabaseHelper;
import com.example.recipe_converter_app.logic.Recipe;

import java.util.ArrayList;

public class MyRecipesViewModel extends ViewModel {

    private ArrayList<Recipe> recipeCardModels = new ArrayList<>();

    private long recipeIdToView;
    private final MutableLiveData<Recipe> selectedRecipe = new MutableLiveData<>();

    public MyRecipesViewModel() {

    }
    public void setSelectedRecipeCard(Recipe recipe) {
        selectedRecipe.setValue(recipe);
    }
    public Recipe getSelectedRecipeCard() {
        return selectedRecipe.getValue();
    }


    public Recipe getRecipeFromDatabase(Long recipeId, Context context){
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        return dbHelper.getRecipeFromDatabase(recipeId);
    }
    public ArrayList<Recipe> getAllRecipesFromDatabase(Context context){
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        //recipes may be empty
        ArrayList<Recipe> recipes = dbHelper.getAllRecipeNamesFromDatabase();
        //recipe is incomplete, only name and id
        return recipes;
    }
    public long getRecipeIdToView() {
        return recipeIdToView;
    }

    public void setRecipeIdToView(long recipeIdToView) {
        this.recipeIdToView = recipeIdToView;
    }
    public void resetDb(Context context) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        dbHelper.clearDatabase();
    }


}