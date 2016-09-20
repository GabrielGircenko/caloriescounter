package com.gircenko.gabriel.calcounter.repos.firebase.database;

import com.gircenko.gabriel.calcounter.models.MealModel;
import com.gircenko.gabriel.calcounter.models.MealModelWithId;

import java.util.List;

/**
 * Created by Gabriel Gircenko on 18-Sep-16.
 */
public interface OnMealDataListener {

    void onGotNewMeal(MealModelWithId meal);
    void onMealChanged(MealModelWithId meal);
    void onMealRemoved(String mealId);
}
