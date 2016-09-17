package com.gircenko.gabriel.calcounter.repos.firebase.database;

import com.firebase.client.Firebase;
import com.gircenko.gabriel.calcounter.models.MealModel;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Gabriel Gircenko on 17-Sep-16.
 */
public class FirebaseInteractor implements IFirebaseInteractor {

    Firebase firebase;
    FirebaseDatabase firebaseDatabase;
    private final String ROOT = "https://calcounter-7fafc.firebaseio.com/";

    private final String MEALS = "Meals";

    public FirebaseInteractor() {
         firebaseDatabase = FirebaseDatabase.getInstance();
    }

    @Override
    public void saveMeal(MealModel meal, final OnEditMealListener listener) {
        DatabaseReference databaseReference = firebaseDatabase.getReference(MEALS);
        databaseReference.push().setValue(meal, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                listener.onSaveSuccess(databaseError == null);
            }
        });
    }

    @Override
    public void deleteMeal(MealModel meal, OnEditMealListener listener) {

    }
}
