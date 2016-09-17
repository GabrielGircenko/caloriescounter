package com.gircenko.gabriel.calcounter.editMeal;

import android.content.Context;

/**
 * Created by Gabriel Gircenko on 16-Sep-16.
 */
public interface IEditMealPresenter {

    /**
     *
     * @param date {@link com.gircenko.gabriel.calcounter.Constants#DATE_FORMAT}
     */
    void setDateModel(String date);

    void intializeDateModel();

    void editDate(Context context);

    void editTime(Context context);
}
