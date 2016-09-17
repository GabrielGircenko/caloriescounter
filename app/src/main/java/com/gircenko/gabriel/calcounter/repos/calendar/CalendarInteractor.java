package com.gircenko.gabriel.calcounter.repos.calendar;

import com.gircenko.gabriel.calcounter.Constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Gabriel Gircenko on 16-Sep-16.
 */
public class CalendarInteractor implements ICalendarInteractor {

    Calendar calendar;
    Date date;

    public CalendarInteractor() {
        calendar = Calendar.getInstance();
    }

    /**{@inheritDoc}*/
    @Override
    public void setDate(int year, int month, int day) {
        calendar.set(year, month, day);
        date = calendar.getTime();
    }

    @Override
    public void setDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
        try {
            this.date = sdf.parse(date);    // TODO test this so that time is not changed

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setTime(int hour, int minute) {
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        date = calendar.getTime();
    }

    @Override
    public void setTime(String time) {

        SimpleDateFormat sdf = new SimpleDateFormat(Constants.TIME_FORMAT);
        try {
            this.date = sdf.parse(time);    // TODO test this so that date is not changed

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initializeDateModel() {
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
        date = calendar.getTime();
    }

    @Override
    public String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
        return sdf.format(date);
    }

    @Override
    public String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.TIME_FORMAT);
        return sdf.format(date);
    }

    @Override
    public String getDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
        return sdf.format(date);
    }

    @Override
    public int getYear() {
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    @Override
    public int getMonth() {
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH);
    }

    @Override
    public int getDay() {
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public int getHour() {
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    @Override
    public int getMinute() {
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }
}
