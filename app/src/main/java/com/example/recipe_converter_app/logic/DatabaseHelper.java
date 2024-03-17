package com.example.recipe_converter_app.logic;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "recipesdb.sqlite";
    private static final int DATABASE_VERSION = 1;
    private static final String INGREDIENTS_TABLE_NAME = "ingredients";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String RECIPE_INGREDIENTS_TABLE_NAME = "recipe_ingredients";
    private static final String RECIPES_TABLE_NAME = "recipes";
    private static final String COLUMN_RECIPE_ID = "recipe_id";
    private static final String COLUMN_INGREDIENT_ID = "ingredient_id";
        private static final String COLUMN_AMOUNT = "amount";
    private static final String COLUMN_UNIT = "unit";
    private final Context context;
    SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTables(db);
    }

    public void getRecipeFromDatabase(String recipeName) {
    }
    public void getAllRecipesFromDatabase() {
    }

    public boolean saveRecipeToDatabase(Recipe recipe) {
        //get writable db
        SQLiteDatabase database = this.getWritableDatabase();
        boolean recipeSuccess = false;
        boolean ingredientSuccess = false;
        boolean recipeIngredientSuccess = false;

        String recipeName = recipe.getName();
        //populate recipes table
        ContentValues recipeValues = new ContentValues();
        recipeValues.put(COLUMN_NAME, recipeName);
        long recipeId = database.insert(RECIPES_TABLE_NAME, null, recipeValues);
        recipeSuccess = insertSuccess(recipeId);
        ContentValues recipeIngredientValues = new ContentValues();

        //fill ingredientValues
        List<Ingredient> ingredients = recipe.getIngredients();
        ContentValues ingredientValues = new ContentValues();
        for (Ingredient ingredient : ingredients) {
            ingredientValues.put(COLUMN_NAME, ingredient.getName());
            long ingredientId = database.insert(INGREDIENTS_TABLE_NAME, null, ingredientValues);
            ingredientSuccess = insertSuccess(ingredientId);
            //fill recipeIngredientValues
            recipeIngredientValues.put(COLUMN_RECIPE_ID,recipeId);
            recipeIngredientValues.put(COLUMN_INGREDIENT_ID,ingredientId);
            recipeIngredientValues.put(COLUMN_AMOUNT,ingredient.getAmount());
            recipeIngredientValues.put(COLUMN_UNIT,ingredient.getUnit().getName());
        }
        long recipeIngredientId = database.insert(RECIPE_INGREDIENTS_TABLE_NAME, null, recipeIngredientValues);
        recipeIngredientSuccess = insertSuccess(recipeIngredientId);
        boolean allSuccess = recipeSuccess && ingredientSuccess && recipeIngredientSuccess;
        return allSuccess;
    }

    private boolean insertSuccess(long result) {
        if (result == -1) {
            Log.d("MyDatabaseHelper", "db insert fail");
            return false;
        } else {
            Log.d("MyDatabaseHelper", "db insert success");
            return true;
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + INGREDIENTS_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + RECIPES_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + RECIPE_INGREDIENTS_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    private void createTables(SQLiteDatabase db){
        //
        db.execSQL("create table if not exists " + INGREDIENTS_TABLE_NAME + "(" +
                COLUMN_ID + " integer primary key autoincrement not null, " +
                COLUMN_NAME + " text not null"
                + ");"
        );
        db.execSQL("create table if not exists " + RECIPES_TABLE_NAME + "(" +
                COLUMN_ID + " integer primary key autoincrement not null, " +
                COLUMN_NAME + " text not null"
                + ");"
        );
        db.execSQL("create table if not exists " + RECIPE_INGREDIENTS_TABLE_NAME + "(" +
                COLUMN_ID + " integer primary key autoincrement not null, " + COLUMN_NAME + " text not null," +
                COLUMN_RECIPE_ID + " text not null," +
                COLUMN_INGREDIENT_ID+ " text not null," +
                COLUMN_AMOUNT+ " real not null,"+
                COLUMN_UNIT+ " text not null," +
                        "foreign key (" + COLUMN_RECIPE_ID + ") references " + RECIPES_TABLE_NAME + "(" + COLUMN_ID + "),"+
                "foreign key (" + COLUMN_INGREDIENT_ID + ") references " + INGREDIENTS_TABLE_NAME + "(" + COLUMN_ID + ")"
                + ");"
        );
    }



}
