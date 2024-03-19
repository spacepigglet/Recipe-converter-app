package com.example.recipe_converter_app.logic;

public enum Unit {
    GRAM("g"),
    KILOGRAM("kg"),
    MILLILITER("ml"),
    DECILITER("dl"),
    LITER("l"),
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
    // convert a string to the corresponding enum constant
    public static Unit fromString(String name) {
        for (Unit unit : Unit.values()) {
            if (unit.name.equalsIgnoreCase(name)) {
                return unit;
            }
        }
        throw new IllegalArgumentException("No enum constant with name " + name);
    }

}
