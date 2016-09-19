package com.gircenko.gabriel.calcounter.repos.firebase.authentication;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Gabriel Gircenko on 15-Sep-16.
 */
public class FirebaseAuthInteractor implements IFirebaseAuthInteractor {

    private FirebaseAuth firebaseAuth;

    public FirebaseAuthInteractor() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    /**{@inheritDoc}*/
    @Override
    public boolean validatedCredentials(String email, String password) {
        return !email.isEmpty() && !password.isEmpty();
    }

    /**{@inheritDoc}*/
    @Override
    public void loginWithCredentials(final OnFirebaseAuthCompleteListener listener, String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) listener.onSuccess();
                else listener.onFailed();
            }
        });
    }

    /**{@inheritDoc}*/
    @Override
    public void signupWithCredentials(final OnFirebaseAuthCompleteListener listener, String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) listener.onSuccess();
                else listener.onFailed();
            }
        });
    }

    /**{@inheritDoc}*/
    @Override
    public boolean isCurrentUserExisting() {
        return firebaseAuth.getCurrentUser() != null;
    }

    /**{@inheritDoc}*/
    @Override
    public void signOut() {
        firebaseAuth.signOut();
    }

    /**{@inheritDoc}*/
    @Override
    public String getCurrentUserId() {
        return firebaseAuth.getCurrentUser().getUid();
    }

    @Override
    public String getCurrentUserEmail() {
        return firebaseAuth.getCurrentUser().getEmail();
    }
}
