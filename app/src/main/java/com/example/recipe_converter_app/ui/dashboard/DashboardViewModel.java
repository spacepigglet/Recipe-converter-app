package com.example.recipe_converter_app.ui.dashboard;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.recipe_converter_app.logic.DatabaseHelper;

public class DashboardViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public DashboardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public void getFromDatabase(String recipeName, Context context){
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        dbHelper.getRecipeFromDatabase(recipeName);
    }
    public void getAllRecipesFromDatabase(Context context){
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        dbHelper.getAllRecipesFromDatabase();
    }

    public LiveData<String> getText() {
        return mText;
    }
}