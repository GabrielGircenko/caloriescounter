package com.gircenko.gabriel.calcounter.editMeal;

/**
 * Created by Gabriel Gircenko on 16-Sep-16.
 */
public interface IEditMealView {

    void setEditDate(String date);
    void setEditTime(String time);
    void onMealSaveSuccessful();
    void onMealSaveFailed();
    void onMealSaveFailedDueToIncorrectInput();
    void onMealDeleteSuccessful();
    void onMealDeleteFailed();
}
