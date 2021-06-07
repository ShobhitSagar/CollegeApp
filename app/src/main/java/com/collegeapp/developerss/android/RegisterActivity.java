package com.collegeapp.developerss.android;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    final CharSequence[] array = {"Teacher", "Student", "Admin"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        FragmentManager fragmentManager = getFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Register as :")
                .setCancelable(false)
                .setItems(array, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        switch (which) {
                            case 0:
                                RegisterTeacherFragment teacherFragment = new RegisterTeacherFragment();
                                fragmentTransaction.add(R.id.registerFragmentContainer, teacherFragment, null);
                                fragmentTransaction.commit();
                                break;

                            case 1:
                                RegisterStudentFragment studentFragment = new RegisterStudentFragment();
                                fragmentTransaction.add(R.id.registerFragmentContainer, studentFragment, null);
                                fragmentTransaction.commit();
                                break;

                            case 2:
                                RegisterAdminFragment adminFragment = new RegisterAdminFragment();
                                fragmentTransaction.add(R.id.registerFragmentContainer, adminFragment, null);
                                fragmentTransaction.commit();
                                break;
                        }

                    }
                }).create().show();
    }
}
