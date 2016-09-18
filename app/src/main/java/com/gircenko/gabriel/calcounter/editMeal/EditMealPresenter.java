package com.gircenko.gabriel.calcounter.editMeal;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.gircenko.gabriel.calcounter.models.MealModel;
import com.gircenko.gabriel.calcounter.repos.calendar.CalendarInteractor;
import com.gircenko.gabriel.calcounter.repos.datePicker.DatePickerInteractor;
import com.gircenko.gabriel.calcounter.repos.firebase.authentication.FirebaseAuthInteractor;
import com.gircenko.gabriel.calcounter.repos.firebase.database.FirebaseDataInteractor;
import com.gircenko.gabriel.calcounter.repos.firebase.database.OnEditMealListener;
import com.gircenko.gabriel.calcounter.repos.timePicker.TimePickerInteractor;

/**
 * Created by Gabriel Gircenko on 16-Sep-16.
 */
public class EditMealPresenter implements IEditMealPresenter,
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener,
        OnEditMealListener {

    IEditMealView view;
    FirebaseAuthInteractor firebaseAuthInteractor;
    FirebaseDataInteractor firebaseDataInteractor;
    CalendarInteractor calendarInteractor;
    DatePickerInteractor datePickerInteractor;
    TimePickerInteractor timePickerInteractor;
    MealModel mealModel;

    public EditMealPresenter(IEditMealView view) {
        this.view = view;
        this.calendarInteractor = new CalendarInteractor();
        this.firebaseAuthInteractor = new FirebaseAuthInteractor();
        this.firebaseDataInteractor = new FirebaseDataInteractor();
        this.mealModel = new MealModel();
    }

    /**{@inheritDoc}*/
    @Override
    public void setDateModel(String date) {
        calendarInteractor.setDate(date);
        setEditDateTime();
    }

    /**{@inheritDoc}*/
    @Override
    public void intializeDateModel() {
        calendarInteractor.initializeDateModel();
        setEditDateTime();
    }

    /**{@inheritDoc}*/
    @Override
    public void editDate(Context context) {
        datePickerInteractor = new DatePickerInteractor(
                context,
                this,
                calendarInteractor.getYear(),
                calendarInteractor.getMonth(),
                calendarInteractor.getDAY());
        datePickerInteractor.showDialog();
    }

    /**{@inheritDoc}*/
    @Override
    public void editTime(Context context) {
        timePickerInteractor = new TimePickerInteractor(
                context,
                this,
                calendarInteractor.getHour(),
                calendarInteractor.getMinute());
        timePickerInteractor.showDialog();
    }

    /**{@inheritDoc}*/
    @Override
    public void attemptToSaveMeal(String user, String description, String calories, String date, String time) {
        if (calories.isEmpty() || date.isEmpty() || time.isEmpty()) {
            view.onMealSaveFailedDueToIncorrectInput();
            return;
        }

        if (user.isEmpty()) {
            mealModel.setUserId(firebaseAuthInteractor.getCurrentUserId());

        } else {
            mealModel.setUserId(user);
        }
        mealModel.setDescription(description);
        mealModel.setCalories(Integer.valueOf(calories));
        mealModel.setDate(date + "T" + time);

        firebaseDataInteractor.saveMeal(mealModel, this);
    }

    /**{@inheritDoc}*/
    @Override
    public void attemptToDeleteMeal() {
        // TODO
    }

    private void setEditDateTime() {
        view.setEditDate(calendarInteractor.getDate());
        view.setEditTime(calendarInteractor.getTime());
    }

    /**{@inheritDoc}*/
    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        calendarInteractor.setDate(year, month, day);
        view.setEditDate(calendarInteractor.getDate());
    }

    /**{@inheritDoc}*/
    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        calendarInteractor.setTime(hour, minute);
        view.setEditTime(calendarInteractor.getTime());
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
}
