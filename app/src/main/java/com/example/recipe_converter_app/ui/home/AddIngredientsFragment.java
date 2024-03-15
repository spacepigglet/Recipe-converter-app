package com.example.recipe_converter_app.ui.home;

import android.R.layout;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.recipe_converter_app.R;
import com.example.recipe_converter_app.databinding.FragmentAddIngredientsBinding;
import com.example.recipe_converter_app.logic.Unit;

import java.util.List;

public class AddIngredientsFragment extends Fragment {
    NewRecipeViewModel viewModel;
    private FragmentAddIngredientsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

                //new ViewModelProvider(this).get(HomeViewModel.class);
        viewModel = new ViewModelProvider(requireActivity()).get(NewRecipeViewModel.class);
        /*viewModel.getFilteredList().observe(getViewLifecycleOwner(), list -> {
            // Update the list UI.
        });*/

        binding = FragmentAddIngredientsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        displayTitle();
        binding.addButton.setOnClickListener(view -> addIngredient());
        binding.unitsSpinner.setAdapter(new ArrayAdapter<Unit>(getContext(), layout.simple_spinner_item, Unit.values()));

        //final EditText editIngredient = binding.editIngredient;
        //homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    private void addIngredient() {
        String name = binding.editIngredient.getText().toString();
        String amountString = binding.editAmount.getText().toString();
        Object unitObject = binding.unitsSpinner.getSelectedItem();

        if(name.equals("") || amountString.equals("") || unitObject == null){
            Toast.makeText(getContext(), "All fields must be filled in!", Toast.LENGTH_SHORT).show();
        }else{
            boolean wasAdded = viewModel.addIngredient(name, Float.parseFloat(amountString), (Unit) unitObject);
            if(wasAdded){
                resetFields();
                displayCurrentRecipe();
            }
        }
    }

    private void displayCurrentRecipe() {
        binding.nextButton.setVisibility(View.VISIBLE);
        binding.currentRecipe.append(viewModel.getCurrentIngredient().toString() + "\n");
    }

    private void resetFields(){
        binding.editIngredient.setText("");
        binding.editAmount.setText("");
        binding.unitsSpinner.setSelection(0);
    }

    private void displayTitle() {
        String title = viewModel.getRecipe().getRecipeName();
        String textToDisplay = getString(R.string.recipe) + " " + title ;
        binding.recipeTitle.setText(textToDisplay);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
