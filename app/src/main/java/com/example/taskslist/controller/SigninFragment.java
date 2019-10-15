package com.example.taskslist.controller;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.taskslist.R;
import com.example.taskslist.model.User;
import com.example.taskslist.model.UserRepository;
import com.google.android.material.snackbar.Snackbar;

import java.util.UUID;

import static com.example.taskslist.controller.SignupFragment.PASSWORD_TYPED_TO_SIGNUP;
import static com.example.taskslist.controller.SignupFragment.USERNAME_TYPED_TO_SIGNUP;


public class SigninFragment extends Fragment {


    public static final int REQUEST_CODE = 0;
    private EditText userName;
    private EditText password;
    private Button signIn;
    private Button signUp;

    public SigninFragment() {
    }

    public static SigninFragment newInstance() {

        Bundle args = new Bundle();

        SigninFragment fragment = new SigninFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        intiUI(view);

        return view;
    }

    private void intiUI(View view) {
        userName = view.findViewById(R.id.username_signin);
        password = view.findViewById(R.id.password_signin);
        signIn = view.findViewById(R.id.signin_button);
        signUp = view.findViewById(R.id.signup_button);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userNameGet = userName.getText().toString();
                String passGet = password.getText().toString();

                if (!UserRepository.getInstance(getContext()).login(userNameGet, passGet))
                    Toast.makeText(getActivity(), "username or password is wrong!", Toast.LENGTH_SHORT).show();
                else {
                    Toast.makeText(getActivity(), "Welcome " + userNameGet, Toast.LENGTH_SHORT).show();
                    Intent intent =TasksListActivity.newIntent(getContext(),userNameGet,passGet);

                    startActivity(intent);
                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userNameGet = userName.getText().toString();
                String passGet = password.getText().toString();
                if (!UserRepository.getInstance(getContext()).login(userNameGet, passGet)) {
                    SignupFragment signUpFragment = SignupFragment.newInstance(userNameGet, passGet);
                    signUpFragment.setTargetFragment(SigninFragment.this, REQUEST_CODE);
                    assert getFragmentManager() != null;
                    getFragmentManager().beginTransaction()
                            .replace(R.id.main_container_layout, signUpFragment)
                            .addToBackStack(SignupFragment.class.getSimpleName())
                            .commit();
                } else {
                    assert getFragmentManager() != null;
                    getFragmentManager().beginTransaction()
                            .replace(R.id.main_container_layout, SignupFragment.newInstance(userNameGet, passGet))
                            .commit();
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode != SignupFragment.RESULT_CODE)
            return;
        if (requestCode == REQUEST_CODE)
            if (data == null)
                return;
        userName.setText(data.getStringExtra(USERNAME_TYPED_TO_SIGNUP));
        password.setText(data.getStringExtra(PASSWORD_TYPED_TO_SIGNUP));
    }

}
