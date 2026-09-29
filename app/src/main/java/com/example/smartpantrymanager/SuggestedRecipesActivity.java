package com.example.smartpantrymanager;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartpantrymanager.adapter.RecipeAdapter;
import com.example.smartpantrymanager.database.DatabaseHelper;
import com.example.smartpantrymanager.model.PantryItem;
import com.example.smartpantrymanager.model.Recipe;
import com.example.smartpantrymanager.model.RecipeIngredient;

import java.util.ArrayList;
import java.util.List;

public class SuggestedRecipesActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private RecyclerView recyclerViewRecipes;
    private TextView textViewNoRecipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggested_recipes);

        databaseHelper = new DatabaseHelper(this);

        recyclerViewRecipes =
                findViewById(R.id.recyclerViewRecipes);

        textViewNoRecipes =
                findViewById(R.id.textViewNoRecipes);

        recyclerViewRecipes.setLayoutManager(
                new LinearLayoutManager(this)
        );

        loadSuggestedRecipes();
    }

    private void loadSuggestedRecipes() {

        List<Recipe> allRecipes =
                databaseHelper.getAllRecipes();

        List<PantryItem> pantryItems =
                databaseHelper.getAllPantryItems();

        List<Recipe> suggestedRecipes =
                new ArrayList<>();

        for (Recipe recipe : allRecipes) {

            List<RecipeIngredient> ingredients =
                    databaseHelper.getRecipeIngredients(
                            recipe.getId()
                    );

            if (IngredientMatcher.canMakeRecipe(
                    recipe,
                    ingredients,
                    pantryItems)) {

                suggestedRecipes.add(recipe);
            }
        }

        RecipeAdapter recipeAdapter =
                new RecipeAdapter(suggestedRecipes);

        recyclerViewRecipes.setAdapter(recipeAdapter);

        if (suggestedRecipes.isEmpty()) {

            textViewNoRecipes.setVisibility(
                    TextView.VISIBLE
            );

            recyclerViewRecipes.setVisibility(
                    RecyclerView.GONE
            );

        } else {

            textViewNoRecipes.setVisibility(
                    TextView.GONE
            );

            recyclerViewRecipes.setVisibility(
                    RecyclerView.VISIBLE
            );
        }
    }
}