package com.gircenko.gabriel.calcounter.main;

/**
 * Created by Gabriel Gircenko on 15-Sep-16.
 */
public interface IMainView {

    void userLoggedIn();
    void userNotLoggedInGoToLogin();
    void fabClicked();
    void logoutClicked();
    void applyTotalCalories(int page, String totalCalories);
    void applyDate(int page, String date);
}
