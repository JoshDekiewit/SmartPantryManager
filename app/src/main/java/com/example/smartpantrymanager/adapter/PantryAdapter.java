package com.example.smartpantrymanager.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartpantrymanager.AddEditIngredientActivity;
import com.example.smartpantrymanager.R;
import com.example.smartpantrymanager.database.DatabaseHelper;
import com.example.smartpantrymanager.model.PantryItem;

import java.util.List;

public class PantryAdapter
        extends RecyclerView.Adapter<PantryAdapter.PantryViewHolder> {

    private final List<PantryItem> pantryItems;

    public PantryAdapter(List<PantryItem> pantryItems) {
        this.pantryItems = pantryItems;
    }

    @NonNull
    @Override
    public PantryViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pantry, parent, false);

        return new PantryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull PantryViewHolder holder,
            int position) {

        PantryItem item = pantryItems.get(position);

        holder.textViewIngredientName.setText(
                item.getName()
        );

        holder.textViewIngredientQuantity.setText(
                item.getQuantity() + " " + item.getUnit()
        );

        if (item.getExpiryDate() == null ||
                item.getExpiryDate().isEmpty()) {

            holder.textViewIngredientExpiry.setText(
                    "Expires: None"
            );

        } else {

            holder.textViewIngredientExpiry.setText(
                    "Expires: " + item.getExpiryDate()
            );
        }

        holder.buttonEdit.setOnClickListener(view -> {

            Context context = view.getContext();

            Intent intent = new Intent(
                    context,
                    AddEditIngredientActivity.class
            );

            intent.putExtra(
                    "ingredient_id",
                    item.getId()
            );

            context.startActivity(intent);
        });

        holder.buttonDelete.setOnClickListener(view -> {

            new AlertDialog.Builder(view.getContext())
                    .setTitle("Delete Ingredient")
                    .setMessage(
                            "Are you sure you want to delete "
                                    + item.getName()
                                    + "?"
                    )
                    .setPositiveButton(
                            "Delete",
                            (dialog, which) -> {

                                DatabaseHelper databaseHelper =
                                        new DatabaseHelper(
                                                view.getContext()
                                        );

                                int rowsDeleted =
                                        databaseHelper.deletePantryItem(
                                                item.getId()
                                        );

                                if (rowsDeleted > 0) {

                                    pantryItems.remove(position);

                                    notifyItemRemoved(position);

                                    Toast.makeText(
                                            view.getContext(),
                                            "Ingredient deleted",
                                            Toast.LENGTH_SHORT
                                    ).show();

                                } else {

                                    Toast.makeText(
                                            view.getContext(),
                                            "Failed to delete ingredient",
                                            Toast.LENGTH_SHORT
                                    ).show();
                                }
                            }
                    )
                    .setNegativeButton(
                            "Cancel",
                            null
                    )
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        return pantryItems.size();
    }

    public static class PantryViewHolder
            extends RecyclerView.ViewHolder {

        TextView textViewIngredientName;
        TextView textViewIngredientQuantity;
        TextView textViewIngredientExpiry;

        Button buttonEdit;
        Button buttonDelete;

        public PantryViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewIngredientName =
                    itemView.findViewById(
                            R.id.textViewIngredientName
                    );

            textViewIngredientQuantity =
                    itemView.findViewById(
                            R.id.textViewIngredientQuantity
                    );

            textViewIngredientExpiry =
                    itemView.findViewById(
                            R.id.textViewIngredientExpiry
                    );

            buttonEdit =
                    itemView.findViewById(
                            R.id.buttonEdit
                    );

            buttonDelete =
                    itemView.findViewById(
                            R.id.buttonDelete
                    );
        }
    }
}