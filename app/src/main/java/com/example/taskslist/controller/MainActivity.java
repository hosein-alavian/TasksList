package com.example.taskslist.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.taskslist.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_container_layout,new MainFragment())
                .commit();
    }
}
