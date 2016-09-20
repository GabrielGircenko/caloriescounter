package com.gircenko.gabriel.calcounter.main;

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
public class MainPresenter implements IMainPresenter, OnExpectedCaloriesRetrievedListener, OnMealListDataListener {

    private IMainView view;
    private FirebaseAuthInteractor firebaseAuthInteractor;
    private FirebaseDataInteractor firebaseDataInteractor;
    private CalendarInteractor calendarInteractor;

    /** String key is the mealId */
    private Map<String, MealModelWithId> map = new TreeMap<>();
    private int[] totalCalories;
    private int expectedCalories = 0;

    public MainPresenter(IMainView view) {
        this.view = view;
        this.firebaseAuthInteractor = new FirebaseAuthInteractor();
        this.firebaseDataInteractor = new FirebaseDataInteractor();
        this.calendarInteractor = new CalendarInteractor();
        totalCalories = new int[CaloriesPagerAdapter.PAGE_COUNT];
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
    public String getCurrentUserId() {
        return firebaseAuthInteractor.getCurrentUserId();
    }

    /**{@inheritDoc}*/
    @Override
    public void getExpectedCalories() {
        firebaseDataInteractor.getExpectedCalories(firebaseAuthInteractor.getCurrentUserId(), this);
    }

    /**{@inheritDoc}*/
    @Override
    public void onExpectedCaloriesRetrieved(String expectedCalories) {
        this.expectedCalories = Integer.valueOf(expectedCalories);
        view.continueAfterGettingExpectedCalories();
    }

    /**{@inheritDoc}*/
    @Override
    public void onExpectedCaloriesError() {
        view.continueAfterGettingExpectedCalories();
    }

    /**{@inheritDoc}*/
    @Override
    public void onMealsChanged(List<MealModelWithId> meals) {
        if (!meals.isEmpty()) {
            int day = calendarInteractor.getDayInLastWeekByDate(meals.get(0).getDate());
            if (day >= 0) {
                map = new TreeMap<>();
                totalCalories[day] = 0;
                Iterator<MealModelWithId> iterator = meals.iterator();
                while (iterator.hasNext()) {
                    MealModelWithId meal = iterator.next();
                    map.put(meal.getMealId(), meal);
                    totalCalories[day] += meal.getMeal().getCalories();
                }

                setTotalCaloriesInView(day);
            }
        } else {
            map = new TreeMap<>();
            totalCalories = new int[CaloriesPagerAdapter.PAGE_COUNT];
        }
    }

    private void setTotalCaloriesInView(int day) {
        if (totalCalories[day] != 0) {
            view.setTotalCalories(day, String.valueOf(totalCalories[day]), expectedCalories > 0 && totalCalories[day] > expectedCalories);

        } else {
            view.setTotalCalories(day, Constants.MESSAGE_NO_MEALS, false);
        }
    }
}
