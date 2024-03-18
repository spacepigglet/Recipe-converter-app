package com.example.recipe_converter_app.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.recipe_converter_app.databinding.FragmentViewRecipeBinding;
import com.example.recipe_converter_app.logic.Ingredient;
import com.example.recipe_converter_app.logic.Recipe;


public class ViewRecipeFragment extends Fragment {
    private MyRecipesViewModel myRecipesViewModel;
    FragmentViewRecipeBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        myRecipesViewModel =
                new ViewModelProvider(requireActivity()).get(MyRecipesViewModel.class);
        binding = FragmentViewRecipeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        displayRecipe();

        return root;
    }

    private void displayRecipe() {
        Recipe recipeCard = myRecipesViewModel.getSelectedRecipeCard();
        Recipe recipe = myRecipesViewModel.getRecipeFromDatabase(recipeCard.getId(), getContext());
        binding.recipeTitleTv.setText(recipe.getName());
        Log.d("debug view ingredients", "displayRecipe() ingredients list: " + recipe.getIngredients());
        for(Ingredient ingredient : recipe.getIngredients()){
            binding.ingredientsTv.append(ingredient + "\n");
        }
    }
}
