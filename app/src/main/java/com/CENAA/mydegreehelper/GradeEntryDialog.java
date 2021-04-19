package com.CENAA.mydegreehelper;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

import org.jetbrains.annotations.NotNull;

public class GradeEntryDialog extends DialogFragment {

    StateManager stateManager;
    Blueprint state;
    String courseName;
    Bundle bundle;
    EditText gradeInput;
    Button enterButton, cancelButton;
    double grade;

    @NotNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        stateManager = ((BPstate)getActivity().getApplicationContext()).getStateManager();
        state = stateManager.getState();


        View dialogView = inflater.inflate(R.layout.grade_entry_dialog, null);
        builder.setView(dialogView);

        bundle = getArguments();
        courseName = bundle.getString("courseName");

        grade = 0.0;

        enterButton = dialogView.findViewById(R.id.enter_grade_button);
        cancelButton = dialogView.findViewById(R.id.cancel_button);

        gradeInput = dialogView.findViewById(R.id.gradeEntry);

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (grade == 0) {
                    dismiss();
                } else {
                    state.completeCourse(courseName, Double.parseDouble(gradeInput.getText().toString()));
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return builder.create();
    }
}

