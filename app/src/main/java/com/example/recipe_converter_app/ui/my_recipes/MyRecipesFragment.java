package com.example.recipe_converter_app.ui.my_recipes;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.recipe_converter_app.R;
import com.example.recipe_converter_app.databinding.FragmentMyRecipesBinding;
import com.example.recipe_converter_app.ui.dialog.ConfirmDeleteFragment;
import com.example.recipe_converter_app.logic.DeleteDialogListener;
import com.example.recipe_converter_app.logic.Recipe;
import com.example.recipe_converter_app.logic.RecyclerViewInterface;
import com.example.recipe_converter_app.logic.RecipeRecyclerViewAdapter;
import com.example.recipe_converter_app.util.VibrationUtil;

import java.util.ArrayList;


public class MyRecipesFragment extends Fragment implements RecyclerViewInterface, DeleteDialogListener {

    private FragmentMyRecipesBinding binding;
    private MyRecipesViewModel myRecipesViewModel;
    private ArrayList<Recipe> recipeCardModels = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        myRecipesViewModel =
                new ViewModelProvider(requireActivity()).get(MyRecipesViewModel.class);

        binding = FragmentMyRecipesBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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
        VibrationUtil.tick(requireContext());
        Recipe recipe = recipeCardModels.get(position);
        myRecipesViewModel.setSelectedRecipeCard(recipe);
        Log.d("debug view recipe", "MyRecipesFragment onItemClicked recipe card id: " + recipe);
        NavHostFragment.findNavController(MyRecipesFragment.this)
                .navigate(R.id.action_MyRecipesFragment_to_ViewRecipeFragment);
    }

    @Override
    public void onItemLongClicked(int position) {

        ConfirmDeleteFragment dialog = new ConfirmDeleteFragment(this);
        dialog.show(getChildFragmentManager(), "delete");

        //save recipe in case it needs to be deleted.
        Recipe recipe = recipeCardModels.get(position);
        myRecipesViewModel.setRecipeCardToDelete(recipe);

    }

    @Override
    public void onConfirmation(boolean confirmed) {
        Toast.makeText(getContext(), "delete recipe", Toast.LENGTH_SHORT).show();
        myRecipesViewModel.deleteSetRecipe();
    }

}