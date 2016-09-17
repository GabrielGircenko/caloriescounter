package com.gircenko.gabriel.calcounter.repos.firebase.database;

import com.gircenko.gabriel.calcounter.models.MealModel;

/**
 * Created by Gabriel Gircenko on 17-Sep-16.
 */
public interface IFirebaseInteractor {

    void saveMeal(MealModel meal, OnEditMealListener listener);
    void deleteMeal(MealModel meal, OnEditMealListener listener);
}
