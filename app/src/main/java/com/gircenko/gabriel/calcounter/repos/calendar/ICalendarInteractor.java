package com.gircenko.gabriel.calcounter.repos.calendar;

import com.gircenko.gabriel.calcounter.models.StartOrEnd;

import java.text.ParseException;

/**
 * Created by Gabriel Gircenko on 16-Sep-16.
 */
public interface ICalendarInteractor {

    void setDate(StartOrEnd startOrEnd, int year, int month, int day);
    void setDate(StartOrEnd startOrEnd, String date);
    void setTime(StartOrEnd startOrEnd, int hour, int minute);
    void setTime(StartOrEnd startOrEnd, String time);
    void initializeDateModels();
    String getDate(StartOrEnd startOrEnd);
    String getTime(StartOrEnd startOrEnd);
    String getDateTime(StartOrEnd startOrEnd);
    int getYear(StartOrEnd startOrEnd);
    int getMonth(StartOrEnd startOrEnd);
    int getDAY(StartOrEnd startOrEnd);
    int getHour(StartOrEnd startOrEnd);
    int getMinute(StartOrEnd startOrEnd);

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

    /**
     *
     * @param dateTime In a format of {@link com.gircenko.gabriel.calcounter.Constants#DATE_TIME_FORMAT}
     * @return Date in format of {@link com.gircenko.gabriel.calcounter.Constants#DATE_FORMAT}
     */
    String cutDateTimeToDate(String dateTime);
}
