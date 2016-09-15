package com.gircenko.gabriel.calcounter.login;

import com.gircenko.gabriel.calcounter.repos.FirebaseInteractor;
import com.gircenko.gabriel.calcounter.repos.OnFirebaseCompleteListener;

/**
 * Created by Gabriel Gircenko on 15-Sep-16.
 */
public class LoginPresenter implements ILoginPresenter, OnFirebaseCompleteListener {

    private ILoginView view;
    private FirebaseInteractor interactor;

    public LoginPresenter(ILoginView view) {
        this.view = view;
        this.interactor = new FirebaseInteractor();
    }

    @Override
    public void attemptLogin(String email, String password) {
        if (interactor.validatedCredentials(email, password)) {
            interactor.loginWithCredentials(this , email, password);

        } else {
            view.credentialsWrong();
        }
    }

    /*@Override
    public void loginComplete(boolean isSuccess) {
        if (isSuccess) view.loginSuccessAndNavigateToMainActivity();
        else view.loginFailed();
    }*/

    @Override
    public void onSuccess() {
        view.loginSuccessAndNavigateToMainActivity();
    }

    @Override
    public void onFailed() {
        view.loginFailed();
    }
}
