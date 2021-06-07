package com.collegeapp.developerss.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class TeacherProfileActivity extends AppCompatActivity {

    private TextView nameTextView;
    private TextView idTextView;
    private TextView yearTextView;
    private TextView departmentTextView;

    private DatabaseReference databaseReference;
    private FirebaseUser currentFirebaseUser;

    String Uid;
    Button tryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_profile);

        currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//        assert currentFirebaseUser != null;
        Uid = currentFirebaseUser.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference("Unregistered Teacher/"+Uid);

        nameTextView = findViewById(R.id.name_tv);
        idTextView = findViewById(R.id.id_tv);
        yearTextView = findViewById(R.id.year_tv);
        departmentTextView = findViewById(R.id.department_tv);
        tryButton = findViewById(R.id.try_Button);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String name = dataSnapshot.child("Name").getValue(String.class);
                String clasS = dataSnapshot.child("User").getValue(String.class);
                String year = dataSnapshot.child("Email").getValue(String.class);

                nameTextView.setText(name);

                Log.d("E_VALUE", "Name : " + name);
                Log.d("E_VALUE", "User : " + clasS);
                Log.d("E_VALUE", "Email : " + year);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
