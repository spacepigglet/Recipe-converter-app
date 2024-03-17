package com.example.recipe_converter_app.ui;

import android.media.Image;

public class RecipeCardModel {
    String recipeName;
    Image image;

    public RecipeCardModel(String recipeName) {
        this.recipeName = recipeName;
        this.image = null;
    }
    public RecipeCardModel(String recipeName, Image image) {
        this.recipeName = recipeName;
        this.image = image;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
