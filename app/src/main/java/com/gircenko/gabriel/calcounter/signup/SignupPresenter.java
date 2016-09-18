package com.gircenko.gabriel.calcounter.signup;

import com.gircenko.gabriel.calcounter.repos.firebase.authentication.FirebaseAuthInteractor;
import com.gircenko.gabriel.calcounter.repos.firebase.authentication.OnFirebaseAuthCompleteListener;

/**
 * Created by Gabriel Gircenko on 15-Sep-16.
 */
public class SignupPresenter implements ISignupPresenter, OnFirebaseAuthCompleteListener {

    private ISignupView view;
    private FirebaseAuthInteractor interactor;

    public SignupPresenter(ISignupView view) {
        this.view = view;
        this.interactor = new FirebaseAuthInteractor();
    }

    /**{@inheritDoc}*/
    @Override
    public void attemptSignup(String email, String password) {
        if (interactor.validatedCredentials(email, password)) {
            interactor.signupWithCredentials(this , email, password);

        } else {
            view.credentialsWrong();
        }
    }

    /**{@inheritDoc}*/
    @Override
    public void onSuccess() {
        view.signupSuccessAndNavigateToMainActivity();
    }

    /**{@inheritDoc}*/
    @Override
    public void onFailed() {
        view.signupFailed();
    }
}
