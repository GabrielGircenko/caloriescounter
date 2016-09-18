package com.gircenko.gabriel.calcounter.repos.firebase.database;

import com.gircenko.gabriel.calcounter.models.MealModel;

/**
 * Created by Gabriel Gircenko on 18-Sep-16.
 */
public interface OnMealDataListener {

    void onGotNewMeal(String mealId, MealModel meal);
    void onMealChanged(String mealId, MealModel meal);
    void onMealRemoved(String mealId);
}
