package com.gircenko.gabriel.calcounter.mealList;

/**
 * Created by Gabriel Gircenko on 18-Sep-16.
 */
public class MealListPresenter implements IMealListPresenter {

    IMealListView view;

    public MealListPresenter(IMealListView view) {
        this.view = view;
    }
}
