package com.gircenko.gabriel.calcounter.settings;

/**
 * Created by Gabriel Gircenko on 17-Sep-16.
 */
public interface ISettingsView {

    void wrongInput();
    void onSuccess(boolean isSuccess);
    void setExpectedCalories(String expectedCalories);
}
