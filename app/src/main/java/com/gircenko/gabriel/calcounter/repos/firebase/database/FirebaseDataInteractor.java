package com.gircenko.gabriel.calcounter.repos.firebase.database;

import android.util.Log;

import com.gircenko.gabriel.calcounter.models.MealModel;
import com.gircenko.gabriel.calcounter.models.MealModelWithId;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Gabriel Gircenko on 17-Sep-16.
 */
public class FirebaseDataInteractor implements IFirebaseDataInteractor {

    private FirebaseDatabase firebaseDatabase;

    // tables
    private final String USERS = "Users";
    private final String MEALS = "Meals";
    private final String EXPECTED = "ExpectedCal";

    // fields
    private final String DATE = "date";
    private final String UID = "userId";

    private final String TAG = "FirebaseDataInteractor";

    public FirebaseDataInteractor() {
         firebaseDatabase = FirebaseDatabase.getInstance();
    }

    // TODO add date parameter
    @Override
    public void saveMeal(String userId, String date, MealModel meal, final OnEditMealListener listener) {
        DatabaseReference databaseReference = firebaseDatabase.getReference(USERS).child(userId).child(MEALS).child(date);
        databaseReference.push().setValue(meal, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                listener.onSaveSuccess(databaseError == null);
            }
        });
    }

    @Override
    public void saveMeal(String userId, String date, String mealId, MealModel meal, final OnEditMealListener listener) {
        DatabaseReference databaseReference = firebaseDatabase.getReference(USERS).child(userId).child(MEALS).child(date).child(mealId);
        databaseReference.setValue(meal, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                listener.onSaveSuccess(databaseError == null);
            }
        });
    }

    /**{@inheritDoc}*/
    @Override
    public void deleteMeal(String userId, String date, String mealId, final OnEditMealListener listener) {
        DatabaseReference databaseReference = firebaseDatabase.getReference(USERS).child(userId).child(MEALS).child(date).child(mealId);
        databaseReference.removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                listener.onDeleteSuccess(databaseError == null);
            }
        });
    }

    /**{@inheritDoc}*/
    @Override
    public void getMealsByUser(String userId, final OnMealListDataListener listener) {
        DatabaseReference databaseReference = firebaseDatabase.getReference(USERS).child(userId).child(MEALS);
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.i(TAG, "onChildAdded");
                if (listener != null) {
                    // TODO Make it simpler
                    listener.onMealsChanged(getMealList(dataSnapshot));
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Log.i(TAG, "onChildChanged");
                if (listener != null) {
                    // TODO Make it simpler
                    listener.onMealsChanged(getMealList(dataSnapshot));
                }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.i(TAG, "onChildRemoved");
                if (listener != null) {
                    // TODO Make it simpler
                    listener.onMealsChanged(getMealList(dataSnapshot));
                }
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                Log.i(TAG, "onChildMoved");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i(TAG, "onCancelled");
            }
        });
    }

    @Override
    public void getMealsByUserAndDate(String userId, String date, final OnMealListDataListener listener) {
        DatabaseReference databaseReference = firebaseDatabase.getReference(USERS).child(userId).child(MEALS);
        databaseReference.orderByKey().equalTo(date).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.i(TAG, "onChildAdded");
                if (listener != null) {
                    // TODO Make it simpler
                    listener.onMealsChanged(getMealList(dataSnapshot));
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Log.i(TAG, "onChildChanged");
                if (listener != null) {
                    // TODO Make it simpler
                    listener.onMealsChanged(getMealList(dataSnapshot));
                }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.i(TAG, "onChildRemoved");
                if (listener != null) {
                    // TODO Make it simpler
                    listener.onMealsChanged(getMealList(dataSnapshot));
                }
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                Log.i(TAG, "onChildMoved");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i(TAG, "onCancelled");
            }
        });
    }

    /**{@inheritDoc}*/
    @Override
    public void saveExpectedCalories(String userId, int expectedCalories, final OnSaveExpectedCaloriesListener listener) {
        DatabaseReference databaseReference = firebaseDatabase.getReference(USERS).child(userId).child(EXPECTED);
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
        DatabaseReference databaseReference = firebaseDatabase.getReference(USERS).child(userId).child(EXPECTED);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) listener.onExpectedCaloriesRetrieved(dataSnapshot.getValue().toString());
                else listener.onExpectedCaloriesError();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                listener.onExpectedCaloriesError();
            }
        });
    }

    @Override
    public void getMealByDateAndMealId(String userId, final String date, String mealId, final OnMealDataListener listener) {
        DatabaseReference databaseReference = firebaseDatabase.getReference(USERS).child(userId).child(MEALS).child(date);
        databaseReference.orderByKey().equalTo(mealId).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.i(TAG, "onChildAdded");
                if (listener != null) {
                    listener.onGotNewMeal(new MealModelWithId(
                            dataSnapshot.getValue(MealModel.class),
                            dataSnapshot.getKey(),
                            date));
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Log.i(TAG, "onChildChanged");
                if (listener != null) {
                    listener.onMealChanged(new MealModelWithId(
                            dataSnapshot.getValue(MealModel.class),
                            dataSnapshot.getKey(),
                            date));
                }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.i(TAG, "onChildRemoved");
                if (listener != null) {
                    listener.onMealRemoved(dataSnapshot.getKey());
                }
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                Log.i(TAG, "onChildMoved");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i(TAG, "onCancelled");
            }
        });
    }

    private MealModelWithId getMeal(DataSnapshot dataSnapshot) {
        DataSnapshot child = dataSnapshot.getChildren().iterator().next();
        return new MealModelWithId(
                child.getValue(MealModel.class),
                child.getKey(),
                dataSnapshot.getKey());
    }

    private List<MealModelWithId> getMealList(DataSnapshot dataSnapshot) {
        List<MealModelWithId> meals = new ArrayList<>();
        Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
        while (iterator.hasNext()) {
            DataSnapshot child = iterator.next();
            meals.add(new MealModelWithId(
                    child.getValue(MealModel.class),
                    child.getKey(),
                    dataSnapshot.getKey()));
        }

        return meals;
    }
}
