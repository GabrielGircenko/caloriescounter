package com.gircenko.gabriel.calcounter.repos.calendar;

import com.gircenko.gabriel.calcounter.models.DateModel;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Gabriel Gircenko on 16-Sep-16.
 */
public class CalendarInteractor implements ICalendarInteractor {

    Calendar calendar;
    DateModel dateModel;

    public CalendarInteractor() {
        calendar = Calendar.getInstance();
    }

    @Override
    public void setDateModel(String date) {
        // TODO
    }

    @Override
    public void initializeDateModel() {
        dateModel = new DateModel(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.HOUR),    // TODO Check if it returns correct time pm
                calendar.get(Calendar.MINUTE));
    }

    @Override
    public String getDate() {
        return dateModel.getDate();
    }

    @Override
    public String getTime() {
        return dateModel.getTime();
    }

    @Override
    public String getDateTime() {
        return dateModel.getDateTime();
    }
}
