package com.example.taskslist.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.taskslist.R;
import com.example.taskslist.model.User;
import com.example.taskslist.model.UserRepository;
import com.example.taskslist.model.WrongUserNameException;

import java.util.UUID;

import static com.example.taskslist.controller.SigninFragment.REQUEST_CODE;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignupFragment extends Fragment {
    public static final String USERNAME_TYPED_TO_SIGNUP = "com.example.taskslist.username typed to signup";
    public static final String PASSWORD_TYPED_TO_SIGNUP = "com.example.taskslist.password typed to signup";
    public static final int RESULT_CODE = 1;
    public static final String USERNAME_VALUE = "username value";
    public static final String PASSWORD_VALUE = "password value";
    private EditText userNameSignUp;
    private EditText passwordSignUp;
    private Button signIn;
    private Button signUp;
    private String mUsername;
    private String mPassword;

    public SignupFragment() {
        // Required empty public constructor
    }

    public static SignupFragment newInstance(String username, String password) {

        Bundle args = new Bundle();
        args.putString(USERNAME_VALUE, username);
        args.putString(PASSWORD_VALUE, password);
        SignupFragment fragment = new SignupFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUsername = getArguments().getString(USERNAME_VALUE);
        mPassword = getArguments().getString(PASSWORD_VALUE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        initUI(view);

        return view;
    }

    private void initUI(View view) {
        userNameSignUp = view.findViewById(R.id.username_signup);
        userNameSignUp.setText(mUsername);
        passwordSignUp = view.findViewById(R.id.pass_signup);
        passwordSignUp.setText(mPassword);
        signIn = view.findViewById(R.id.login_su_button);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SigninSignupActivity.class);
                startActivity(intent);
            }
        });
        signUp = view.findViewById(R.id.signup_su_button);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userNameGet = userNameSignUp.getText().toString();
                String passGet = passwordSignUp.getText().toString();
                try {
                    if (!userNameGet.equals("") && !passGet.equals("")) {
                        Toast.makeText(getActivity(),
                                "account created!",
                                Toast.LENGTH_SHORT).show();
                        UserRepository.getInstance(getContext()).insertUser(new User(userNameGet, passGet, UUID.randomUUID()));
                        Intent intent = new Intent();
                        intent.putExtra(USERNAME_TYPED_TO_SIGNUP, userNameGet);
                        intent.putExtra(PASSWORD_TYPED_TO_SIGNUP, passGet);
                        onActivityResult(REQUEST_CODE, RESULT_CODE, intent);
                        assert getFragmentManager() != null;
                        getFragmentManager().popBackStack();
                    } else if (userNameGet.equals("") && passGet.equals(""))
                        Toast.makeText(getActivity(),
                                "username and password field is empty!",
                                Toast.LENGTH_SHORT).show();
                    else if (userNameGet.equals(""))
                        Toast.makeText(getActivity(),
                                "username field is empty!",
                                Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getActivity(),
                                "password field is empty!",
                                Toast.LENGTH_SHORT).show();

                } catch (WrongUserNameException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

