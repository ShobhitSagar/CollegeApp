package com.collegeapp.developerss.android;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TeacherProfileFragment extends Fragment {

    public TeacherProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_teacher_profile, container, false);

        RegisterTeacherFragment teacherFragment = new RegisterTeacherFragment();
//        String techrName = teacherFragment.getMyData();

//        nameTextView.setText(techrName);


        return view;
    }



}
