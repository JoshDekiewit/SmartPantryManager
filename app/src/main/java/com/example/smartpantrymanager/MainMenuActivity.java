package com.example.smartpantrymanager;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Toolbar toolbar = findViewById(R.id.toolbarMainMenu);
        setSupportActionBar(toolbar);

        if (toolbar.getBackground() instanceof ColorDrawable) {
            int toolbarColor =
                    ((ColorDrawable) toolbar.getBackground()).getColor();

            Window window = getWindow();
            window.setStatusBarColor(toolbarColor);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemId = item.getItemId();

        if (itemId == R.id.menu_my_pantry) {

            Intent intent = new Intent(
                    MainMenuActivity.this,
                    PantryListActivity.class
            );
            startActivity(intent);
            return true;

        } else if (itemId == R.id.menu_all_recipes) {

            Intent intent = new Intent(
                    MainMenuActivity.this,
                    AllRecipesActivity.class
            );
            startActivity(intent);
            return true;

        } else if (itemId == R.id.menu_suggested_recipes) {

            Intent intent = new Intent(
                    MainMenuActivity.this,
                    SuggestedRecipesActivity.class
            );
            startActivity(intent);
            return true;

        } else if (itemId == R.id.menu_settings) {

            Intent intent = new Intent(
                    MainMenuActivity.this,
                    SettingsActivity.class
            );
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}