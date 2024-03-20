package com.example.recipe_converter_app.logic;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RecipeCalculator {
    private final float ratio;

    public RecipeCalculator(float originalIngredientAmount, float newIngredientAmount) {
        this.ratio = calculateRatio(originalIngredientAmount, newIngredientAmount);
    }

    private float newAmount(float originalAmount){
        return ratio*originalAmount;
    }


    private float calculateRatio(float originalIngredientAmount, float newIngredientAmount){
        return newIngredientAmount/originalIngredientAmount;
    }

    public Recipe getNewRecipe(Recipe recipe) {
        Recipe newRecipe = new Recipe(recipe.getName());
        for(Ingredient ingredient : recipe.getIngredients()){
            float newAmount = newAmount(ingredient.getAmount());
            float roundedAmount= round(newAmount);
            Ingredient newIngredient = new Ingredient(ingredient.getName(), roundedAmount, ingredient.getUnit());
            newRecipe.addIngredient(newIngredient);
        }
        return newRecipe;
    }
    private float round(float amount){
        float roundedAmount = BigDecimal.valueOf(amount)
                .setScale(1, RoundingMode.HALF_UP)
                .floatValue();
        return roundedAmount;
    }
}
