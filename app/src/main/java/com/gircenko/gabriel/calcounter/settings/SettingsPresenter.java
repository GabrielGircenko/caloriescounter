package com.gircenko.gabriel.calcounter.settings;

import com.gircenko.gabriel.calcounter.repos.firebase.authentication.FirebaseAuthInteractor;
import com.gircenko.gabriel.calcounter.repos.firebase.database.FirebaseDataInteractor;
import com.gircenko.gabriel.calcounter.repos.firebase.database.OnExpectedCaloriesRetrievedListener;
import com.gircenko.gabriel.calcounter.repos.firebase.database.OnSaveExpectedCaloriesListener;

/**
 * Created by Gabriel Gircenko on 17-Sep-16.
 */
public class SettingsPresenter implements ISettingsPresenter, OnExpectedCaloriesRetrievedListener {

    ISettingsView view;
    FirebaseDataInteractor firebaseDataInteractor;
    FirebaseAuthInteractor firebaseAuthInteractor;

    public SettingsPresenter(ISettingsView view) {
        this.view = view;
        this.firebaseDataInteractor = new FirebaseDataInteractor();
        this.firebaseAuthInteractor = new FirebaseAuthInteractor();
    }

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

    @Override
    public void getExpectedCalories() {
        firebaseDataInteractor.getExpectedCalories(firebaseAuthInteractor.getCurrentUserId(), this);
    }

    @Override
    public void onExpectedCaloriesRetrieved(String expectedCalories) {
        view.setExpectedCalories(expectedCalories);
    }
}
