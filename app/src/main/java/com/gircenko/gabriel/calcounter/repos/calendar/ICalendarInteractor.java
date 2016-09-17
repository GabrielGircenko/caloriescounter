package com.gircenko.gabriel.calcounter.repos.calendar;

import java.text.ParseException;

/**
 * Created by Gabriel Gircenko on 16-Sep-16.
 */
public interface ICalendarInteractor {

    void setDate(int year, int month, int day);
    void setDate(String date);
    void setTime(int hour, int minute);
    void setTime(String time);
    void initializeDateModel();
    String getDate();
    String getTime();
    String getDateTime();
    int getYear();
    int getMonth();
    int getDay();
    int getHour();
    int getMinute();
}
