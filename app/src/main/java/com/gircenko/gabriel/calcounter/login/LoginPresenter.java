package com.gircenko.gabriel.calcounter.login;

import com.gircenko.gabriel.calcounter.repos.firebase.authentication.FirebaseAuthInteractor;
import com.gircenko.gabriel.calcounter.repos.firebase.authentication.OnFirebaseAuthCompleteListener;

/**
 * Created by Gabriel Gircenko on 15-Sep-16.
 */
public class LoginPresenter implements ILoginPresenter, OnFirebaseAuthCompleteListener {

    private ILoginView view;
    private FirebaseAuthInteractor interactor;

    public LoginPresenter(ILoginView view) {
        this.view = view;
        this.interactor = new FirebaseAuthInteractor();
    }

    @Override
    public void attemptLogin(String email, String password) {
        if (interactor.validatedCredentials(email, password)) {
            interactor.loginWithCredentials(this , email, password);

        } else {
            view.credentialsWrong();
        }
    }

    /**{@inheritDoc}*/
    @Override
    public void onSuccess() {
        view.loginSuccessAndNavigateToMainActivity();
    }

    /**{@inheritDoc}*/
    @Override
    public void onFailed() {
        view.loginFailed();
    }
}
