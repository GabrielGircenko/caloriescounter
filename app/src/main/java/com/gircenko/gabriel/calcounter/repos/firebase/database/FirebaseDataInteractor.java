package com.gircenko.gabriel.calcounter.repos.firebase.database;

import com.firebase.client.Firebase;
import com.gircenko.gabriel.calcounter.models.MealModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Gabriel Gircenko on 17-Sep-16.
 */
public class FirebaseDataInteractor implements IFirebaseDataInteractor {

    Firebase firebase;
    FirebaseDatabase firebaseDatabase;
    private final String ROOT = "https://calcounter-7fafc.firebaseio.com/";

    private final String MEALS = "Meals";
    private final String EXPECTED = "ExpectedCal";

    public FirebaseDataInteractor() {
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
        // TODO
    }

    @Override
    public void getMealsByUser(String userId) {
        // TODO
    }

    @Override
    public void saveExpectedCalories(String userId, int expectedCalories, final OnSaveExpectedCaloriesListener listener) {
        DatabaseReference databaseReference = firebaseDatabase.getReference(EXPECTED).child(userId);
        databaseReference.setValue(expectedCalories, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                listener.onSuccess(databaseError == null);
            }
        });
    }

    @Override
    public void getExpectedCalories(String userId, final OnExpectedCaloriesRetrievedListener listener) {
        DatabaseReference databaseReference = firebaseDatabase.getReference(EXPECTED).child(userId);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listener.onExpectedCaloriesRetrieved(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }
}
