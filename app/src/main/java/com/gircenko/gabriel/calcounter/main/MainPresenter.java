package com.gircenko.gabriel.calcounter.main;

import com.gircenko.gabriel.calcounter.Constants;
import com.gircenko.gabriel.calcounter.models.MealModel;
import com.gircenko.gabriel.calcounter.repos.calendar.CalendarInteractor;
import com.gircenko.gabriel.calcounter.repos.firebase.authentication.FirebaseAuthInteractor;
import com.gircenko.gabriel.calcounter.repos.firebase.database.FirebaseDataInteractor;
import com.gircenko.gabriel.calcounter.repos.firebase.database.OnExpectedCaloriesRetrievedListener;
import com.gircenko.gabriel.calcounter.repos.firebase.database.OnMealDataListener;
import com.gircenko.gabriel.calcounter.ui.adapters.CaloriesPagerAdapter;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Gabriel Gircenko on 15-Sep-16.
 */
public class MainPresenter implements IMainPresenter, OnMealDataListener, OnExpectedCaloriesRetrievedListener {

    private IMainView view;
    private FirebaseAuthInteractor firebaseAuthInteractor;
    private FirebaseDataInteractor firebaseDataInteractor;
    private CalendarInteractor calendarInteractor;

    /** String key is the mealId */
    private Map<String, MealModel> map = new TreeMap<>();
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

    @Override
    public void getExpectedCalories() {
        firebaseDataInteractor.getExpectedCalories(firebaseAuthInteractor.getCurrentUserId(), this);
    }

    @Override
    public void onExpectedCaloriesRetrieved(String expectedCalories) {
        this.expectedCalories = Integer.valueOf(expectedCalories);
        view.continueAfterGettingExpectedCalories();
    }

    @Override
    public void onExpectedCaloriesError() {
        view.continueAfterGettingExpectedCalories();
    }

    /**{@inheritDoc}*/
    @Override
    public void onGotNewMeal(String mealId, MealModel meal) {
        add(mealId, meal);
    }

    /**{@inheritDoc}*/
    @Override
    public void onMealChanged(String mealId, MealModel meal) {
        remove(mealId);
        add(mealId, meal);
    }

    /**{@inheritDoc}*/
    @Override
    public void onMealRemoved(String mealId) {
        remove(mealId);
    }

    /** @param mealId Id of the meal to be added */
    private void add(String mealId, MealModel meal) {
        int day = calendarInteractor.getDayInLastWeekByDate(meal.getDate());
        if (day >= 0) {
            map.put(mealId, meal);
            totalCalories[day] += meal.getCalories();
            setTotalCaloriesInView(day);
        }
    }

    /**
     * @param mealId Id of the meal to be removed  */
    private void remove(String mealId) {
        MealModel previousMeal = map.get(mealId);
        if (previousMeal != null) {
            int day = calendarInteractor.getDayInLastWeekByDate(previousMeal.getDate());
            if (day >= 0) {
                map.remove(mealId);
                totalCalories[day] -= previousMeal.getCalories();
                setTotalCaloriesInView(day);
            }
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
