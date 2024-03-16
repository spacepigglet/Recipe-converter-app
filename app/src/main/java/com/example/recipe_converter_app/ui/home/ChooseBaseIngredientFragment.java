package com.example.recipe_converter_app.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.recipe_converter_app.databinding.FragmentChooseBaseIngredientBinding;
import com.example.recipe_converter_app.databinding.FragmentAddIngredientsBinding;

public class ChooseBaseIngredientFragment extends Fragment {
    private NewRecipeViewModel viewModel;
    private FragmentChooseBaseIngredientBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity()).get(NewRecipeViewModel.class);

        binding = FragmentChooseBaseIngredientBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        return root;

    }

}
