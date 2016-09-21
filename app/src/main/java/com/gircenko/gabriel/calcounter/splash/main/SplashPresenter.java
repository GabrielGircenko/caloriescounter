package com.gircenko.gabriel.calcounter.splash.main;

import com.gircenko.gabriel.calcounter.Constants;
import com.gircenko.gabriel.calcounter.models.MealModelWithId;
import com.gircenko.gabriel.calcounter.repos.calendar.CalendarInteractor;
import com.gircenko.gabriel.calcounter.repos.firebase.authentication.FirebaseAuthInteractor;
import com.gircenko.gabriel.calcounter.repos.firebase.database.FirebaseDataInteractor;
import com.gircenko.gabriel.calcounter.repos.firebase.database.OnExpectedCaloriesRetrievedListener;
import com.gircenko.gabriel.calcounter.repos.firebase.database.OnMealListDataListener;
import com.gircenko.gabriel.calcounter.ui.adapters.CaloriesPagerAdapter;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Gabriel Gircenko on 15-Sep-16.
 */
public class SplashPresenter implements ISplashPresenter {

    private ISplashView view;
    private FirebaseAuthInteractor firebaseAuthInteractor;

    public SplashPresenter(ISplashView view) {
        this.view = view;
        this.firebaseAuthInteractor = new FirebaseAuthInteractor();
    }

    /**{@inheritDoc}*/
    @Override
    public void validateCurrentUser() {
        if (!firebaseAuthInteractor.isCurrentUserExisting()) view.userNotLoggedInGoToLogin();
        else view.userLoggedIn();
    }

    /**{@inheritDoc}*/
    @Override
    public String getCurrentUserId() {
        return firebaseAuthInteractor.getCurrentUserId();
    }
}
