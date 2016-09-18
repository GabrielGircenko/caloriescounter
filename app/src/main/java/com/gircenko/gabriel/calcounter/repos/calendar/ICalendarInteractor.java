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
    int getDAY();
    int getHour();
    int getMinute();

    /**
     * @param dateTime This is in format of {@link com.gircenko.gabriel.calcounter.Constants#DATE_TIME_FORMAT}
     * @return 6 - if today, 5 - if yesterday, ... 0 - if 7 days before today. Negative value if error occurred or is not applicable.
     */
    int getDayInLastWeekByFullDate(String dateTime);

    /**
     *
     * @param page Page number. 6 - if today, 5 - if yesterday, ... 0 - if 7 days before today.
     * @return Date in format of {@link com.gircenko.gabriel.calcounter.Constants#DATE_FORMAT}
     */
    String getDateByPage(int page);
}
