package com.example.recipe_converter_app.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.recipe_converter_app.databinding.FragmentMyRecipesBinding;
import com.example.recipe_converter_app.logic.Recipe;
import com.example.recipe_converter_app.ui.RecipeCardModel;

import java.util.ArrayList;


public class MyRecipesFragment extends Fragment {

    private FragmentMyRecipesBinding binding;
    private MyRecipesViewModel myRecipesViewModel;
    private ArrayList<RecipeCardModel> recipeCardModels = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        myRecipesViewModel =
                new ViewModelProvider(this).get(MyRecipesViewModel.class);

        binding = FragmentMyRecipesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        setupRecipeCardModels();

        return root;
    }

    private void setupRecipeCardModels() {
        ArrayList<Recipe> recipes = myRecipesViewModel.getAllRecipesFromDatabase(getContext());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}