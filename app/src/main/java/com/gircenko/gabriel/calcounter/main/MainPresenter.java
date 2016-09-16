package com.gircenko.gabriel.calcounter.main;

import com.gircenko.gabriel.calcounter.repos.firebase.FirebaseInteractor;

/**
 * Created by Gabriel Gircenko on 15-Sep-16.
 */
public class MainPresenter implements IMainPresenter {

    IMainView view;
    FirebaseInteractor interactor;

    public MainPresenter(IMainView view) {
        this.view = view;
        this.interactor = new FirebaseInteractor();
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
