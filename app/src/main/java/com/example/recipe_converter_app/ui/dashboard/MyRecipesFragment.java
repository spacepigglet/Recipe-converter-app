package com.example.recipe_converter_app.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.recipe_converter_app.R;
import com.example.recipe_converter_app.databinding.FragmentMyRecipesBinding;
import com.example.recipe_converter_app.logic.Recipe;
import com.example.recipe_converter_app.logic.RecyclerViewInterface;
import com.example.recipe_converter_app.ui.RecipeRecyclerViewAdapter;

import java.util.ArrayList;


public class MyRecipesFragment extends Fragment implements RecyclerViewInterface {

    private FragmentMyRecipesBinding binding;
    private MyRecipesViewModel myRecipesViewModel;
    private ArrayList<Recipe> recipeCardModels = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        myRecipesViewModel =
                new ViewModelProvider(requireActivity()).get(MyRecipesViewModel.class);

        binding = FragmentMyRecipesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        setupRecipeCardModels();
        //setup must be done before adapter
        RecipeRecyclerViewAdapter adapter = new RecipeRecyclerViewAdapter(getContext(), recipeCardModels, this);
        binding.recipeRecyclerView.setAdapter(adapter);
        binding.recipeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Show message if no recipes found
        if (recipeCardModels.isEmpty()) {
            binding.noRecipesMessage.setVisibility(View.VISIBLE);
        } else {
            binding.noRecipesMessage.setVisibility(View.GONE);
        }

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

    @Override
    public void onItemClicked(int position) {
        Recipe recipe = recipeCardModels.get(position);
        myRecipesViewModel.setSelectedRecipeCard(recipe);
        Log.d("debug view recipe", "MyRecipesFragment onItemClicked recipe card id: " + recipe);
        NavHostFragment.findNavController(MyRecipesFragment.this)
                .navigate(R.id.action_MyRecipesFragment_to_ViewRecipeFragment);
    }

}