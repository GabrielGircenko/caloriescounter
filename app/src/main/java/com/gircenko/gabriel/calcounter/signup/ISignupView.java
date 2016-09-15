package com.gircenko.gabriel.calcounter.signup;

/**
 * Created by Gabriel Gircenko on 15-Sep-16.
 */
public interface ISignupView {

    void signupTapped();
    void credentialsWrong();
    void signupFailed();
    void signupSuccessAndNavigateToMainActivity();
}
