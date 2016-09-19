package com.gircenko.gabriel.calcounter.mealList;

import com.gircenko.gabriel.calcounter.Constants;
import com.gircenko.gabriel.calcounter.models.MealModel;
import com.gircenko.gabriel.calcounter.repos.calendar.CalendarInteractor;
import com.gircenko.gabriel.calcounter.repos.firebase.authentication.FirebaseAuthInteractor;
import com.gircenko.gabriel.calcounter.repos.firebase.database.FirebaseDataInteractor;
import com.gircenko.gabriel.calcounter.repos.firebase.database.OnMealDataListener;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Gabriel Gircenko on 18-Sep-16.
 */
public class MealListPresenter implements IMealListPresenter, OnMealDataListener {

    private IMealListView view;
    private FirebaseAuthInteractor firebaseAuthInteractor;
    private FirebaseDataInteractor firebaseDataInteractor;
    private CalendarInteractor calendarInteractor;
    /** String key is the mealId */
    private Map<String, MealModel> map = new TreeMap<>();
    private int totalCalories;
    private String date;

    public MealListPresenter(IMealListView view) {
        this.view = view;
        firebaseAuthInteractor = new FirebaseAuthInteractor();
        this.firebaseDataInteractor = new FirebaseDataInteractor();
        this.calendarInteractor = new CalendarInteractor();
        this.totalCalories = 0;
    }

    /**{@inheritDoc}*/
    @Override
    public void getMealsByUser(String userId) {
        firebaseDataInteractor.getMealsByUser(userId, this);
    }

    /**{@inheritDoc}*/
    @Override
    public void getMealsByUserAndDate(String userId, String date) {
        this.date = date;
        firebaseDataInteractor.getMealsByUser(userId, this);
    }

    @Override
    public void getUserEmail() {
        view.setUserEmail(firebaseAuthInteractor.getCurrentUserEmail());
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

    private void add(String mealId, MealModel meal) {
        int day = calendarInteractor.getDayInLastWeekByFullDate(meal.getDate());
        if (day >= 0 && date != null && date.equals(meal.getDate().split("T")[0])) {
            map.put(mealId, meal);

            totalCalories += meal.getCalories();

            view.addMeal(mealId, meal);
            view.setTotalCalories(String.valueOf(totalCalories));
        }
    }

    private void remove(String mealId) {
        MealModel previousMeal = map.get(mealId);
        if (previousMeal != null) {
            int day = calendarInteractor.getDayInLastWeekByFullDate(previousMeal.getDate());
            if (day >= 0 && date != null && date.equals(previousMeal.getDate().split("T")[0])) {
                map.remove(mealId);

                totalCalories -= previousMeal.getCalories();

                view.removeMeal(mealId);
                view.setTotalCalories(String.valueOf(totalCalories));
            }
        }
    }
}
