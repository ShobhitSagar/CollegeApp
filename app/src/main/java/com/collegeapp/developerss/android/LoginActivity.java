package com.collegeapp.developerss.android;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private TextView registerTextView;
    private EditText emailidEdittext;
    private EditText passwordEdittext;
    private Button signInButton;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser currentFirebaseUser;
    private DatabaseReference databaseReference;

    Bundle bundle = new Bundle();

    final CharSequence[] array = {"Teacher", "Student", "Admin"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setMessage("Connect to Internet!");
//        builder.show();

        registerTextView = findViewById(R.id.register_TextView);
        emailidEdittext = findViewById(R.id.emailid_edittext);
        passwordEdittext = findViewById(R.id.password_edittext);
        signInButton = findViewById(R.id.signIn_Button);

        firebaseAuth = FirebaseAuth.getInstance();
        currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(LoginActivity.this);
                builder.setTitle("Log In as :")
                        .setCancelable(false)
                        .setItems(array, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                switch (which) {
                                    case 0:
                                        bundle.putString("value", "Teacher");
                                        Toast.makeText(LoginActivity.this, "Teacher", Toast.LENGTH_SHORT).show();
                                        break;

                                    case 1:
                                        bundle.putString("value", "Student");
                                        Toast.makeText(LoginActivity.this, "Student", Toast.LENGTH_SHORT).show();
                                        break;

                                    case 2:
                                        bundle.putString("value", "Admin");
                                        Toast.makeText(LoginActivity.this, "Admin", Toast.LENGTH_SHORT).show();
                                        break;
                                }

                                (firebaseAuth.signInWithEmailAndPassword(emailidEdittext.getText().toString(),
                                        passwordEdittext.getText().toString()))
                                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {

                                                if (task.isSuccessful()) {

                                                    String userUid = currentFirebaseUser.getUid();
                                                    bundle.putString("USERUID", userUid);

                                                    Intent intent = new Intent(LoginActivity.this,
                                                            MainActivity.class);
                                                    intent.putExtras(bundle);
                                                    startActivity(intent);

                                                    Toast.makeText(LoginActivity.this, "Sign In Successful!",
                                                            Toast.LENGTH_SHORT).show();
                                                } else {
                                                    Toast.makeText(LoginActivity.this, "Sign In Unsuccessful!",
                                                            Toast.LENGTH_SHORT).show();
                                                }

                                            }
                                        });

                            }
                        }).create().show();
            }
        });

        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
////
//////        getParentdata();
////
//////        if (firebaseAuth.getCurrentUser() != null) {
//////
//            Intent intent = new Intent(LoginActivity.this,
//                    TeacherProfileActivity.class);
//////            intent.putExtras(bundle);
//            startActivity(intent);
//////        }
//    }

    public void getParentdata() {

        String userUid = currentFirebaseUser.getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference(userUid);
//        String paren = databaseReference.getParent().toString();
//        String naam = databaseReference.child("Name").toString();

//        Toast.makeText(LoginActivity.this, naam, Toast.LENGTH_SHORT).show();
//        Toast.makeText(LoginActivity.this, paren, Toast.LENGTH_SHORT).show();

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    String parent = child.getKey();
                    Toast.makeText(LoginActivity.this, parent, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
