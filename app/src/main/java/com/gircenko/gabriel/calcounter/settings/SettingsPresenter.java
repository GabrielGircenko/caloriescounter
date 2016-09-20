package com.gircenko.gabriel.calcounter.settings;

import com.gircenko.gabriel.calcounter.repos.firebase.authentication.FirebaseAuthInteractor;
import com.gircenko.gabriel.calcounter.repos.firebase.database.FirebaseDataInteractor;
import com.gircenko.gabriel.calcounter.repos.firebase.database.OnExpectedCaloriesRetrievedListener;
import com.gircenko.gabriel.calcounter.repos.firebase.database.OnNameRetrievedListener;
import com.gircenko.gabriel.calcounter.repos.firebase.database.OnSaveExpectedCaloriesListener;
import com.gircenko.gabriel.calcounter.repos.firebase.database.OnSaveListener;
import com.gircenko.gabriel.calcounter.repos.firebase.database.OnSaveNameListener;

/**
 * Created by Gabriel Gircenko on 17-Sep-16.
 */
public class SettingsPresenter implements ISettingsPresenter, OnExpectedCaloriesRetrievedListener, OnNameRetrievedListener {

    ISettingsView view;
    FirebaseDataInteractor firebaseDataInteractor;
    FirebaseAuthInteractor firebaseAuthInteractor;

    public SettingsPresenter(ISettingsView view) {
        this.view = view;
        this.firebaseDataInteractor = new FirebaseDataInteractor();
        this.firebaseAuthInteractor = new FirebaseAuthInteractor();
    }

    /**{@inheritDoc}*/
    @Override
    public void saveExpectedCalories(String calories) {
        if (calories.isEmpty()) {
            view.wrongInput();

        } else {
            firebaseDataInteractor.saveExpectedCalories(firebaseAuthInteractor.getCurrentUserId(), Integer.valueOf(calories), new OnSaveExpectedCaloriesListener() {
                @Override
                public void onSuccess(boolean isSuccess) {
                    view.onSuccess(isSuccess);
                }
            });
        }
    }

    /**{@inheritDoc}*/
    @Override
    public void saveName(String name) {
        if (name.isEmpty()) {
            view.wrongInput();

        } else {
            firebaseDataInteractor.saveName(firebaseAuthInteractor.getCurrentUserId(), name, new OnSaveNameListener() {
                @Override
                public void onSuccess(boolean isSuccess) {
                    view.onSuccess(isSuccess);
                }
            });
        }
    }

    /**{@inheritDoc}*/
    @Override
    public void getExpectedCalories() {
        firebaseDataInteractor.getExpectedCalories(firebaseAuthInteractor.getCurrentUserId(), this);
        firebaseDataInteractor.getName(firebaseAuthInteractor.getCurrentUserId(), this);
    }

    /**{@inheritDoc}*/
    @Override
    public void onExpectedCaloriesRetrieved(String expectedCalories) {
        view.setExpectedCalories(expectedCalories);
    }

    /**{@inheritDoc}*/
    @Override
    public void onExpectedCaloriesError() {}

    /**{@inheritDoc}*/
    @Override
    public void onNameRetrieved(String name) {
        view.setName(name);
    }

    @Override
    public void onNameError() {}
}
