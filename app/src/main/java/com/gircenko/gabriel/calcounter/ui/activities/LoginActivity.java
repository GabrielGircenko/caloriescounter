package com.gircenko.gabriel.calcounter.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.gircenko.gabriel.calcounter.R;
import com.gircenko.gabriel.calcounter.login.ILoginView;
import com.gircenko.gabriel.calcounter.login.LoginPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends ActivityWithProgressDialog implements ILoginView {

    @BindView(R.id.et_email)
    EditText et_email;
    @BindView(R.id.et_password)
    EditText et_password;

    public static final int REQUEST_SIGNUP = 101;
    public static final int SIGNED_UP = 201;

    private LoginPresenter presenter;

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

    /**{@inheritDoc}*/
    @OnClick(R.id.btn_login)
    @Override
    public void loginTapped() {
        showProgressDialog("Logging in... Please, wait.");

        String email = et_email.getText().toString().trim();
        String password = et_password.getText().toString().trim();

        presenter.attemptLogin(email, password);
    }

    /**{@inheritDoc}*/
    @Override
    public void credentialsWrong() {
        dismissProgressDialogAndShowToast("Please, fill all fields");
    }

    /**{@inheritDoc}*/
    @Override
    public void loginFailed() {
        dismissProgressDialogAndShowToast("Couldn't login. Please, try again.");
    }

    /**{@inheritDoc}*/
    @Override
    public void loginSuccessAndNavigateToMainActivity() {
        dismissProgressDialogAndShowToast("Login successful");

        finish();
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }

    /**{@inheritDoc}*/
    @OnClick(R.id.tv_signup)
    @Override
    public void navigateToSignup() {
        startActivityForResult(new Intent(this, SignUpActivity.class), REQUEST_SIGNUP);
    }
}
