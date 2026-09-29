package com.example.smartpantrymanager;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartpantrymanager.database.DatabaseHelper;
import com.example.smartpantrymanager.model.Recipe;
import com.example.smartpantrymanager.model.RecipeIngredient;

import java.util.List;

public class RecipeDetailActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

    private TextView textViewRecipeDetailName;
    private TextView textViewRecipeIngredients;
    private TextView textViewRecipeInstructions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        databaseHelper = new DatabaseHelper(this);

        textViewRecipeDetailName =
                findViewById(R.id.textViewRecipeDetailName);

        textViewRecipeIngredients =
                findViewById(R.id.textViewRecipeIngredients);

        textViewRecipeInstructions =
                findViewById(R.id.textViewRecipeInstructions);

        int recipeId =
                getIntent().getIntExtra("recipe_id", -1);

        if (recipeId != -1) {
            loadRecipe(recipeId);
        }
    }

    private void loadRecipe(int recipeId) {

        List<Recipe> recipes =
                databaseHelper.getAllRecipes();

        for (Recipe recipe : recipes) {

            if (recipe.getId() == recipeId) {

                textViewRecipeDetailName.setText(
                        recipe.getName()
                );

                textViewRecipeInstructions.setText(
                        recipe.getInstructions()
                );

                loadIngredients(recipeId);

                break;
            }
        }
    }

    private void loadIngredients(int recipeId) {

        List<RecipeIngredient> ingredients =
                databaseHelper.getRecipeIngredients(recipeId);

        StringBuilder ingredientText =
                new StringBuilder();

        for (RecipeIngredient ingredient : ingredients) {

            ingredientText
                    .append("• ")
                    .append(ingredient.getQuantity())
                    .append(" ")
                    .append(ingredient.getUnit())
                    .append(" ")
                    .append(ingredient.getIngredientName())
                    .append("\n");
        }

        textViewRecipeIngredients.setText(
                ingredientText.toString()
        );
    }
}