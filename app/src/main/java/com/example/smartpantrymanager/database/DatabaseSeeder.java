package com.example.smartpantrymanager.database;

import com.example.smartpantrymanager.model.Recipe;
import com.example.smartpantrymanager.model.RecipeIngredient;

public class DatabaseSeeder {

    private final DatabaseHelper databaseHelper;

    public DatabaseSeeder(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public void seedRecipes() {

        if (databaseHelper.recipesExist()) {
            return;
        }

        // 1. Pancakes
        addRecipe(
                "Pancakes",
                "Mix the flour, milk, eggs and sugar until smooth. "
                        + "Heat a pan and cook the pancakes on both sides.",
                new IngredientData[]{
                        new IngredientData("flour", 1, "cup"),
                        new IngredientData("milk", 1, "cup"),
                        new IngredientData("eggs", 2, "units"),
                        new IngredientData("sugar", 1, "tbsp")
                }
        );

        // 2. Omelette
        addRecipe(
                "Omelette",
                "Beat the eggs with milk. Pour into a heated pan "
                        + "and add cheese. Cook until set.",
                new IngredientData[]{
                        new IngredientData("eggs", 2, "units"),
                        new IngredientData("milk", 0.5, "cup"),
                        new IngredientData("cheese", 0.25, "cup")
                }
        );

        // 3. French Toast
        addRecipe(
                "French Toast",
                "Whisk the eggs and milk together. Dip the bread "
                        + "into the mixture and fry until golden.",
                new IngredientData[]{
                        new IngredientData("bread", 2, "slices"),
                        new IngredientData("eggs", 2, "units"),
                        new IngredientData("milk", 0.5, "cup"),
                        new IngredientData("sugar", 1, "tbsp")
                }
        );

        // 4. Scrambled Eggs
        addRecipe(
                "Scrambled Eggs",
                "Beat the eggs with milk. Cook in a pan while "
                        + "stirring until the eggs are fully cooked.",
                new IngredientData[]{
                        new IngredientData("eggs", 2, "units"),
                        new IngredientData("milk", 0.25, "cup"),
                        new IngredientData("butter", 1, "tbsp")
                }
        );

        // 5. Grilled Cheese Sandwich
        addRecipe(
                "Grilled Cheese Sandwich",
                "Place cheese between two slices of bread. "
                        + "Butter the outside and cook in a pan "
                        + "until the bread is golden and the cheese melts.",
                new IngredientData[]{
                        new IngredientData("bread", 2, "slices"),
                        new IngredientData("cheese", 2, "slices"),
                        new IngredientData("butter", 1, "tbsp")
                }
        );

        // 6. Tomato Sandwich
        addRecipe(
                "Tomato Sandwich",
                "Slice the tomato and place it between slices "
                        + "of bread with cheese. Serve fresh.",
                new IngredientData[]{
                        new IngredientData("bread", 2, "slices"),
                        new IngredientData("tomato", 1, "unit"),
                        new IngredientData("cheese", 1, "slice")
                }
        );

        // 7. Egg Sandwich
        addRecipe(
                "Egg Sandwich",
                "Cook the eggs and place them between two slices "
                        + "of bread. Add cheese and serve.",
                new IngredientData[]{
                        new IngredientData("bread", 2, "slices"),
                        new IngredientData("eggs", 2, "units"),
                        new IngredientData("cheese", 1, "slice")
                }
        );

        // 8. Pasta with Tomato Sauce
        addRecipe(
                "Pasta with Tomato Sauce",
                "Cook the pasta. Prepare the tomato sauce in a pan "
                        + "and combine it with the cooked pasta.",
                new IngredientData[]{
                        new IngredientData("pasta", 200, "g"),
                        new IngredientData("tomato", 2, "units"),
                        new IngredientData("onion", 1, "unit"),
                        new IngredientData("olive oil", 1, "tbsp")
                }
        );

        // 9. Tomato Pasta
        addRecipe(
                "Tomato Pasta",
                "Cook the pasta until tender. Cook the tomatoes "
                        + "with olive oil and combine with the pasta.",
                new IngredientData[]{
                        new IngredientData("pasta", 200, "g"),
                        new IngredientData("tomato", 2, "units"),
                        new IngredientData("olive oil", 1, "tbsp")
                }
        );

        // 10. Egg Fried Rice
        addRecipe(
                "Egg Fried Rice",
                "Cook the eggs in a pan. Add cooked rice and "
                        + "vegetables and stir-fry until hot.",
                new IngredientData[]{
                        new IngredientData("rice", 2, "cups"),
                        new IngredientData("eggs", 2, "units"),
                        new IngredientData("carrot", 1, "unit"),
                        new IngredientData("oil", 1, "tbsp")
                }
        );

        // 11. Chicken Rice
        addRecipe(
                "Chicken Rice",
                "Cook the chicken thoroughly. Serve it with "
                        + "cooked rice and vegetables.",
                new IngredientData[]{
                        new IngredientData("chicken", 200, "g"),
                        new IngredientData("rice", 2, "cups"),
                        new IngredientData("carrot", 1, "unit"),
                        new IngredientData("onion", 1, "unit")
                }
        );

        // 12. Chicken Sandwich
        addRecipe(
                "Chicken Sandwich",
                "Cook the chicken and slice it. Place it between "
                        + "two slices of bread with tomato and cheese.",
                new IngredientData[]{
                        new IngredientData("chicken", 150, "g"),
                        new IngredientData("bread", 2, "slices"),
                        new IngredientData("tomato", 1, "unit"),
                        new IngredientData("cheese", 1, "slice")
                }
        );

        // 13. Mashed Potatoes
        addRecipe(
                "Mashed Potatoes",
                "Boil the potatoes until soft. Mash them with "
                        + "butter and milk until smooth.",
                new IngredientData[]{
                        new IngredientData("potatoes", 4, "units"),
                        new IngredientData("butter", 2, "tbsp"),
                        new IngredientData("milk", 0.5, "cup")
                }
        );

        // 14. Potato Omelette
        addRecipe(
                "Potato Omelette",
                "Cook the potatoes until tender. Add them to beaten "
                        + "eggs and cook in a pan until set.",
                new IngredientData[]{
                        new IngredientData("potatoes", 2, "units"),
                        new IngredientData("eggs", 3, "units"),
                        new IngredientData("onion", 1, "unit"),
                        new IngredientData("oil", 1, "tbsp")
                }
        );

        // 15. Simple Salad
        addRecipe(
                "Simple Salad",
                "Chop the vegetables and combine them in a bowl. "
                        + "Add olive oil and serve.",
                new IngredientData[]{
                        new IngredientData("tomato", 2, "units"),
                        new IngredientData("cucumber", 1, "unit"),
                        new IngredientData("lettuce", 1, "head"),
                        new IngredientData("olive oil", 1, "tbsp")
                }
        );
    }

    private void addRecipe(
            String name,
            String instructions,
            IngredientData[] ingredients) {

        Recipe recipe = new Recipe(
                name,
                instructions
        );

        long recipeId =
                databaseHelper.addRecipe(recipe);

        if (recipeId == -1) {
            return;
        }

        for (IngredientData ingredient : ingredients) {

            RecipeIngredient recipeIngredient =
                    new RecipeIngredient(
                            (int) recipeId,
                            ingredient.name,
                            ingredient.quantity,
                            ingredient.unit
                    );

            databaseHelper.addRecipeIngredient(
                    recipeIngredient
            );
        }
    }

    private static class IngredientData {

        String name;
        double quantity;
        String unit;

        IngredientData(
                String name,
                double quantity,
                String unit) {

            this.name = name;
            this.quantity = quantity;
            this.unit = unit;
        }
    }
}