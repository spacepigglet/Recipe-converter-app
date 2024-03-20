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
import com.example.recipe_converter_app.logic.Unit;
import com.example.recipe_converter_app.util.VibrationUtil;

public class AddIngredientsFragment extends Fragment {
    private NewRecipeViewModel newRecipeViewModel;
    private FragmentAddIngredientsBinding binding;
    private int defaultUnitPosition;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        newRecipeViewModel = new ViewModelProvider(requireActivity()).get(NewRecipeViewModel.class);

        binding = FragmentAddIngredientsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        displayTitle();
        //in case the user backed up from the next step to add more
        displayCurrentRecipe();
        setupUnitSpinner();
        binding.addButton.setOnClickListener(view -> addIngredient());
        binding.nextButton.setOnClickListener(view -> {
            VibrationUtil.tick(requireContext());

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
        if(sharedPref != null){
            // Default to the ordinal value of Unit.GRAM if no preference is set
            int defaultUnitPosition = sharedPref.getInt(getString(R.string.default_unit_position_key), Unit.GRAM.ordinal());

            return defaultUnitPosition;
        }
        return Unit.GRAM.ordinal();
    }

    private void addIngredient() {
        VibrationUtil.tick(requireContext());
        String name = binding.editIngredient.getText().toString().trim().toLowerCase();
        String amountString = binding.editAmount.getText().toString();
        Object unitObject = binding.unitSpinner.getSelectedItem();

        if(name.equals("") || amountString.equals("") || unitObject == null){
            Toast.makeText(getContext(), getString(R.string.toast_empty_fields), Toast.LENGTH_SHORT).show();
        }else{
            newRecipeViewModel.addIngredient(name, Float.parseFloat(amountString), (Unit) unitObject);
            resetFields();
            displayCurrentRecipe();

        }
    }

    private void displayCurrentRecipe() {
        binding.nextButton.setEnabled(true);
        binding.currentRecipe.setText(newRecipeViewModel.getRecipe().getIngredientsString());
    }

    private void resetFields(){
        binding.editIngredient.setText("");
        //set focus to top row
        binding.editIngredient.requestFocus();
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
