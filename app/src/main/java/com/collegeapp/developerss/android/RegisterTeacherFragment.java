package com.collegeapp.developerss.android;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterTeacherFragment extends Fragment {

    private EditText nameEdittext;
    private EditText emailIdEdittext;
    private EditText passwordEdittext;
    private EditText confirmPasswordEdittext;
    private EditText batchEdittext;
    private EditText yearEdittext;
    private EditText departmentEdittext;
    private EditText collegeIdEdittext;
    private Button registerButton;

    private FirebaseAuth firebaseAuth;

    private ArrayList<String> listArray= new ArrayList<>();

    DatabaseReference databaseReference;
    FirebaseUser currentFirebaseUser;

    String correctPaswrd;
    String userUid;

    public RegisterTeacherFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register_teacher, container, false);

        databaseReference = FirebaseDatabase.getInstance().getReference("Teacher");

        firebaseAuth = FirebaseAuth.getInstance();
        currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;

        nameEdittext = view.findViewById(R.id.name_edittext);
        emailIdEdittext = view.findViewById(R.id.emailid_edittext);
        passwordEdittext = view.findViewById(R.id.password_edittext);
        confirmPasswordEdittext = view.findViewById(R.id.confirmPassword_edittext);
        batchEdittext = view.findViewById(R.id.batch_edittext);
        yearEdittext = view.findViewById(R.id.year_edittext);
        departmentEdittext = view.findViewById(R.id.department_edittext);
        collegeIdEdittext = view.findViewById(R.id.collegeId_edittext);
        registerButton = view.findViewById(R.id.register_Button);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if (!registerButton.isEnabled())
//                    registerButton.setBackgroundColor(R.color.colorTextHint);

                firebaseRegistration();
                addArtist();
            }
        });

        return view;
    }

    public void firebaseRegistration() {

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Loading!");
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();

        String passwrd = passwordEdittext.getText().toString();
        String confrmpasswrd = confirmPasswordEdittext.getText().toString();

//        if (passwrd != confrmpasswrd) {
//            Toast.makeText(getActivity(), "Password didn't match!", Toast.LENGTH_SHORT).show();
//        } else {
//            correctPaswrd = passwrd + confrmpasswrd;
//        }

        firebaseAuth.createUserWithEmailAndPassword(emailIdEdittext.getText().toString(), passwrd)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if (task.isSuccessful()) {

                            Toast.makeText(getActivity(), userUid, Toast.LENGTH_SHORT).show();
                            Toast.makeText(getActivity(), "Registration Successful!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getActivity(), LoginActivity.class));
                        } else {
                            Toast.makeText(getActivity(), "Registration Unsuccessful!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void addArtist() {

        userUid = currentFirebaseUser.getUid();

        String PW = passwordEdittext.getText().toString();

        String name = nameEdittext.getText().toString();
        String email = emailIdEdittext.getText().toString();
        String batch = batchEdittext.getText().toString();
        String year = yearEdittext.getText().toString();
        String department = departmentEdittext.getText().toString();
        String collegeId = collegeIdEdittext.getText().toString();

        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(PW)) {

//            DatabaseReference newRef = databaseReference.child(userUid);

            databaseReference.child(userUid).child("User").setValue("Teacher");

            databaseReference.child(userUid).child("Name").setValue(name);
            databaseReference.child(userUid).child("Email").setValue(email);
            databaseReference.child(userUid).child("Year").setValue(year);
            databaseReference.child(userUid).child("Batch").setValue(batch);
            databaseReference.child(userUid).child("Department").setValue(department);
            databaseReference.child(userUid).child("College ID").setValue(collegeId);
            Toast.makeText(getActivity(), "User Added!", Toast.LENGTH_SHORT).show();

        }
        else {
            Toast.makeText(getActivity(), "You should enter a name", Toast.LENGTH_SHORT).show();
        }
    }

}
