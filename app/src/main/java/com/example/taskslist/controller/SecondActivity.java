package com.example.taskslist.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.taskslist.R;
import com.google.android.material.tabs.TabLayout;

public class SecondActivity extends AppCompatActivity {

    private static final String USERNAME = "com.example.taskslist.username";
    private static final String TASKS_NUMBER = "com.example.taskslist.tasksnumber";
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private AdsPagerAdapter adsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        /*
        getSupportFragmentManager().beginTransaction()
                .add(R.id.second_container_layout,
                        ToDoFragment.newInstance(getIntent().getStringExtra(USERNAME),
                        getIntent().getIntExtra(TASKS_NUMBER,0)))
                .commit();*/
        tabLayout = findViewById(R.id.tasks_tablayout);
        viewPager = findViewById(R.id.tasks_viewPager);
        adsPagerAdapter=new AdsPagerAdapter(getSupportFragmentManager(),
                getIntent().getStringExtra(USERNAME),
                getIntent().getIntExtra(TASKS_NUMBER,0));
        viewPager.setAdapter(adsPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(4);
    }

    public static Intent newIntent(Context context, String username, int tasksNumber) {
        Intent intent=new Intent(context, SecondActivity.class);
        intent.putExtra(USERNAME,username);
        intent.putExtra(TASKS_NUMBER,tasksNumber);
        return intent;
    }
}
