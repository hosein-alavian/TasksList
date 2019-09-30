package com.example.taskslist.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.taskslist.R;

public class SigninSignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container_layout, SigninFragment.newInstance())
                .commit();
    }

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, SigninSignupActivity.class);
        return intent;
    }
}
