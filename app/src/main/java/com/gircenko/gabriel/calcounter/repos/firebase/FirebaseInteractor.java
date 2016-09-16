package com.gircenko.gabriel.calcounter.repos.firebase;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Gabriel Gircenko on 15-Sep-16.
 */
public class FirebaseInteractor implements IFirebaseInteractor {

    FirebaseAuth firebaseAuth;

    public FirebaseInteractor() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public boolean validatedCredentials(String email, String password) {
        return !email.isEmpty() && !password.isEmpty();
    }

    @Override
    public void loginWithCredentials(final OnFirebaseCompleteListener listener, String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) listener.onSuccess();
                else listener.onFailed();
            }
        });
    }

    @Override
    public void signupWithCredentials(final OnFirebaseCompleteListener listener, String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) listener.onSuccess();
                else listener.onFailed();
            }
        });
    }

    @Override
    public boolean isCurrentUserExisting() {
        return firebaseAuth.getCurrentUser() != null;
    }

    @Override
    public void signOut() {
        firebaseAuth.signOut();
    }
}
