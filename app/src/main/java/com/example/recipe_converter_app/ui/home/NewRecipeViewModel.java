package com.example.recipe_converter_app.ui.home;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.recipe_converter_app.logic.DatabaseHelper;
import com.example.recipe_converter_app.logic.Ingredient;
import com.example.recipe_converter_app.logic.Recipe;
import com.example.recipe_converter_app.logic.RecipeCalculator;
import com.example.recipe_converter_app.logic.Unit;

import java.util.ArrayList;

public class NewRecipeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private ArrayList<Ingredient> recipeIngredients;
    private Ingredient currentIngredient;
    private Recipe recipe;
    private float newBaseIngredientAmount;
    private float originalBaseIngredientAmount;
    //private final LiveData<List<Ingredient>> recipeIngredients;
    /*private final MutableLiveData<Set<Filter>> filters = new MutableLiveData<>();

    private final LiveData<List<Item>> originalList = ...;
    private final LiveData<List<Item>> filteredList = ...;

    public LiveData<List<Item>> getFilteredList() {
        return filteredList;
    }

    public LiveData<Set<Filter>> getFilters() {
        return filters;
    }

    public void addFilter(Filter filter) { ... }

    public void removeFilter(Filter filter) { ... }*/

    public NewRecipeViewModel() {
        mText = new MutableLiveData<>();
        recipeIngredients = new ArrayList<>();
        /*recipeIngredients = new LiveData<List<Ingredient>>() {
            @Override
            public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super List<Ingredient>> observer) {
                super.observe(owner, observer);
            }
        }*/
    }

    public void setText(String recipeTitle) {
        mText.setValue(recipeTitle);
    }
    public String getText() {
        return mText.getValue();
    }

    public boolean addIngredient(String ingredientToAdd, float amount, Unit unit){
        Ingredient ingredient = new Ingredient(ingredientToAdd, amount, unit);
        currentIngredient = ingredient;

        return recipe.addIngredient(ingredient);
    }
    public Ingredient getCurrentIngredient(){return currentIngredient;}

    public void newRecipe(String title){
        recipe = new Recipe(title);
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void generateRecipe(Context context) {
        RecipeCalculator recipeCalculator = new RecipeCalculator(originalBaseIngredientAmount, newBaseIngredientAmount);
        Recipe recipe = recipeCalculator.getNewRecipe(this.recipe);
        saveToDatabase(recipe, context);
    }

    private void saveToDatabase(Recipe recipe, Context context) {
        //get writable db
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        dbHelper.saveRecipeToDatabase(recipe);
    }

    public float getNewBaseIngredientAmount() {
        return newBaseIngredientAmount;
    }
    public void setOriginalBaseIngredientAmount(float originalBaseIngredientAmount) {
        this.originalBaseIngredientAmount = originalBaseIngredientAmount;
    }

    public void setNewBaseIngredientAmount(float newBaseIngredientAmount) {
        this.newBaseIngredientAmount = newBaseIngredientAmount;
    }
}