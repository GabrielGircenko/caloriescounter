package com.gircenko.gabriel.calcounter.repos.firebase.database;

/**
 * Created by Gabriel Gircenko on 17-Sep-16.
 */
public interface OnNameRetrievedListener {

    void onNameRetrieved(String name);
    void onNameError();
}
