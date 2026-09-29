package com.example.smartpantrymanager;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartpantrymanager.adapter.RecipeAdapter;
import com.example.smartpantrymanager.database.DatabaseHelper;
import com.example.smartpantrymanager.model.Recipe;

import java.util.List;

public class AllRecipesActivity extends AppCompatActivity {

    private RecyclerView recyclerViewAllRecipes;
    private DatabaseHelper databaseHelper;
    private RecipeAdapter recipeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_recipes);

        databaseHelper = new DatabaseHelper(this);

        recyclerViewAllRecipes = findViewById(R.id.recyclerViewAllRecipes);

        recyclerViewAllRecipes.setLayoutManager(
                new LinearLayoutManager(this)
        );

        loadRecipes();
    }

    private void loadRecipes() {

        List<Recipe> recipes = databaseHelper.getAllRecipes();

        recipeAdapter = new RecipeAdapter(recipes);

        recyclerViewAllRecipes.setAdapter(recipeAdapter);
    }
}