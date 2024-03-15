package com.example.recipe_converter_app.logic;

public class Ingredient {
    private final Unit unit;
    private final int amount;

    public Ingredient(Unit unit, int amount) {
        this.unit = unit;
        this.amount = amount;
    }
}
