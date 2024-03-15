package com.example.recipe_converter_app.logic;

public class RecipeCalculator {
    private float ratio;

    public RecipeCalculator(float originalIngredientAmount, float newIngredientAmount) {
        this.ratio = calculateRatio(originalIngredientAmount, newIngredientAmount);
    }

    public float newAmount(float originalAmount){
        return ratio*originalAmount;
    }


    private float calculateRatio(float originalIngredientAmount, float newIngredientAmount){
        return newIngredientAmount/originalIngredientAmount;
    }

}
