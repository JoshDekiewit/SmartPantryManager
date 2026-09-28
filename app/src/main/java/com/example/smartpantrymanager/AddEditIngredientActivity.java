package com.example.smartpantrymanager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartpantrymanager.database.DatabaseHelper;
import com.example.smartpantrymanager.model.PantryItem;

import java.util.List;

public class AddEditIngredientActivity extends AppCompatActivity {

    private EditText editTextIngredientName;
    private EditText editTextQuantity;
    private EditText editTextUnit;
    private EditText editTextExpiry;

    private DatabaseHelper databaseHelper;

    private int ingredientId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_ingredient);

        editTextIngredientName =
                findViewById(R.id.editTextIngredientName);

        editTextQuantity =
                findViewById(R.id.editTextQuantity);

        editTextUnit =
                findViewById(R.id.editTextUnit);

        editTextExpiry =
                findViewById(R.id.editTextExpiry);

        Button buttonSaveIngredient =
                findViewById(R.id.buttonSaveIngredient);

        databaseHelper = new DatabaseHelper(this);

        ingredientId =
                getIntent().getIntExtra("ingredient_id", -1);

        if (ingredientId != -1) {
            loadIngredientForEditing();
        }

        buttonSaveIngredient.setOnClickListener(
                view -> saveIngredient()
        );
    }

    private void loadIngredientForEditing() {

        List<PantryItem> pantryItems =
                databaseHelper.getAllPantryItems();

        for (PantryItem item : pantryItems) {

            if (item.getId() == ingredientId) {

                editTextIngredientName.setText(
                        item.getName()
                );

                editTextQuantity.setText(
                        String.valueOf(item.getQuantity())
                );

                editTextUnit.setText(
                        item.getUnit()
                );

                if (item.getExpiryDate() != null) {
                    editTextExpiry.setText(
                            item.getExpiryDate()
                    );
                }

                TextView title =
                        findViewById(R.id.textViewFormTitle);

                title.setText("Edit Ingredient");

                break;
            }
        }
    }

    private void saveIngredient() {

        String name =
                editTextIngredientName
                        .getText()
                        .toString()
                        .trim();

        String quantityText =
                editTextQuantity
                        .getText()
                        .toString()
                        .trim();

        String unit =
                editTextUnit
                        .getText()
                        .toString()
                        .trim();

        String expiry =
                editTextExpiry
                        .getText()
                        .toString()
                        .trim();

        if (name.isEmpty()) {
            editTextIngredientName.setError(
                    "Enter an ingredient name"
            );
            editTextIngredientName.requestFocus();
            return;
        }

        if (quantityText.isEmpty()) {
            editTextQuantity.setError(
                    "Enter a quantity"
            );
            editTextQuantity.requestFocus();
            return;
        }

        if (unit.isEmpty()) {
            editTextUnit.setError(
                    "Enter a unit"
            );
            editTextUnit.requestFocus();
            return;
        }

        double quantity;

        try {
            quantity = Double.parseDouble(quantityText);
        } catch (NumberFormatException e) {

            editTextQuantity.setError(
                    "Enter a valid quantity"
            );
            editTextQuantity.requestFocus();
            return;
        }

        if (quantity <= 0) {
            editTextQuantity.setError(
                    "Quantity must be greater than 0"
            );
            editTextQuantity.requestFocus();
            return;
        }

        PantryItem pantryItem = new PantryItem(
                name,
                quantity,
                unit,
                expiry.isEmpty() ? null : expiry
        );

        if (ingredientId == -1) {

            long id =
                    databaseHelper.addPantryItem(
                            pantryItem
                    );

            if (id != -1) {

                Toast.makeText(
                        this,
                        "Ingredient added",
                        Toast.LENGTH_SHORT
                ).show();

                finish();

            } else {

                Toast.makeText(
                        this,
                        "Failed to add ingredient",
                        Toast.LENGTH_SHORT
                ).show();
            }

        } else {

            pantryItem.setId(ingredientId);

            int rowsUpdated =
                    databaseHelper.updatePantryItem(
                            pantryItem
                    );

            if (rowsUpdated > 0) {

                Toast.makeText(
                        this,
                        "Ingredient updated",
                        Toast.LENGTH_SHORT
                ).show();

                finish();

            } else {

                Toast.makeText(
                        this,
                        "Failed to update ingredient",
                        Toast.LENGTH_SHORT
                ).show();
            }
        }
    }
}