package com.gircenko.gabriel.calcounter.repos.firebase.database;

import com.gircenko.gabriel.calcounter.models.MealModelWithId;

import java.util.List;

/**
 * Created by Gabriel Gircenko on 20-Sep-16.
 */
public interface OnMealListDataListener {

    void onMealsChanged(List<MealModelWithId> meals);
}
