package com.example.recipe_converter_app.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.recipe_converter_app.R;
import com.example.recipe_converter_app.databinding.FragmentAddIngredientsBinding;
import com.example.recipe_converter_app.logic.Ingredient;
import com.example.recipe_converter_app.logic.Unit;

public class AddIngredientsFragment extends Fragment {
    private NewRecipeViewModel newRecipeViewModel;
    private FragmentAddIngredientsBinding binding;
    private int defaultUnitPosition;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

                //new ViewModelProvider(this).get(HomeViewModel.class);
        newRecipeViewModel = new ViewModelProvider(requireActivity()).get(NewRecipeViewModel.class);
        /*viewModel.getFilteredList().observe(getViewLifecycleOwner(), list -> {
            // Update the list UI.
        });*/

        binding = FragmentAddIngredientsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        displayTitle();
        setupUnitSpinner();
        binding.addButton.setOnClickListener(view -> addIngredient());
        binding.nextButton.setOnClickListener(view -> {

            NavHostFragment.findNavController(AddIngredientsFragment.this)
                        .navigate(R.id.action_AddIngredientsFragment_to_ChooseBaseIngredientFragment);


        });

        return root;
    }
    private void setupUnitSpinner() {
        ArrayAdapter<Unit> adapter = new ArrayAdapter<>(requireContext(),
                R.layout.spinner_item, Unit.values());
        adapter.setDropDownViewResource(R.layout.spinner_dropdown);
        binding.unitSpinner.setAdapter(adapter);

        defaultUnitPosition = getDefaultUnitPosition();
        binding.unitSpinner.setSelection(defaultUnitPosition);
    }

    private int getDefaultUnitPosition() {
        SharedPreferences sharedPref = getActivity().getPreferences(getContext().MODE_PRIVATE);
        // Default to the ordinal value of Unit.GRAM if no preference is set
        int defaultUnitPosition = sharedPref.getInt(getString(R.string.default_unit_position_key), Unit.GRAM.ordinal());

        return defaultUnitPosition;
    }

    private void addIngredient() {
        String name = binding.editIngredient.getText().toString().trim().toLowerCase();
        String amountString = binding.editAmount.getText().toString();
        Object unitObject = binding.unitSpinner.getSelectedItem();

        if(name.equals("") || amountString.equals("") || unitObject == null){
            Toast.makeText(getContext(), "All fields must be filled in!", Toast.LENGTH_SHORT).show();
        }else{
            Ingredient addedIngredient = newRecipeViewModel.addIngredient(name, Float.parseFloat(amountString), (Unit) unitObject);
            resetFields();
            displayCurrentRecipe(addedIngredient);

        }
    }

    private void displayCurrentRecipe(Ingredient addedIngredient) {
        binding.nextButton.setEnabled(true);
        binding.currentRecipe.append(addedIngredient.toString() + "\n");
    }

    private void resetFields(){
        binding.editIngredient.setText("");
        binding.editAmount.setText("");
        binding.unitSpinner.setSelection(defaultUnitPosition);
    }

    private void displayTitle() {
        String title = newRecipeViewModel.getRecipe().getName();
        String textToDisplay = getString(R.string.recipe_text) + " " + title ;
        binding.recipeTitle.setText(textToDisplay);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
