package com.example.recipe_converter_app.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;


import com.example.recipe_converter_app.R;
import com.example.recipe_converter_app.databinding.FragmentHomeBinding;
import com.example.recipe_converter_app.util.VibrationUtil;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    NewRecipeViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        viewModel = new ViewModelProvider(requireActivity()).get(NewRecipeViewModel.class);

        binding.newRecipeButton.setOnClickListener(view -> {
            String title = binding.editRecipeTitle.getText().toString();
            if(!title.equals("")){

                //viewModel.setText(title);
                viewModel.newRecipe(title);
                VibrationUtil.tick(requireContext());
                NavHostFragment.findNavController(HomeFragment.this)
                        .navigate(R.id.action_HomeFragment_to_AddIngredientsFragment);
            }else{
                Toast.makeText(getContext(), "The new recipe needs a title!", Toast.LENGTH_SHORT).show();
            }


        });

        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}