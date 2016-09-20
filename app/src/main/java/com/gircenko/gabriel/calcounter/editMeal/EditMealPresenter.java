package com.gircenko.gabriel.calcounter.editMeal;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.gircenko.gabriel.calcounter.models.MealModel;
import com.gircenko.gabriel.calcounter.models.MealModelWithId;
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

    private IEditMealView view;
    private FirebaseAuthInteractor firebaseAuthInteractor;
    private FirebaseDataInteractor firebaseDataInteractor;
    private CalendarInteractor calendarInteractor;
    private DatePickerInteractor datePickerInteractor;
    private TimePickerInteractor timePickerInteractor;
    private MealModelWithId meal;

    public EditMealPresenter(IEditMealView view) {
        this.view = view;
        this.calendarInteractor = new CalendarInteractor();
        this.firebaseAuthInteractor = new FirebaseAuthInteractor();
        this.firebaseDataInteractor = new FirebaseDataInteractor();
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
    public void attemptToSaveMeal(String userId, String description, String calories, String date, String time) {
        if (calories.isEmpty() || date.isEmpty() || time.isEmpty()) {
            view.onMealSaveFailedDueToIncorrectInput();
            return;
        }

        MealModel mealModel = new MealModel();
        mealModel.setDescription(description);
        mealModel.setCalories(Integer.valueOf(calories));
        mealModel.setTime(time);

        if (meal == null) {
            meal = new MealModelWithId(mealModel, null, date);

        } else {
            meal.setDate(date);
            meal.setMeal(mealModel);
        }

        if (userId.isEmpty()) {
            userId = firebaseAuthInteractor.getCurrentUserId();
        }

        if (meal.getMealId() != null) {
            firebaseDataInteractor.saveMeal(userId, meal.getDate(), meal.getMealId(), mealModel, this);

        } else {
            firebaseDataInteractor.saveMeal(userId, meal.getDate(), mealModel, this);
        }
    }

    /**{@inheritDoc}*/
    @Override
    public void attemptToDeleteMeal() {
        if (meal != null && meal.getMealId() != null) {
            firebaseDataInteractor.deleteMeal(firebaseAuthInteractor.getCurrentUserId(), meal.getDate(), meal.getMealId(), this);
        }
    }

    @Override
    public void getMealByDateAndMealId(String date, String mealId) {
        if (meal == null) {
            meal = new MealModelWithId(null, null, null);
        }

        meal.setMealId(mealId);
        meal.setDate(date);
        firebaseDataInteractor.getMealByDateAndMealId(firebaseAuthInteractor.getCurrentUserId(), date, mealId, this);
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

    /**{@inheritDoc}
     * @param meal*/
    @Override
    public void onGotNewMeal(MealModelWithId meal) {
        add(meal);
    }

    /**{@inheritDoc}*/
    @Override
    public void onMealChanged(MealModelWithId meal) {
        remove(meal.getMealId());
        add(meal);
    }

    /**{@inheritDoc}*/
    @Override
    public void onMealRemoved(String mealId) {
        remove(mealId);
    }

    private void add(MealModelWithId meal) {
        if (this.meal == null || this.meal.getMealId() == null || this.meal.getMealId().equals(meal.getMealId())) {
            this.meal = meal;

            view.setUser("");
            view.setEditDescriptionField(meal.getMeal().getDescription());
            view.setEditCaloriesField(String.valueOf(meal.getMeal().getCalories()));
            view.setEditDateField(meal.getDate());
            view.setEditTimeField(meal.getMeal().getTime());
        }
    }

    private void remove(String mealId) {
        if (this.meal != null && this.meal.getMealId() != null && this.meal.getMealId().equals(mealId)) {
            this.meal = null;
            intializeDateModel();
            view.setEditCaloriesField("");
            view.setEditDescriptionField("");
        }
    }
}
