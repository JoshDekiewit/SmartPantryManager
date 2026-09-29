package com.example.smartpantrymanager.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.smartpantrymanager.model.Recipe;
import com.example.smartpantrymanager.model.RecipeIngredient;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.smartpantrymanager.model.PantryItem;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "smart_pantry.db";
    private static final int DATABASE_VERSION = 1;

    // Pantry table
    public static final String TABLE_PANTRY_ITEMS = "pantry_items";
    public static final String COLUMN_PANTRY_ID = "id";
    public static final String COLUMN_PANTRY_NAME = "name";
    public static final String COLUMN_PANTRY_QUANTITY = "quantity";
    public static final String COLUMN_PANTRY_UNIT = "unit";
    public static final String COLUMN_PANTRY_EXPIRY = "expiry_date";

    // Recipes table
    public static final String TABLE_RECIPES = "recipes";
    public static final String COLUMN_RECIPE_ID = "id";
    public static final String COLUMN_RECIPE_NAME = "name";
    public static final String COLUMN_RECIPE_INSTRUCTIONS = "instructions";

    // Recipe ingredients table
    public static final String TABLE_RECIPE_INGREDIENTS = "recipe_ingredients";
    public static final String COLUMN_RECIPE_INGREDIENT_ID = "id";
    public static final String COLUMN_RECIPE_INGREDIENT_RECIPE_ID = "recipe_id";
    public static final String COLUMN_RECIPE_INGREDIENT_NAME = "ingredient_name";
    public static final String COLUMN_RECIPE_INGREDIENT_QUANTITY = "quantity";
    public static final String COLUMN_RECIPE_INGREDIENT_UNIT = "unit";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createPantryTable = "CREATE TABLE " + TABLE_PANTRY_ITEMS + " (" +
                COLUMN_PANTRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PANTRY_NAME + " TEXT NOT NULL, " +
                COLUMN_PANTRY_QUANTITY + " REAL NOT NULL, " +
                COLUMN_PANTRY_UNIT + " TEXT NOT NULL, " +
                COLUMN_PANTRY_EXPIRY + " TEXT" +
                ")";

        String createRecipesTable = "CREATE TABLE " + TABLE_RECIPES + " (" +
                COLUMN_RECIPE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_RECIPE_NAME + " TEXT NOT NULL, " +
                COLUMN_RECIPE_INSTRUCTIONS + " TEXT NOT NULL" +
                ")";

        String createRecipeIngredientsTable =
                "CREATE TABLE " + TABLE_RECIPE_INGREDIENTS + " (" +
                        COLUMN_RECIPE_INGREDIENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_RECIPE_INGREDIENT_RECIPE_ID + " INTEGER NOT NULL, " +
                        COLUMN_RECIPE_INGREDIENT_NAME + " TEXT NOT NULL, " +
                        COLUMN_RECIPE_INGREDIENT_QUANTITY + " REAL NOT NULL, " +
                        COLUMN_RECIPE_INGREDIENT_UNIT + " TEXT NOT NULL, " +
                        "FOREIGN KEY (" + COLUMN_RECIPE_INGREDIENT_RECIPE_ID + ") " +
                        "REFERENCES " + TABLE_RECIPES + "(" + COLUMN_RECIPE_ID + ")" +
                        ")";

        db.execSQL(createPantryTable);
        db.execSQL(createRecipesTable);
        db.execSQL(createRecipeIngredientsTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPE_INGREDIENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PANTRY_ITEMS);

        onCreate(db);
    }

    // CREATE
    public long addPantryItem(PantryItem item) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_PANTRY_NAME, item.getName());
        values.put(COLUMN_PANTRY_QUANTITY, item.getQuantity());
        values.put(COLUMN_PANTRY_UNIT, item.getUnit());
        values.put(COLUMN_PANTRY_EXPIRY, item.getExpiryDate());

        long id = db.insert(TABLE_PANTRY_ITEMS, null, values);

        db.close();

        return id;
    }

    // READ
    public List<PantryItem> getAllPantryItems() {

        List<PantryItem> pantryItems = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_PANTRY_ITEMS,
                null,
                null,
                null,
                null,
                null,
                COLUMN_PANTRY_NAME + " ASC"
        );

        if (cursor.moveToFirst()) {

            do {

                int id = cursor.getInt(
                        cursor.getColumnIndexOrThrow(COLUMN_PANTRY_ID)
                );

                String name = cursor.getString(
                        cursor.getColumnIndexOrThrow(COLUMN_PANTRY_NAME)
                );

                double quantity = cursor.getDouble(
                        cursor.getColumnIndexOrThrow(COLUMN_PANTRY_QUANTITY)
                );

                String unit = cursor.getString(
                        cursor.getColumnIndexOrThrow(COLUMN_PANTRY_UNIT)
                );

                String expiryDate = cursor.getString(
                        cursor.getColumnIndexOrThrow(COLUMN_PANTRY_EXPIRY)
                );

                PantryItem item = new PantryItem(
                        id,
                        name,
                        quantity,
                        unit,
                        expiryDate
                );

                pantryItems.add(item);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return pantryItems;
    }

    // UPDATE
    public int updatePantryItem(PantryItem item) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_PANTRY_NAME, item.getName());
        values.put(COLUMN_PANTRY_QUANTITY, item.getQuantity());
        values.put(COLUMN_PANTRY_UNIT, item.getUnit());
        values.put(COLUMN_PANTRY_EXPIRY, item.getExpiryDate());

        int rowsAffected = db.update(
                TABLE_PANTRY_ITEMS,
                values,
                COLUMN_PANTRY_ID + " = ?",
                new String[]{String.valueOf(item.getId())}
        );

        db.close();

        return rowsAffected;
    }

    // DELETE
    public int deletePantryItem(int id) {

        SQLiteDatabase db = this.getWritableDatabase();

        int rowsAffected = db.delete(
                TABLE_PANTRY_ITEMS,
                COLUMN_PANTRY_ID + " = ?",
                new String[]{String.valueOf(id)}
        );

        db.close();

        return rowsAffected;
    }

    public long addRecipe(Recipe recipe) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("name", recipe.getName());
        values.put("instructions", recipe.getInstructions());

        return db.insert("recipes", null, values);
    }

    public long addRecipeIngredient(RecipeIngredient ingredient) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("recipe_id", ingredient.getRecipeId());
        values.put("ingredient_name", ingredient.getIngredientName());
        values.put("quantity", ingredient.getQuantity());
        values.put("unit", ingredient.getUnit());

        return db.insert(
                "recipe_ingredients",
                null,
                values
        );
    }

    public List<Recipe> getAllRecipes() {

        List<Recipe> recipes = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(
                "recipes",
                null,
                null,
                null,
                null,
                null,
                "name ASC"
        );

        if (cursor.moveToFirst()) {

            do {

                int id =
                        cursor.getInt(
                                cursor.getColumnIndexOrThrow("id")
                        );

                String name =
                        cursor.getString(
                                cursor.getColumnIndexOrThrow("name")
                        );

                String instructions =
                        cursor.getString(
                                cursor.getColumnIndexOrThrow("instructions")
                        );

                recipes.add(
                        new Recipe(
                                id,
                                name,
                                instructions
                        )
                );

            } while (cursor.moveToNext());
        }

        cursor.close();

        return recipes;
    }

    public List<RecipeIngredient> getRecipeIngredients(
            int recipeId) {

        List<RecipeIngredient> ingredients =
                new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(
                "recipe_ingredients",
                null,
                "recipe_id = ?",
                new String[]{
                        String.valueOf(recipeId)
                },
                null,
                null,
                "id ASC"
        );

        if (cursor.moveToFirst()) {

            do {

                int id =
                        cursor.getInt(
                                cursor.getColumnIndexOrThrow("id")
                        );

                String ingredientName =
                        cursor.getString(
                                cursor.getColumnIndexOrThrow(
                                        "ingredient_name"
                                )
                        );

                double quantity =
                        cursor.getDouble(
                                cursor.getColumnIndexOrThrow(
                                        "quantity"
                                )
                        );

                String unit =
                        cursor.getString(
                                cursor.getColumnIndexOrThrow("unit")
                        );

                ingredients.add(
                        new RecipeIngredient(
                                id,
                                recipeId,
                                ingredientName,
                                quantity,
                                unit
                        )
                );

            } while (cursor.moveToNext());
        }

        cursor.close();

        return ingredients;
    }

    public boolean recipesExist() {

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT COUNT(*) FROM recipes",
                null
        );

        boolean exists = false;

        if (cursor.moveToFirst()) {
            exists = cursor.getInt(0) > 0;
        }

        cursor.close();

        return exists;
    }

}