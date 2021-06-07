package com.collegeapp.developerss.android;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle = getIntent().getExtras();
        String userValue = bundle.getString("value");
        String userUid = bundle.getString("USERUID");

        databaseReference = FirebaseDatabase.getInstance().getReference();

        FragmentManager fragmentManager = getFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Toast.makeText(this, userUid, Toast.LENGTH_SHORT).show();

        if (userValue.equals("Teacher")) {
            Toast.makeText(MainActivity.this, "Teacher", Toast.LENGTH_SHORT).show();

            TeacherDashboardFragment teacherDashboardFragment = new TeacherDashboardFragment();
            fragmentTransaction.add(R.id.dashboardFragmentContainer, teacherDashboardFragment, null);

//            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        } else if (userValue.equals("Student")) {
            Toast.makeText(MainActivity.this, "Student", Toast.LENGTH_SHORT).show();

            RegisterStudentFragment studentFragment = new RegisterStudentFragment();
            fragmentTransaction.add(R.id.dashboardFragmentContainer, studentFragment, null);
            fragmentTransaction.commit();

        } else if (userValue.equals("StudentAdmin")) {
            Toast.makeText(MainActivity.this, "Student", Toast.LENGTH_SHORT).show();

            RegisterStudentFragment studentFragment = new RegisterStudentFragment();
            fragmentTransaction.add(R.id.dashboardFragmentContainer, studentFragment, null);
            fragmentTransaction.commit();

        } else {
            // Do nothing
        }
    }
}
