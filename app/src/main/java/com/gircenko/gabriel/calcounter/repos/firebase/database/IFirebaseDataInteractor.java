package com.gircenko.gabriel.calcounter.repos.firebase.database;

import com.gircenko.gabriel.calcounter.models.MealModel;

/**
 * Created by Gabriel Gircenko on 17-Sep-16.
 */
public interface IFirebaseDataInteractor {

    void saveMeal(MealModel meal, OnEditMealListener listener);
    void saveMeal(String mealId, MealModel meal, OnEditMealListener listener);
    void deleteMeal(String mealId, OnEditMealListener listener);
    void getMealsByUser(String userId, OnMealDataListener listener);
    void saveExpectedCalories(String userId, int expectedCalories, OnSaveExpectedCaloriesListener listener);
    void getExpectedCalories(String userId, OnExpectedCaloriesRetrievedListener listener);
    void getMealByMealId(String mealId, OnMealDataListener listener);
}
