package com.gircenko.gabriel.calcounter.main;

import com.gircenko.gabriel.calcounter.models.MealModel;
import com.gircenko.gabriel.calcounter.repos.calendar.CalendarInteractor;
import com.gircenko.gabriel.calcounter.repos.firebase.authentication.FirebaseAuthInteractor;
import com.gircenko.gabriel.calcounter.repos.firebase.database.FirebaseDataInteractor;
import com.gircenko.gabriel.calcounter.repos.firebase.database.OnMealDataListener;
import com.gircenko.gabriel.calcounter.ui.adapters.CaloriesPagerAdapter;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Gabriel Gircenko on 15-Sep-16.
 */
public class MainPresenter implements IMainPresenter, OnMealDataListener {

    IMainView view;
    FirebaseAuthInteractor firebaseAuthInteractor;
    FirebaseDataInteractor firebaseDataInteractor;
    CalendarInteractor calendarInteractor;

    /** String key is the date in format {@link com.gircenko.gabriel.calcounter.Constants#DATE_FORMAT} */
    private Map<String, MealModel> map = new TreeMap<>();
    private int[] totalCalories = new int[CaloriesPagerAdapter.PAGE_COUNT];

    public MainPresenter(IMainView view) {
        this.view = view;
        this.firebaseAuthInteractor = new FirebaseAuthInteractor();
        this.firebaseDataInteractor = new FirebaseDataInteractor();
        this.calendarInteractor = new CalendarInteractor();
    }

    /**{@inheritDoc}*/
    @Override
    public void validateCurrentUser() {
        if (!firebaseAuthInteractor.isCurrentUserExisting()) view.userNotLoggedInGoToLogin();
        else view.userLoggedIn();
    }

    /**{@inheritDoc}*/
    @Override
    public void signOut() {
        firebaseAuthInteractor.signOut();
    }

    /**{@inheritDoc}*/
    @Override
    public void getMealsByCurrentUser() {
        firebaseDataInteractor.getMealsByUser(firebaseAuthInteractor.getCurrentUserId(), this);
    }

    /**{@inheritDoc}*/
    @Override
    public void applyDatesToPages() {
        for (int i = 0; i < CaloriesPagerAdapter.PAGE_COUNT; i++) {
            view.applyDate(i, calendarInteractor.getDateByPage(i));
        }
    }

    /**{@inheritDoc}*/
    @Override
    public void onGotNewMeal(String mealId, MealModel meal) {
        int day = calendarInteractor.getDayInLastWeekByFullDate(meal.getDate());
        putMealIntoMapAndUpdateViewIfApplicable(mealId, meal, day);
    }

    /**{@inheritDoc}*/
    @Override
    public void onMealChanged(String mealId, MealModel meal) {
        // TODO
    }

    /**{@inheritDoc}*/
    @Override
    public void onMealRemoved(String mealId) {
        // TODO
    }

    private void putMealIntoMapAndUpdateViewIfApplicable(String mealId, MealModel meal, int day) {
        if (day >= 0) {
            map.put(mealId, meal);
            totalCalories[day] += meal.getCalories();
            view.applyTotalCalories(day, String.valueOf(totalCalories[day]));
        }
    }
}
