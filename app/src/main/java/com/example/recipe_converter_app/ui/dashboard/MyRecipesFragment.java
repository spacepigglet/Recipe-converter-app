package com.example.recipe_converter_app.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.recipe_converter_app.databinding.FragmentMyRecipesBinding;

public class MyRecipesFragment extends Fragment {

    private FragmentMyRecipesBinding binding;
    MyRecipesViewModel myRecipesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        myRecipesViewModel =
                new ViewModelProvider(this).get(MyRecipesViewModel.class);

        binding = FragmentMyRecipesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}