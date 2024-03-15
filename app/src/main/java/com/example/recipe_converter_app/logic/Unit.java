package com.example.recipe_converter_app.logic;

public enum Unit {
    GRAM("g"),
    KILOGRAM("kg"),
    MILLILITER("mL"),
    DECILITER("dL"),
    LITER("L"),
    TEASPOON("tsk"),
    TABLESPOON("msk"),
    SPICE("krm"),
    PIECE("st");

    private final String name;
    Unit(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    @Override public String toString(){
        return name;
    }
}
