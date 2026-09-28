package com.example.smartpantrymanager;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartpantrymanager.adapter.PantryAdapter;
import com.example.smartpantrymanager.database.DatabaseHelper;
import com.example.smartpantrymanager.model.PantryItem;

import java.util.List;

public class PantryListActivity extends AppCompatActivity {

    private RecyclerView recyclerViewPantry;
    private PantryAdapter pantryAdapter;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantry_list);

        // Initialise the database helper first
        databaseHelper = new DatabaseHelper(this);

        // Find the RecyclerView
        recyclerViewPantry = findViewById(R.id.recyclerViewPantry);

        // Set up the RecyclerView
        recyclerViewPantry.setLayoutManager(
                new LinearLayoutManager(this)
        );

        // Set up the Add button
        findViewById(R.id.buttonAddIngredient).setOnClickListener(view -> {

            Intent intent = new Intent(
                    PantryListActivity.this,
                    AddEditIngredientActivity.class
            );

            startActivity(intent);
        });

        // Load pantry items after the database has been initialised
        loadPantryItems();
    }

    private void loadPantryItems() {

        List<PantryItem> pantryItems =
                databaseHelper.getAllPantryItems();

        pantryAdapter = new PantryAdapter(pantryItems);

        recyclerViewPantry.setAdapter(pantryAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (databaseHelper != null && recyclerViewPantry != null) {
            loadPantryItems();
        }
    }
}