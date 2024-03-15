package com.example.recipe_converter_app.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class AddIngredientViewModel {
    private final MutableLiveData<String> mText;

    public AddIngredientViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is AddIngredient fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
