package com.gircenko.gabriel.calcounter.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.gircenko.gabriel.calcounter.R;
import com.gircenko.gabriel.calcounter.login.ILoginView;
import com.gircenko.gabriel.calcounter.login.LoginPresenter;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends CredentialsActivity implements ILoginView {

    public static final int REQUEST_SIGNUP = 101;
    public static final int SIGNED_UP = 201;

    LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        presenter = new LoginPresenter(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP && resultCode == SIGNED_UP) {
            this.finish();
        }
    }

    @OnClick(R.id.btn_login)
    @Override
    public void loginTapped() {
        progressDialog.setMessage("Logging in... Please, wait.");
        progressDialog.show();

        String email = et_email.getText().toString().trim();
        String password = et_password.getText().toString().trim();

        presenter.attemptLogin(email, password);
    }

    @Override
    public void credentialsWrong() {
        progressDialog.dismiss();

        Toast.makeText(this, "Please, fill all fields", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginFailed() {
        progressDialog.dismiss();

        Toast.makeText(LoginActivity.this, "Couldn't login. Please, try again.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginSuccessAndNavigateToMainActivity() {
        progressDialog.dismiss();

        finish();
        startActivity(new Intent(LoginActivity.this, MainActivity.class));

        Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.tv_signup)
    @Override
    public void navigateToSignup() {
        startActivityForResult(new Intent(this, SignUpActivity.class), REQUEST_SIGNUP);
    }
}
