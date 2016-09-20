package com.gircenko.gabriel.calcounter.repos.firebase.database;

import com.gircenko.gabriel.calcounter.mealList.MealListPresenter;
import com.gircenko.gabriel.calcounter.models.MealModel;

/**
 * Created by Gabriel Gircenko on 17-Sep-16.
 */
public interface IFirebaseDataInteractor {

    void saveMeal(String userId, String date, MealModel meal, OnEditMealListener listener);
    void saveMeal(String userId, String date, String mealId, MealModel meal, OnEditMealListener listener);
    void saveExpectedCalories(String userId, int expectedCalories, OnSaveExpectedCaloriesListener listener);

    void getMealsByUser(String userId, OnMealListDataListener listener);
    void getMealsByUserAndDate(String userId, String date, OnMealListDataListener listener);
    void getMealByDateAndMealId(String userId, String date, String mealId, OnMealDataListener listener);
    void getExpectedCalories(String userId, OnExpectedCaloriesRetrievedListener listener);

    void deleteMeal(String userId, String date, String mealId, OnEditMealListener listener);
}
