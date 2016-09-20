package com.gircenko.gabriel.calcounter.repos.firebase.database;

import com.gircenko.gabriel.calcounter.models.MealModel;

/**
 * Created by Gabriel Gircenko on 17-Sep-16.
 */
public interface IFirebaseDataInteractor {

    void getUserList();
    void getMealsByUser(String userId, OnMealListDataListener listener);
    void getMealsByUserAndDate(String userId, String date, OnMealListDataListener listener);
    void getMealByDateAndMealId(String userId, String date, String mealId, OnMealDataListener listener);
    void getExpectedCalories(String userId, OnExpectedCaloriesRetrievedListener listener);
    void getName(String userId, final OnNameRetrievedListener listener);

    void saveMeal(String userId, String date, MealModel meal, OnEditMealListener listener);
    void saveMeal(String userId, String date, String mealId, MealModel meal, OnEditMealListener listener);
    void saveExpectedCalories(String userId, int expectedCalories, OnSaveExpectedCaloriesListener listener);
    void saveName(String userId, String name, final  OnSaveNameListener listener);

    void deleteMeal(String userId, String date, String mealId, OnEditMealListener listener);

    void checkIfUserIsAdmin(String userId, OnIsAdminListener listener);
}
