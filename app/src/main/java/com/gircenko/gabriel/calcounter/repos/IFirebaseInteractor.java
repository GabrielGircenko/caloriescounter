package com.gircenko.gabriel.calcounter.repos;

/**
 * Created by Gabriel Gircenko on 15-Sep-16.
 */
public interface IFirebaseInteractor {

    boolean validatedCredentials(String email, String password);
    void loginWithCredentials(OnFirebaseCompleteListener listener, String email, String password);
    void signupWithCredentials(OnFirebaseCompleteListener listener, String email, String password);
}
