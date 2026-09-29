package com.example.smartpantrymanager;

import com.example.smartpantrymanager.model.PantryItem;
import com.example.smartpantrymanager.model.Recipe;
import com.example.smartpantrymanager.model.RecipeIngredient;

import java.util.List;

public class IngredientMatcher {

    public static boolean canMakeRecipe(
            Recipe recipe,
            List<RecipeIngredient> recipeIngredients,
            List<PantryItem> pantryItems) {

        for (RecipeIngredient required : recipeIngredients) {

            boolean ingredientSatisfied = false;

            String requiredName =
                    IngredientNormalizer.normalize(
                            required.getIngredientName()
                    );

            for (PantryItem pantryItem : pantryItems) {

                String pantryName =
                        IngredientNormalizer.normalize(
                                pantryItem.getName()
                        );

                if (requiredName.equals(pantryName)) {

                    if (pantryItem.getQuantity()
                            >= required.getQuantity()) {

                        ingredientSatisfied = true;
                        break;
                    }
                }
            }

            if (!ingredientSatisfied) {
                return false;
            }
        }

        return true;
    }
}