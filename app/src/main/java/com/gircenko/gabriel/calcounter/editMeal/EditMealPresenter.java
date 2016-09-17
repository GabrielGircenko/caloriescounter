package com.gircenko.gabriel.calcounter.editMeal;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.gircenko.gabriel.calcounter.repos.calendar.CalendarInteractor;
import com.gircenko.gabriel.calcounter.repos.datePicker.DatePickerInteractor;
import com.gircenko.gabriel.calcounter.repos.firebase.FirebaseInteractor;
import com.gircenko.gabriel.calcounter.repos.timePicker.TimePickerInteractor;

/**
 * Created by Gabriel Gircenko on 16-Sep-16.
 */
public class EditMealPresenter implements IEditMealPresenter, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    IEditMealView view;
    FirebaseInteractor firebaseInteractor;
    CalendarInteractor calendarInteractor;
    DatePickerInteractor datePickerInteractor;
    TimePickerInteractor timePickerInteractor;

    public EditMealPresenter(IEditMealView view) {
        this.view = view;
        this.calendarInteractor = new CalendarInteractor();
        this.firebaseInteractor = new FirebaseInteractor();
    }

    /**{@inheritDoc}*/
    @Override
    public void setDateModel(String date) {
        calendarInteractor.setDate(date);
        setEditDateTime();
    }

    @Override
    public void intializeDateModel() {
        calendarInteractor.initializeDateModel();
        setEditDateTime();
    }

    @Override
    public void editDate(Context context) {
        datePickerInteractor = new DatePickerInteractor(
                context,
                this,
                calendarInteractor.getYear(),
                calendarInteractor.getMonth(),
                calendarInteractor.getDay());
        datePickerInteractor.showDialog();
    }

    @Override
    public void editTime(Context context) {
        timePickerInteractor = new TimePickerInteractor(
                context,
                this,
                calendarInteractor.getHour(),
                calendarInteractor.getMinute());
        timePickerInteractor.showDialog();
    }

    private void setEditDateTime() {
        view.setEditDate(calendarInteractor.getDate());
        view.setEditTime(calendarInteractor.getTime());
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        calendarInteractor.setDate(year, month, day);
        view.setEditDate(calendarInteractor.getDate());
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        calendarInteractor.setTime(hour, minute);
        view.setEditTime(calendarInteractor.getTime());
    }
}
