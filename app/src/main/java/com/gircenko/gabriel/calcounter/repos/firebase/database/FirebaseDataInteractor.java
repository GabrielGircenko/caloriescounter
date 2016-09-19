package com.gircenko.gabriel.calcounter.repos.firebase.database;

import android.util.Log;

import com.firebase.client.Firebase;
import com.gircenko.gabriel.calcounter.models.MealModel;
import com.google.firebase.database.ChildEventListener;
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

    // tables
    private final String MEALS = "Meals";
    private final String EXPECTED = "ExpectedCal";

    // fields
    private final String DATE = "date";
    private final String UID = "userId";

    private final String TAG = "FirebaseDataInteractor";

    public FirebaseDataInteractor() {
         firebaseDatabase = FirebaseDatabase.getInstance();
    }

    private OnMealDataListener listener;
    private ChildEventListener childEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            Log.i(TAG, "onChildAdded");
            if (listener != null) listener.onGotNewMeal(dataSnapshot.getKey(), dataSnapshot.getValue(MealModel.class));
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            Log.i(TAG, "onChildChanged");
            if (listener != null) listener.onMealChanged(dataSnapshot.getKey(), dataSnapshot.getValue(MealModel.class));
        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
            Log.i(TAG, "onChildRemoved");
            if (listener != null) listener.onMealRemoved(dataSnapshot.getKey());
        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            Log.i(TAG, "onChildMoved");
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Log.i(TAG, "onCancelled");
        }
    };

    /**{@inheritDoc}*/
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
    public void saveMeal(String mealId, MealModel meal, final OnEditMealListener listener) {
        DatabaseReference databaseReference = firebaseDatabase.getReference(MEALS).child(mealId);
        databaseReference.setValue(meal, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                listener.onSaveSuccess(databaseError == null);
            }
        });
    }

    /**{@inheritDoc}*/
    @Override
    public void deleteMeal(String mealId, final OnEditMealListener listener) {
        DatabaseReference databaseReference = firebaseDatabase.getReference(MEALS).child(mealId);
        databaseReference.removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                listener.onSaveSuccess(databaseError == null);
            }
        });
    }

    /**{@inheritDoc}*/
    @Override
    public void getMealsByUser(String userId, final OnMealDataListener listener) {
        this.listener = listener;
        DatabaseReference databaseReference = firebaseDatabase.getReference(MEALS);
        databaseReference.orderByChild(UID).equalTo(userId).addChildEventListener(childEventListener);
    }

    /**{@inheritDoc}*/
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

    /**{@inheritDoc}*/
    @Override
    public void getExpectedCalories(String userId, final OnExpectedCaloriesRetrievedListener listener) {
        DatabaseReference databaseReference = firebaseDatabase.getReference(EXPECTED).child(userId);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listener.onExpectedCaloriesRetrieved(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                listener.onExpectedCaloriesError();
            }
        });
    }

    @Override
    public void getMealByMealId(String mealId, final OnMealDataListener listener) {
        this.listener = listener;
        DatabaseReference databaseReference = firebaseDatabase.getReference(MEALS);
        databaseReference.orderByKey().equalTo(mealId).addChildEventListener(childEventListener);
    }

    @Override
    public void searchMeals(String userId, String dateStart, String dateEnd, String timeStart, String timeEnd, OnMealDataListener listener) {
        this.listener = listener;
        DatabaseReference databaseReference = firebaseDatabase.getReference(MEALS);
        databaseReference.orderByChild(UID).equalTo(userId).addChildEventListener(childEventListener);
        // TODO edit with ranges
    }
}
