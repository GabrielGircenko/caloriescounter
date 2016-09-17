package com.gircenko.gabriel.calcounter.repos.firebase.authentication;

/**
 * Created by Gabriel Gircenko on 15-Sep-16.
 */
public interface OnFirebaseAuthCompleteListener {

    void onSuccess();
    void onFailed();
}
