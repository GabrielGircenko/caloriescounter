package com.gircenko.gabriel.calcounter.repos.firebase.database;

/**
 * Created by Gabriel Gircenko on 17-Sep-16.
 */
public interface OnEditMealListener {

    void onSaveSuccess(boolean success);
    void onDeleteSuccess(boolean success);
}
