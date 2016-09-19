package com.gircenko.gabriel.calcounter.mealList;

import com.gircenko.gabriel.calcounter.models.MealModelWithId;
import com.gircenko.gabriel.calcounter.repos.firebase.database.OnMealDataListener;

import java.util.Map;

/**
 * Created by Gabriel Gircenko on 18-Sep-16.
 */
public interface IMealListPresenter {

    void getMealsByUser(String userId);

    void getMealsByUserAndDate(String userId, String date);

    void getUserEmail();
}
