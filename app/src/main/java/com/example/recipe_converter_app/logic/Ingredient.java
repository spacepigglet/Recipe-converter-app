package com.example.recipe_converter_app.logic;

import androidx.annotation.NonNull;

public class Ingredient {
    private final Unit unit;
    private final float amount;
    private final String name;

    public Ingredient(String name, float amount, Unit unit) {
        this.unit = unit;
        this.amount = amount;
        this.name = name;
    }

    public Unit getUnit() {
        return unit;
    }

    public float getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }
    public String formatDetails() {
        return amount + " " + unit + " " +  name;
    }

    @NonNull
    @Override
    public String toString() {
        return formatDetails();
    }
}
