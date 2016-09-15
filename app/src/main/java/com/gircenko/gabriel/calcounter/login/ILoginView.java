package com.gircenko.gabriel.calcounter.login;

/**
 * Created by Gabriel Gircenko on 15-Sep-16.
 */
public interface ILoginView {

    void loginTapped();
    void credentialsWrong();
    void loginFailed();
    void loginSuccessAndNavigateToMainActivity();
    void navigateToSignup();
}
