package com.gircenko.gabriel.calcounter.repos.calendar;

/**
 * Created by Gabriel Gircenko on 16-Sep-16.
 */
public interface ICalendarInteractor {

    void setDateModel(String date);
    void initializeDateModel();
    String getDate();
    String getTime();
    String getDateTime();
}
