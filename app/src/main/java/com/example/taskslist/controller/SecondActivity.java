package com.example.taskslist.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.taskslist.R;

public class SecondActivity extends AppCompatActivity {

    private static final String USERNAME = "com.example.taskslist.username";
    private static final String TASKS_NUMBER = "com.example.taskslist.tasksnumber";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.second_container_layout,
                        ToDoFragment.newInstance(getIntent().getStringExtra(USERNAME),
                        getIntent().getIntExtra(TASKS_NUMBER,0)))
                .commit();
    }

    public static Intent newIntent(Context context, String username, int tasksNumber) {
        Intent intent=new Intent(context, SecondActivity.class);
        intent.putExtra(USERNAME,username);
        intent.putExtra(TASKS_NUMBER,tasksNumber);
        return intent;
    }
}
