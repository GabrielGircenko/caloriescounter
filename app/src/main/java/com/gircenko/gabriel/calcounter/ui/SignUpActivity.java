package com.gircenko.gabriel.calcounter.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.gircenko.gabriel.calcounter.R;
import com.gircenko.gabriel.calcounter.signup.ISignupView;
import com.gircenko.gabriel.calcounter.signup.SignupPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Gabriel Gircenko on 13-Sep-16.
 */
public class SignUpActivity extends CredentialsActivity implements ISignupView {

    SignupPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        ButterKnife.bind(this);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        presenter = new SignupPresenter(this);
    }

    @OnClick(R.id.btn_signup)
    @Override
    public void signupTapped() {
        progressDialog.setMessage("Signing up... Please, wait.");
        progressDialog.show();

        String email = et_email.getText().toString().trim();
        String password = et_password.getText().toString().trim();

        presenter.attemptSignup(email, password);
    }

    @Override
    public void credentialsWrong() {
        progressDialog.dismiss();
        Toast.makeText(this, "Please, fill all fields", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void signupFailed() {
        progressDialog.dismiss();
        Toast.makeText(SignUpActivity.this, "Couldn't sign up the user. Please, try again.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void signupSuccessAndNavigateToMainActivity() {
        progressDialog.dismiss();

        Toast.makeText(SignUpActivity.this, "Sign up successful", Toast.LENGTH_SHORT).show();

        setResult(LoginActivity.SIGNED_UP, null);
        finish();

        startActivity(new Intent(SignUpActivity.this, MainActivity.class));
    }
}
