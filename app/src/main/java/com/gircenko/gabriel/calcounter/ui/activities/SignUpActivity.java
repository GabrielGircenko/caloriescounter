package com.gircenko.gabriel.calcounter.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.widget.EditText;

import com.gircenko.gabriel.calcounter.R;
import com.gircenko.gabriel.calcounter.signup.ISignupView;
import com.gircenko.gabriel.calcounter.signup.SignupPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Gabriel Gircenko on 13-Sep-16.
 */
public class SignUpActivity extends ActivityWithProgressDialog implements ISignupView {

    @BindView(R.id.et_email)
    EditText et_email;
    @BindView(R.id.et_password)
    EditText et_password;

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
        showProgressDialog("Signing up... Please, wait.");

        String email = et_email.getText().toString().trim();
        String password = et_password.getText().toString().trim();

        presenter.attemptSignup(email, password);
    }

    @Override
    public void credentialsWrong() {
        dismissProgressDialogAndShowToast("Please, fill all fields");
    }

    @Override
    public void signupFailed() {
        dismissProgressDialogAndShowToast("Couldn't sign up the user. Please, try again.");
    }

    @Override
    public void signupSuccessAndNavigateToMainActivity() {
        dismissProgressDialogAndShowToast("Sign up successful");

        setResult(LoginActivity.SIGNED_UP, null);
        finish();

        startActivity(new Intent(SignUpActivity.this, MainActivity.class));
    }
}
