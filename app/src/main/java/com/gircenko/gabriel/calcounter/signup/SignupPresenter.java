package com.gircenko.gabriel.calcounter.signup;

import com.gircenko.gabriel.calcounter.firebase.FirebaseInteractor;
import com.gircenko.gabriel.calcounter.firebase.OnFirebaseCompleteListener;
import com.gircenko.gabriel.calcounter.login.ILoginView;

/**
 * Created by Gabriel Gircenko on 15-Sep-16.
 */
public class SignupPresenter implements ISignupPresenter, OnFirebaseCompleteListener {

    private ISignupView view;
    private FirebaseInteractor interactor;

    public SignupPresenter(ISignupView view) {
        this.view = view;
        this.interactor = new FirebaseInteractor();
    }

    @Override
    public void attemptSignup(String email, String password) {
        if (interactor.validatedCredentials(email, password)) {
            interactor.signupWithCredentials(this , email, password);

        } else {
            view.credentialsWrong();
        }
    }

    @Override
    public void onSuccess() {
        view.signupSuccessAndNavigateToMainActivity();
    }

    @Override
    public void onFailed() {
        view.signupFailed();
    }
}
