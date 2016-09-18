package com.gircenko.gabriel.calcounter.caloriesFragment;

/**
 * Created by Gabriel Gircenko on 16-Sep-16.
 */
public interface ICaloriesView {

    void goToMealListClicked();

    /** @param date Format of the value is {@link com.gircenko.gabriel.calcounter.Constants#DATE_TIME_FORMAT} */
    void goToMealList(String date);

    /** @param date Format of the value is {@link com.gircenko.gabriel.calcounter.Constants#DATE_TIME_FORMAT} */
    void setDate(String date);

    void setTotalCalories(String caloriesAtDate);
}
