package com.example.recipe_converter_app.ui.my_recipes;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.recipe_converter_app.logic.DatabaseHelper;
import com.example.recipe_converter_app.logic.Recipe;

import java.util.ArrayList;

public class MyRecipesViewModel extends ViewModel {

    private int recipeCardToDeletePosition;
    private final MutableLiveData<Recipe> selectedRecipe = new MutableLiveData<>();
    private MutableLiveData<Recipe> recipeCardToDelete = new MutableLiveData<>();

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
    public void resetDb(Context context) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        dbHelper.clearDatabase();
    }
    public void setRecipeCardToDelete(Recipe recipeCard) {
        recipeCardToDelete.setValue(recipeCard);
    }
    public void resetRecipeCardToDelete() {
        recipeCardToDelete = new MutableLiveData<>();
    }

    public void deleteSetRecipe(Context context) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        dbHelper.deleteRecipe(recipeCardToDelete.getValue().getId());
    }

    public void setRecipeCardToDeletePosition(int position) {
        recipeCardToDeletePosition = position;
    }

    public int getRecipeCardToDeletePosition() {
        return recipeCardToDeletePosition;
    }
}