package com.gircenko.gabriel.calcounter.main;

import com.gircenko.gabriel.calcounter.repos.firebase.authentication.FirebaseAuthInteractor;

/**
 * Created by Gabriel Gircenko on 15-Sep-16.
 */
public class MainPresenter implements IMainPresenter {

    IMainView view;
    FirebaseAuthInteractor interactor;

    public MainPresenter(IMainView view) {
        this.view = view;
        this.interactor = new FirebaseAuthInteractor();
    }

    @Override
    public void validateCurrentUser() {
        if (!interactor.isCurrentUserExisting()) view.userNotLoggedInGoToLogin();
    }

    @Override
    public void signOut() {
        interactor.signOut();
    }
}
