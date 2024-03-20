package com.example.recipe_converter_app.ui.home;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.recipe_converter_app.R;
import com.example.recipe_converter_app.databinding.FragmentChooseBaseIngredientBinding;
import com.example.recipe_converter_app.logic.Ingredient;
import com.example.recipe_converter_app.logic.Recipe;
import com.example.recipe_converter_app.logic.Unit;
import com.example.recipe_converter_app.util.VibrationUtil;
import com.google.android.material.internal.TextWatcherAdapter;

import java.util.List;

public class ChooseBaseIngredientFragment extends Fragment {
    private NewRecipeViewModel viewModel;
    private FragmentChooseBaseIngredientBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity()).get(NewRecipeViewModel.class);

        binding = FragmentChooseBaseIngredientBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        addRadioButtons();
        binding.recipeTitle.setText(viewModel.getRecipe().getName());
        setEditTextListener();
        binding.generateRecipeButton.setOnClickListener(view -> generateRecipe());

        return root;

    }

    private void generateRecipe() {
        String newAmountStr = binding.editNewAmount.getText().toString();
        if(newAmountStr.equals("")){
            Toast.makeText(getContext(), "Input new base amount!", Toast.LENGTH_SHORT).show();
        }else{
            VibrationUtil.tick(requireContext());
            //have to set newBaseIngredientAmount before generating recipe
            viewModel.setNewBaseIngredientAmount(Float.parseFloat(newAmountStr));
            viewModel.generateRecipe(getContext());
            Toast.makeText(getContext(), "New recipe generated!\nSee\"My Recipes\"", Toast.LENGTH_SHORT).show();
            NavHostFragment.findNavController(ChooseBaseIngredientFragment.this)
                    .navigate(R.id.action_ChooseBaseIngredientFragment_to_HomeFragment);
        }

    }


    private void addRadioButtons() {
        //get string array from source
        Recipe recipe = viewModel.getRecipe();
        List<Ingredient> ingredients =  recipe.getIngredients();

        //create radio buttons
        for (int i = 0; i < ingredients.size(); i++) {
            RadioButton radioButton = new RadioButton(getContext());
            radioButton.setText(ingredients.get(i).getName());
            radioButton.setTextSize(20);
            radioButton.setId(i);
            binding.radioGroup.addView(radioButton);
        }
        setRadioGroupListener();


    }

    private void setRadioGroupListener() {
        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRadioButton = group.findViewById(checkedId);
                if (checkedRadioButton != null) {
                    String selectedIngredientName = checkedRadioButton.getText().toString();
                    Ingredient ingredient = viewModel.getRecipe().getIngredient(selectedIngredientName);
                    if(ingredient != null){
                        viewModel.setOriginalBaseIngredientAmount(ingredient.getAmount());
                        binding.editNewAmount.setEnabled(true);
                        Unit unit = ingredient.getUnit();
                        binding.unit.setText(unit.getName());

                    }
                }
            }
        });
    }
    private void setEditTextListener(){
        binding.editNewAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void afterTextChanged(Editable editable) {
                binding.generateRecipeButton.setEnabled(true);
            }
        });
    }

}
