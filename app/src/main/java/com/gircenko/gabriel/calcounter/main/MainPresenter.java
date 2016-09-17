package com.gircenko.gabriel.calcounter.main;

import com.gircenko.gabriel.calcounter.repos.firebase.authentication.FirebaseAuthInteractor;
import com.gircenko.gabriel.calcounter.repos.firebase.database.FirebaseDataInteractor;

/**
 * Created by Gabriel Gircenko on 15-Sep-16.
 */
public class MainPresenter implements IMainPresenter {

    IMainView view;
    FirebaseAuthInteractor firebaseAuthInteractor;
    FirebaseDataInteractor firebaseDataInteractor;

    public MainPresenter(IMainView view) {
        this.view = view;
        this.firebaseAuthInteractor = new FirebaseAuthInteractor();
        this.firebaseDataInteractor = new FirebaseDataInteractor();
    }

    @Override
    public void validateCurrentUser() {
        if (!firebaseAuthInteractor.isCurrentUserExisting()) view.userNotLoggedInGoToLogin();
        else view.userLoggedIn();
    }

    @Override
    public void signOut() {
        firebaseAuthInteractor.signOut();
    }

    @Override
    public void getMealsByCurrentUser() {
        firebaseDataInteractor.getMealsByUser(firebaseAuthInteractor.getCurrentUserId());
    }
}
