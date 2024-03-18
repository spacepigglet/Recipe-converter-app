package com.example.recipe_converter_app.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.recipe_converter_app.databinding.FragmentMyRecipesBinding;
import com.example.recipe_converter_app.logic.Recipe;
import com.example.recipe_converter_app.ui.RecipeCardModel;
import com.example.recipe_converter_app.ui.RecipeRecyclerViewAdapter;

import java.util.ArrayList;


public class MyRecipesFragment extends Fragment {

    private FragmentMyRecipesBinding binding;
    private MyRecipesViewModel myRecipesViewModel;
    private ArrayList<Recipe> recipeCardModels = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        myRecipesViewModel =
                new ViewModelProvider(this).get(MyRecipesViewModel.class);

        binding = FragmentMyRecipesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        setupRecipeCardModels();
        //setup must be done before adapter
        RecipeRecyclerViewAdapter adapter = new RecipeRecyclerViewAdapter(getContext(), recipeCardModels);
        binding.recipeRecyclerView.setAdapter(adapter);
        binding.recipeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return root;
    }

    private void setupRecipeCardModels() {
        ArrayList<Recipe> recipes = myRecipesViewModel.getAllRecipesFromDatabase(getContext());
        recipeCardModels = recipes;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}