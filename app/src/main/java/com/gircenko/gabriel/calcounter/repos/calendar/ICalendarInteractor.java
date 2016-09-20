package com.gircenko.gabriel.calcounter.repos.calendar;

import com.gircenko.gabriel.calcounter.models.StartOrEnd;

import java.text.ParseException;

/**
 * Created by Gabriel Gircenko on 16-Sep-16.
 */
public interface ICalendarInteractor {

    void setDate(StartOrEnd startOrEnd, int year, int month, int day);
    void setTime(StartOrEnd startOrEnd, int hour, int minute);
    void initializeDateModels();
    String getDate(StartOrEnd startOrEnd);
    String getTime(StartOrEnd startOrEnd);
    int getYear(StartOrEnd startOrEnd);
    int getMonth(StartOrEnd startOrEnd);
    int getDAY(StartOrEnd startOrEnd);
    int getHour(StartOrEnd startOrEnd);
    int getMinute(StartOrEnd startOrEnd);

    /**
     * @param dateTime This is in format of {@link com.gircenko.gabriel.calcounter.Constants#DATE_TIME_FORMAT}
     * @return 6 - if today, 5 - if yesterday, ... 0 - if 7 days before today. Negative value if error occurred or is not applicable.
     */
    int getDayInLastWeekByDate(String dateTime);

    /**
     *
     * @param page Page number. 6 - if today, 5 - if yesterday, ... 0 - if 7 days before today.
     * @return Date in format of {@link com.gircenko.gabriel.calcounter.Constants#DATE_FORMAT}
     */
    String getDateByPage(int page);

    boolean isDateInRange(String date, String dateStart, String dateEnd);
    boolean isTimeInRange(String time, String timeStart, String timeEnd);
}
