package com.gircenko.gabriel.calcounter.mealList;

import com.gircenko.gabriel.calcounter.models.MealModel;
import com.gircenko.gabriel.calcounter.models.MealModelWithId;

/**
 * Created by Gabriel Gircenko on 18-Sep-16.
 */
public interface IMealListView {

    void addMeal(String mealId, MealModel meal);
    void removeMeal(String mealId);
    void setTotalCalories(String totalCalories);
    void setUserEmail(String email);
}
