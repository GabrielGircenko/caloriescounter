package com.gircenko.gabriel.calcounter.repos.firebase.authentication;

import com.gircenko.gabriel.calcounter.models.MealModel;

/**
 * Created by Gabriel Gircenko on 15-Sep-16.
 */
public interface IFirebaseAuthInteractor {

    boolean validatedCredentials(String email, String password);
    void loginWithCredentials(OnFirebaseAuthCompleteListener listener, String email, String password);
    void signupWithCredentials(OnFirebaseAuthCompleteListener listener, String email, String password);
    boolean isCurrentUserExisting();
    void signOut();
    String getCurrentUserId();
    String getCurrentUserEmail();
}
