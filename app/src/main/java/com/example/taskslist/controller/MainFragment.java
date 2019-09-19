package com.example.taskslist.controller;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.taskslist.R;
import com.google.android.material.snackbar.Snackbar;


public class MainFragment extends Fragment {

    private EditText userNameTV;
    private EditText tasksNumberTV;
    private Button createTasksButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userNameTV=view.findViewById(R.id.username_textview);
        tasksNumberTV=view.findViewById(R.id.tasks_number_textview);
        createTasksButton=view.findViewById(R.id.create_tasks_button);
        createTasksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    if(userNameTV.getText()==null || tasksNumberTV.getText()==null)
                        Snackbar.make(getView(),
                                "username or tasks number is empty!",
                                Snackbar.LENGTH_SHORT).show();
                    else {
                        Intent getIntent = SecondActivity.newIntent(getActivity(),
                                userNameTV.getText().toString(),
                                Integer.valueOf(tasksNumberTV.getText().toString()));
                        startActivity(getIntent);
                    }
                }
                catch (IllegalArgumentException ex){
                    Snackbar.make(getView(),
                            "tasks number should be numerical!",
                            Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }

}
