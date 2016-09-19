package com.gircenko.gabriel.calcounter.search;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.gircenko.gabriel.calcounter.models.StartOrEnd;
import com.gircenko.gabriel.calcounter.repos.calendar.CalendarInteractor;

/**
 * Created by Gabriel Gircenko on 19-Sep-16.
 */
public class SearchPresenter implements ISearchPresenter,
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private ISearchView view;
    private CalendarInteractor calendarInteractor;
    private StartOrEnd startOrEnd;

    public SearchPresenter(ISearchView view) {
        this.view = view;
        this.calendarInteractor = new CalendarInteractor();
    }

    @Override
    public void initializeDates() {
        calendarInteractor.initializeDateModels();

    }

    @Override
    public void editDate(Context context, StartOrEnd startOrEnd) {
        this.startOrEnd = startOrEnd;
    }

    @Override
    public void editTime(Context context, StartOrEnd startOrEnd) {
        this.startOrEnd = startOrEnd;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {

    }
}
