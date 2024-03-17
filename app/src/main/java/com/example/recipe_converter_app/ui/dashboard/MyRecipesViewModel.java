package com.example.recipe_converter_app.ui.dashboard;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.recipe_converter_app.logic.DatabaseHelper;

import java.util.TreeMap;

public class MyRecipesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public MyRecipesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public void getFromDatabase(String recipeName, Context context){
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        dbHelper.getRecipeFromDatabase(recipeName);
    }
    public TreeMap<Long, String> getAllRecipesFromDatabase(Context context){
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        TreeMap<Long, String> recipeNames = dbHelper.getAllRecipeNamesFromDatabase();
        return recipeNames;
    }

    public LiveData<String> getText() {
        return mText;
    }
}