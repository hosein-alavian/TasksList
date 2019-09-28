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
import com.example.taskslist.model.UserRepository;
import com.google.android.material.snackbar.Snackbar;

import static com.example.taskslist.controller.SignUpFragment.PASSWORD_TYPED_TO_SIGNUP;
import static com.example.taskslist.controller.SignUpFragment.USERNAME_TYPED_TO_SIGNUP;


public class SignInFragment extends Fragment {

    private EditText mTitleTV;
    private EditText mTasksNumberTV;
    private Button mCreateTasksButton;
    public static final String USERNAME_TYPED = "username typed";
    public static final String PASSWORD_TYPED = "password typed";
    public static final int REQUEST_CODE = 0;
    private EditText userName;
    private EditText password;
    private Button signIn;
    private Button signUp;

    public SignInFragment() {
    }

    public static SignInFragment newInstance() {

        Bundle args = new Bundle();

        SignInFragment fragment = new SignInFragment();
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
                if (!UserRepository.getInstance().login(userNameGet, passGet))
                    Toast.makeText(getActivity(), "username or password is wrong!", Toast.LENGTH_LONG).show();
                else {
                    Snackbar.make(view, "Welcome " + userNameGet, Snackbar.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), TasksListActivity.class);
                    startActivity(intent);
                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userNameGet = userName.getText().toString();
                String passGet = password.getText().toString();
                if (!UserRepository.getInstance().login(userNameGet, passGet)) {
/*                    Intent intent = new Intent(getActivity(), SignUp.class);
                    intent.putExtra(USERNAME_TYPED, userNameGet);
                    intent.putExtra(PASSWORD_TYPED, passGet);
                    startActivityForResult(intent, REQUEST_CODE);*/
                    SignUpFragment signUpFragment = SignUpFragment.newInstance(userNameGet, passGet);
                    signUpFragment.setTargetFragment(SignInFragment.this, REQUEST_CODE);
                    assert getFragmentManager() != null;
                    getFragmentManager().beginTransaction()
                            .replace(R.id.main_container_layout, signUpFragment)
                            .addToBackStack(SignUpFragment.class.getSimpleName())
                            .commit();
                } else {
                    assert getFragmentManager() != null;
                    getFragmentManager().beginTransaction()
                            .replace(R.id.main_container_layout, SignUpFragment.newInstance(userNameGet, passGet))
                            .commit();
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode != SignUpFragment.RESULT_CODE)
            return;
        if (requestCode == REQUEST_CODE)
            if (data == null)
                return;
        userName.setText(data.getStringExtra(USERNAME_TYPED_TO_SIGNUP));
        password.setText(data.getStringExtra(PASSWORD_TYPED_TO_SIGNUP));
    }

}
