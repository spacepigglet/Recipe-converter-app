package com.example.recipe_converter_app.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;

import com.example.recipe_converter_app.R;
import com.example.recipe_converter_app.logic.DeleteDialogListener;
import com.example.recipe_converter_app.util.VibrationUtil;

public class ConfirmDeleteFragment extends DialogFragment {
    private DeleteDialogListener listener;

    // Constructor to set the listener
    public ConfirmDeleteFragment(DeleteDialogListener listener) {
        this.listener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction.
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.dialog_confirm_delete)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (listener != null) {
                            VibrationUtil.tick(getContext());
                            listener.onConfirmation(true); // Notify MainActivity of "Yes" click
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancels the dialog.
                        if (listener != null) {
                            VibrationUtil.tick(getContext());
                            listener.onConfirmation(false); // Notify MainActivity of "Cancel" click
                        }
                    }
                });
        // Create the AlertDialog object and return it.
        return builder.create();
    }
}
