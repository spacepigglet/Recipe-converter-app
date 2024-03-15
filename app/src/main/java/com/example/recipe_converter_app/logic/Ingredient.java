package com.example.recipe_converter_app.logic;

public class Ingredient {
    private final Unit unit;
    private final int amount;
    private final String name;

    public Ingredient(String name, int amount, Unit unit) {
        this.unit = unit;
        this.amount = amount;
        this.name = name;
    }

    public Unit getUnit() {
        return unit;
    }

    public int getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }
    public String formatDetails() {
        return amount + " " + unit + " " +  name;
    }
}
