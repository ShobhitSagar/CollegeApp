package com.collegeapp.developerss.android;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class DialogListToRegister extends DialogFragment {

    final CharSequence[] array = {"Teacher", "Student", "Admin"};
    private String selection;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setTitle("Register as :")
                .setItems(array, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        switch (which) {
                            case 0:
                                Intent intent0 = new Intent(getContext(), RegisterActivity.class);
                                startActivity(intent0);
                                break;
                            case 1:
                                Intent intent1 = new Intent(getContext(), RegisterActivity.class);
                                startActivity(intent1);
                                break;
                            case 2:
                                Intent intent2 = new Intent(getContext(), RegisterActivity.class);
                                startActivity(intent2);
                                break;
                        }

                    }
                });
        return builder.create();
    }
}
