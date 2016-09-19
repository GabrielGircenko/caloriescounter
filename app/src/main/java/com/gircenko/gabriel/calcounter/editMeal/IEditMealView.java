package com.gircenko.gabriel.calcounter.editMeal;

import com.gircenko.gabriel.calcounter.models.MealModel;

/**
 * Created by Gabriel Gircenko on 16-Sep-16.
 */
public interface IEditMealView {

    void setUser(String user);
    void setEditDescriptionField(String description);
    void setEditCaloriesField(String calories);
    void setEditDateField(String date);
    void setEditTimeField(String time);
    void onMealSaveSuccessful();
    void onMealSaveFailed();
    void onMealSaveFailedDueToIncorrectInput();
    void onMealDeleteSuccessful();
    void onMealDeleteFailed();
}
