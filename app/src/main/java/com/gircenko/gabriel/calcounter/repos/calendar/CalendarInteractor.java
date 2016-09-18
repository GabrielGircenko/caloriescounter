package com.gircenko.gabriel.calcounter.repos.calendar;

import com.gircenko.gabriel.calcounter.Constants;
import com.gircenko.gabriel.calcounter.ui.adapters.CaloriesPagerAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Gabriel Gircenko on 16-Sep-16.
 */
public class CalendarInteractor implements ICalendarInteractor {

    private Calendar calendar;
    private Date date;

    private final long DAY = 1000 * 60 * 60 * 24;

    public CalendarInteractor() {
        calendar = Calendar.getInstance();
    }

    /**{@inheritDoc}*/
    @Override
    public void setDate(int year, int month, int day) {
        calendar.set(year, month, day);
        date = calendar.getTime();
    }

    /**{@inheritDoc}*/
    @Override
    public void setDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
        try {
            this.date = sdf.parse(date);    // TODO test this so that time is not changed

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**{@inheritDoc}*/
    @Override
    public void setTime(int hour, int minute) {
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        date = calendar.getTime();
    }

    /**{@inheritDoc}*/
    @Override
    public void setTime(String time) {

        SimpleDateFormat sdf = new SimpleDateFormat(Constants.TIME_FORMAT);
        try {
            this.date = sdf.parse(time);    // TODO test this so that date is not changed

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**{@inheritDoc}*/
    @Override
    public void initializeDateModel() {
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
        // TODO finish
        date = calendar.getTime();
    }

    /**{@inheritDoc}*/
    @Override
    public String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
        return sdf.format(date);
    }

    /**{@inheritDoc}*/
    @Override
    public String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.TIME_FORMAT);
        return sdf.format(date);
    }

    /**{@inheritDoc}*/
    @Override
    public String getDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
        return sdf.format(date);
    }

    /**{@inheritDoc}*/
    @Override
    public int getYear() {
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**{@inheritDoc}*/
    @Override
    public int getMonth() {
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH);
    }

    /**{@inheritDoc}*/
    public int getDAY() {
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**{@inheritDoc}*/
    @Override
    public int getHour() {
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**{@inheritDoc}*/
    @Override
    public int getMinute() {
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    /**{@inheritDoc}*/
    @Override
    public int getDayInLastWeekByFullDate(String dateTime) {
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
        Date dateDate = null;
        try {
            dateDate = sdf.parse(dateTime);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (dateDate != null) {
            Calendar todayDateCalendar = getTodaysCalendar();
            Date now = new Date();
            long todaysDiff = now.getTime() - todayDateCalendar.getTime().getTime();
            long diff = now.getTime() - dateDate.getTime();
            if (todaysDiff < diff) {
                return CaloriesPagerAdapter.PAGE_COUNT - 2 - millisecondsToDays(todayDateCalendar.getTime().getTime() - dateDate.getTime());

            } else {
                return CaloriesPagerAdapter.PAGE_COUNT - 1;
            }

        } else {
            return -1;
        }
    }

    /**{@inheritDoc}*/
    @Override
    public String getDateByPage(int page) {
        // today
        Calendar date = getTodaysCalendar();
        date.add(Calendar.DAY_OF_MONTH, - (CaloriesPagerAdapter.PAGE_COUNT - 1 - page));

        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
        return sdf.format(date.getTime());
    }

    @Override
    public String cutDateTimeToDate(String dateTime) {
        return dateTime.split("T")[0];
    }

    private Calendar getTodaysCalendar() {
        // today
        Calendar gregorianCalendar = new GregorianCalendar();
        // reset hour, minutes, seconds and millis
        gregorianCalendar.set(Calendar.HOUR_OF_DAY, 0);
        gregorianCalendar.set(Calendar.MINUTE, 0);
        gregorianCalendar.set(Calendar.SECOND, 0);
        gregorianCalendar.set(Calendar.MILLISECOND, 0);

        return gregorianCalendar;
    }

    private int millisecondsToDays(long milliseconds) {
        return (int) (milliseconds / DAY);
    }

    private long daysToMilliseconds(int days) {
        return DAY * days;
    }
}
