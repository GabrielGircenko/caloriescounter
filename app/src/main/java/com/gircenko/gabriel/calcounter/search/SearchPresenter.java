package com.gircenko.gabriel.calcounter.search;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.gircenko.gabriel.calcounter.models.StartOrEnd;
import com.gircenko.gabriel.calcounter.repos.calendar.CalendarInteractor;
import com.gircenko.gabriel.calcounter.repos.datePicker.DatePickerInteractor;
import com.gircenko.gabriel.calcounter.repos.timePicker.TimePickerInteractor;

/**
 * Created by Gabriel Gircenko on 19-Sep-16.
 */
public class SearchPresenter implements ISearchPresenter,
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private ISearchView view;
    private CalendarInteractor calendarInteractor;
    private StartOrEnd startOrEnd;
    private DatePickerInteractor datePickerInteractor;
    private TimePickerInteractor timePickerInteractor;

    public SearchPresenter(ISearchView view) {
        this.view = view;
        this.calendarInteractor = new CalendarInteractor();
    }

    @Override
    public void initializeDates() {
        calendarInteractor.initializeDateModels();
        view.setDateStart(calendarInteractor.getDate(StartOrEnd.START));
        view.setDateEnd(calendarInteractor.getDate(StartOrEnd.END));
        view.setTimeStart(calendarInteractor.getTime(StartOrEnd.START));
        view.setTimeEnd(calendarInteractor.getTime(StartOrEnd.END));
    }

    @Override
    public void editDate(Context context, StartOrEnd startOrEnd) {
        this.startOrEnd = startOrEnd;
        datePickerInteractor = new DatePickerInteractor(
                context,
                this,
                calendarInteractor.getYear(startOrEnd),
                calendarInteractor.getMonth(startOrEnd),
                calendarInteractor.getDAY(startOrEnd));
        datePickerInteractor.showDialog();
    }

    @Override
    public void editTime(Context context, StartOrEnd startOrEnd) {
        this.startOrEnd = startOrEnd;
        timePickerInteractor = new TimePickerInteractor(
                context,
                this,
                calendarInteractor.getHour(startOrEnd),
                calendarInteractor.getMinute(startOrEnd));
        timePickerInteractor.showDialog();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        calendarInteractor.setDate(startOrEnd, year, month, day);
        String date = calendarInteractor.getDate(startOrEnd);

        if (startOrEnd == StartOrEnd.START) view.setDateStart(date);
        else  view.setDateEnd(date);
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        calendarInteractor.setTime(startOrEnd, hour, minute);
        String time = calendarInteractor.getTime(startOrEnd);

        if (startOrEnd == StartOrEnd.START) view.setTimeStart(time);
        else view.setTimeEnd(time);
    }
}
