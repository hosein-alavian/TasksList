package com.example.taskslist.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.taskslist.R;

public class SgininSignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container_layout,SignInFragment.newInstance())
                .commit();
    }

    public static Intent newIntent(Context context) {
        Intent intent=new Intent(context, SgininSignupActivity.class);
        return intent;
    }
}
