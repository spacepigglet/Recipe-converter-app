package com.example.recipe_converter_app.ui.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.recipe_converter_app.R;
import com.example.recipe_converter_app.databinding.FragmentSettingsBinding;
import com.example.recipe_converter_app.logic.Unit;

public class SettingsFragment extends Fragment {

    private FragmentSettingsBinding binding;
    SettingsViewModel settingsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        settingsViewModel = new ViewModelProvider(requireActivity()).get(SettingsViewModel.class);

        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupUnitSpinnerSetting();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    public void setupUnitSpinnerSetting() {

        // Set up spinner with enum values
        ArrayAdapter<Unit> adapter = new ArrayAdapter<>(requireContext(),
                R.layout.spinner_item, Unit.values());
        adapter.setDropDownViewResource(R.layout.spinner_dropdown);
        binding.defaultUnitSpinner.setAdapter(adapter);
        setStartPosition();

        // Save selected default unit to SharedPreferences when selected
        binding.defaultUnitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                saveDefaultUnitPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

    }
    private void saveDefaultUnitPosition(int position) {
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(getString(R.string.default_unit_position_key), position);
        editor.apply();
    }
    private void setStartPosition(){
        SharedPreferences sharedPref = getActivity().getPreferences(getContext().MODE_PRIVATE);
        int defaultUnitPosition = sharedPref.getInt(getString(R.string.default_unit_position_key), Unit.GRAM.ordinal());
        binding.defaultUnitSpinner.setSelection(defaultUnitPosition);
    }

}