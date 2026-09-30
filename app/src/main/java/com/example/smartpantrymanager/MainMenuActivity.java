package com.example.smartpantrymanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainMenuActivity extends AppCompatActivity {

    private Button buttonMyPantry;
    private Button buttonAllRecipes;
    private Button buttonSuggestedRecipes;
    private Button buttonSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        buttonMyPantry = findViewById(R.id.buttonMyPantry);
        buttonAllRecipes = findViewById(R.id.buttonAllRecipes);
        buttonSuggestedRecipes = findViewById(R.id.buttonSuggestedRecipes);
        buttonSettings = findViewById(R.id.buttonSettings);

        buttonMyPantry.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainMenuActivity.this,
                    PantryListActivity.class
            );
            startActivity(intent);
        });

        buttonAllRecipes.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainMenuActivity.this,
                    AllRecipesActivity.class
            );
            startActivity(intent);
        });

        buttonSuggestedRecipes.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainMenuActivity.this,
                    SuggestedRecipesActivity.class
            );
            startActivity(intent);
        });

        buttonSettings.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainMenuActivity.this,
                    SettingsActivity.class
            );
            startActivity(intent);
        });
    }
}