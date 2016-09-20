package com.gircenko.gabriel.calcounter.mealList;

import com.gircenko.gabriel.calcounter.models.MealModel;
import com.gircenko.gabriel.calcounter.models.MealModelWithId;

import java.util.List;

/**
 * Created by Gabriel Gircenko on 18-Sep-16.
 */
public interface IMealListView {

    void setMeals(List<MealModelWithId> meals);
    void removeMeal(String mealId);
    void setTotalCalories(String totalCalories);
    void setUserEmail(String email);
}
