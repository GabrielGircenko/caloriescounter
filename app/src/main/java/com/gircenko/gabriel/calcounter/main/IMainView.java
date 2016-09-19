package com.gircenko.gabriel.calcounter.main;

/**
 * Created by Gabriel Gircenko on 15-Sep-16.
 */
public interface IMainView {

    void userLoggedIn();
    void userNotLoggedInGoToLogin();
    void continueAfterGettingExpectedCalories();
    void fabClicked();
    void logoutClicked();
    void setTotalCalories(int page, String totalCalories, boolean isOverExpected);
    void applyDate(int page, String date);
}
