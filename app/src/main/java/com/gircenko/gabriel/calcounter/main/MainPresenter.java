package com.gircenko.gabriel.calcounter.main;

import com.gircenko.gabriel.calcounter.models.MealModel;
import com.gircenko.gabriel.calcounter.models.MealModelWithId;
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

    private IMainView view;
    private FirebaseAuthInteractor firebaseAuthInteractor;
    private FirebaseDataInteractor firebaseDataInteractor;
    private CalendarInteractor calendarInteractor;

    /** String key is the date in format {@link com.gircenko.gabriel.calcounter.Constants#DATE_FORMAT} */
    private Map<String, MealModelWithId> map = new TreeMap<>();
    private int[] totalCalories;

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

    // TODO Optimize
    /**{@inheritDoc}*/
    @Override
    public Map<String, MealModelWithId> getMealsByCurrentUserAndDate(String date) {
        Map<String, MealModelWithId> mealsOfTheDay = new TreeMap<>();
        for (Map.Entry<String, MealModelWithId> entry : map.entrySet()) {
            String entryDate = entry.getKey();
            if (entryDate.equals(date)) {
                mealsOfTheDay.put(entry.getKey(), entry.getValue());
            }
        }

        return mealsOfTheDay;
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
            totalCalories[day] += meal.getCalories();
            String date = calendarInteractor.cutDateTimeToDate(meal.getDate());
            map.put(date, new MealModelWithId(meal, mealId));
            view.addToTotalCalories(day, String.valueOf(meal.getCalories()));
        }
    }
}
