package com.careitapp.care_it;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginFragment extends Fragment {

    @BindView(R.id.login_email_edittext)
    EditText emailEditText;
    @BindView(R.id.login_password_edittext)
    EditText passwordEditText;
    @BindView(R.id.login_fragment_frame)
    FrameLayout loginFrame;

    private FirebaseAuth firebaseAuth;

    public LoginFragment() {
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);

        ButterKnife.bind(this, rootView);

        startFirebase();

        return rootView;
    }

    private void startFirebase(){
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @OnClick(R.id.login_button)
    public void createUser(){
        if (!TextUtils.isEmpty(emailEditText.getText().toString()) && !TextUtils.isEmpty(passwordEditText.getText().toString()))
            firebaseAuth.createUserWithEmailAndPassword(
                    emailEditText.getText().toString().trim(),
                    passwordEditText.getText().toString().trim()).addOnCompleteListener(task -> {
                startActivity(new Intent(getContext(), MainActivity.class));
                Objects.requireNonNull(getActivity()).finish();
            });
        else
            Toast.makeText(getContext(), "Please make sure password and email aren't empty", Toast.LENGTH_SHORT).show();
    }

}
