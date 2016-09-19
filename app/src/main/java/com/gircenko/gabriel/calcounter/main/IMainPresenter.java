package com.gircenko.gabriel.calcounter.main;

import com.gircenko.gabriel.calcounter.models.MealModel;
import com.gircenko.gabriel.calcounter.models.MealModelWithId;

import java.util.Map;

/**
 * Created by Gabriel Gircenko on 15-Sep-16.
 */
public interface IMainPresenter {

    void validateCurrentUser();

    void signOut();

    void getMealsByCurrentUser();

    void applyDatesToPages();

    String getCurrentUserId();

    void getExpectedCalories();
}
