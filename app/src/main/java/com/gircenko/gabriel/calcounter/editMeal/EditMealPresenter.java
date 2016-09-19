package com.gircenko.gabriel.calcounter.editMeal;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.gircenko.gabriel.calcounter.models.MealModel;
import com.gircenko.gabriel.calcounter.models.StartOrEnd;
import com.gircenko.gabriel.calcounter.repos.calendar.CalendarInteractor;
import com.gircenko.gabriel.calcounter.repos.datePicker.DatePickerInteractor;
import com.gircenko.gabriel.calcounter.repos.firebase.authentication.FirebaseAuthInteractor;
import com.gircenko.gabriel.calcounter.repos.firebase.database.FirebaseDataInteractor;
import com.gircenko.gabriel.calcounter.repos.firebase.database.OnEditMealListener;
import com.gircenko.gabriel.calcounter.repos.firebase.database.OnMealDataListener;
import com.gircenko.gabriel.calcounter.repos.timePicker.TimePickerInteractor;

/**
 * Created by Gabriel Gircenko on 16-Sep-16.
 */
public class EditMealPresenter implements IEditMealPresenter,
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener,
        OnEditMealListener, OnMealDataListener {

    IEditMealView view;
    FirebaseAuthInteractor firebaseAuthInteractor;
    FirebaseDataInteractor firebaseDataInteractor;
    CalendarInteractor calendarInteractor;
    DatePickerInteractor datePickerInteractor;
    TimePickerInteractor timePickerInteractor;
    MealModel meal;
    String mealId;

    public EditMealPresenter(IEditMealView view) {
        this.view = view;
        this.calendarInteractor = new CalendarInteractor();
        this.firebaseAuthInteractor = new FirebaseAuthInteractor();
        this.firebaseDataInteractor = new FirebaseDataInteractor();
    }

    /**{@inheritDoc}*/
    @Override
    public void setDateModel(String date) {
        calendarInteractor.setDate(StartOrEnd.START, date);
        setDateAndTime();
    }

    /**{@inheritDoc}*/
    @Override
    public void intializeDateModel() {
        calendarInteractor.initializeDateModels();
        setDateAndTime();
    }

    /**{@inheritDoc}*/
    @Override
    public void editDate(Context context) {
        datePickerInteractor = new DatePickerInteractor(
                context,
                this,
                calendarInteractor.getYear(StartOrEnd.START),
                calendarInteractor.getMonth(StartOrEnd.START),
                calendarInteractor.getDAY(StartOrEnd.START));
        datePickerInteractor.showDialog();
    }

    /**{@inheritDoc}*/
    @Override
    public void editTime(Context context) {
        timePickerInteractor = new TimePickerInteractor(
                context,
                this,
                calendarInteractor.getHour(StartOrEnd.START),
                calendarInteractor.getMinute(StartOrEnd.START));
        timePickerInteractor.showDialog();
    }

    /**{@inheritDoc}*/
    @Override
    public void attemptToSaveMeal(String user, String description, String calories, String date, String time) {
        if (calories.isEmpty() || date.isEmpty() || time.isEmpty()) {
            view.onMealSaveFailedDueToIncorrectInput();
            return;
        }

        if (meal == null) {
            meal = new MealModel();
        }

        if (user.isEmpty()) {
            meal.setUserId(firebaseAuthInteractor.getCurrentUserId());

        } else {
            meal.setUserId(user);
        }
        meal.setDescription(description);
        meal.setCalories(Integer.valueOf(calories));
        meal.setDate(date + "T" + time);

        if (mealId != null) {
            firebaseDataInteractor.saveMeal(mealId, meal, this);

        } else {
            firebaseDataInteractor.saveMeal(meal, this);
        }
    }

    /**{@inheritDoc}*/
    @Override
    public void attemptToDeleteMeal() {
        if (mealId != null) {
            firebaseDataInteractor.deleteMeal(mealId, this);
        }
    }

    @Override
    public void getMealByMealId(String mealId) {
        this.mealId = mealId;
        firebaseDataInteractor.getMealByMealId(mealId, this);
    }

    private void setDateAndTime() {
        view.setEditDateField(calendarInteractor.getDate(StartOrEnd.START));
        view.setEditTimeField(calendarInteractor.getTime(StartOrEnd.START));
    }

    /**{@inheritDoc}*/
    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        calendarInteractor.setDate(StartOrEnd.START, year, month, day);
        view.setEditDateField(calendarInteractor.getDate(StartOrEnd.START));
    }

    /**{@inheritDoc}*/
    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        calendarInteractor.setTime(StartOrEnd.START, hour, minute);
        view.setEditTimeField(calendarInteractor.getTime(StartOrEnd.START));
    }

    /**{@inheritDoc}*/
    @Override
    public void onSaveSuccess(boolean success) {
        if (success) view.onMealSaveSuccessful();
        else view.onMealSaveFailed();
    }

    /**{@inheritDoc}*/
    @Override
    public void onDeleteSuccess(boolean success) {
        if (success) view.onMealDeleteSuccessful();
        else view.onMealDeleteFailed();
    }

    /**{@inheritDoc}*/
    @Override
    public void onGotNewMeal(String mealId, MealModel meal) {
        add(mealId, meal);
    }

    /**{@inheritDoc}*/
    @Override
    public void onMealChanged(String mealId, MealModel meal) {
        remove(mealId);
        add(mealId, meal);
    }

    /**{@inheritDoc}*/
    @Override
    public void onMealRemoved(String mealId) {
        remove(mealId);
    }

    private void add(String mealId, MealModel meal) {
        if (this.mealId == null || this.mealId.equals(mealId)) {
            this.meal = meal;
            this.mealId = mealId;

            view.setUser("");
            view.setEditDescriptionField(meal.getDescription());
            view.setEditCaloriesField(String.valueOf(meal.getCalories()));
            view.setEditDateField(meal.getDate().split("T")[0]);
            view.setEditTimeField(meal.getDate().split("T")[1]);
        }
    }

    private void remove(String mealId) {
        if (this.mealId != null && this.mealId.equals(mealId)) {
            this.meal = null;
            this.mealId = null;
            intializeDateModel();
            view.setEditCaloriesField("");
            view.setEditDescriptionField("");
        }
    }
}
