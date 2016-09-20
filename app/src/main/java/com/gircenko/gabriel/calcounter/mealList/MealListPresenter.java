package com.gircenko.gabriel.calcounter.mealList;

import com.gircenko.gabriel.calcounter.models.MealModelWithId;
import com.gircenko.gabriel.calcounter.repos.calendar.CalendarInteractor;
import com.gircenko.gabriel.calcounter.repos.firebase.authentication.FirebaseAuthInteractor;
import com.gircenko.gabriel.calcounter.repos.firebase.database.FirebaseDataInteractor;
import com.gircenko.gabriel.calcounter.repos.firebase.database.OnMealListDataListener;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Gabriel Gircenko on 18-Sep-16.
 */
public class MealListPresenter implements IMealListPresenter, OnMealListDataListener {

    private IMealListView view;
    private FirebaseAuthInteractor firebaseAuthInteractor;
    private FirebaseDataInteractor firebaseDataInteractor;
    private CalendarInteractor calendarInteractor;
    /** String key is the mealId */
    private Map<String, MealModelWithId> map = new TreeMap<>();
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
    public void getMealsByUserAndDate(String userId, String date) {
        this.date = date;
        firebaseDataInteractor.getMealsByUserAndDate(userId, date, this);
    }

    @Override
    public void getUserEmail() {
        view.setUserEmail(firebaseAuthInteractor.getCurrentUserEmail());
    }

    /*private void add(MealModelWithId meal) {
        int day = calendarInteractor.getDayInLastWeekByDate(meal.getDate());
        if (day >= 0 && date != null && date.equals(meal.getDate())) {
            map.put(meal.getMealId(), meal);

            totalCalories += meal.getMeal().getCalories();

            view.addMeal(meal);
            view.setTotalCalories(String.valueOf(totalCalories));
        }
    }

    private void remove(String mealId) {
        MealModelWithId previousMeal = map.get(mealId);
        if (previousMeal != null) {
            int day = calendarInteractor.getDayInLastWeekByDate(previousMeal.getDate());
            if (day >= 0 && date != null && date.equals(previousMeal.getDate())) {
                map.remove(mealId);

                totalCalories -= previousMeal.getMeal().getCalories();

                view.removeMeal(mealId);
                view.setTotalCalories(String.valueOf(totalCalories));
            }
        }
    }*/

    @Override
    public void onMealsChanged(List<MealModelWithId> meals) {
        if (!meals.isEmpty()) {
            int day = calendarInteractor.getDayInLastWeekByDate(meals.get(0).getDate());
            if (day >= 0 && date != null && date.equals(meals.get(0).getDate())) {
                map = new TreeMap<>();
                totalCalories = 0;

                Iterator<MealModelWithId> iterator = meals.iterator();
                while (iterator.hasNext()) {
                    MealModelWithId meal = iterator.next();
                    map.put(meal.getMealId(), meal);
                    totalCalories += meal.getMeal().getCalories();
                }

                view.setMeals(meals);
                view.setTotalCalories(String.valueOf(totalCalories));
            }

        } else {
            map = new TreeMap<>();
            totalCalories = 0;
            view.setMeals(meals);
            view.setTotalCalories(String.valueOf(totalCalories));
        }
    }
}
