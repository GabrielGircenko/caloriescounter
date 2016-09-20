package com.gircenko.gabriel.calcounter.editMeal;

import android.content.Context;

import com.gircenko.gabriel.calcounter.models.MealModel;
import com.gircenko.gabriel.calcounter.models.MealModelWithId;

/**
 * Created by Gabriel Gircenko on 16-Sep-16.
 */
public interface IEditMealPresenter {

    void intializeDateModel();

    void editDate(Context context);

    void editTime(Context context);

    void attemptToSaveMeal(String description, String calories, String date, String time);

    void attemptToDeleteMeal();

    void getMeal(String date, String mealId);

    void getMeal(String userId, String date, String mealId);
}
