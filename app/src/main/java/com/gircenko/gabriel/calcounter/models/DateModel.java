package com.gircenko.gabriel.calcounter.models;

/**
 * Created by Gabriel Gircenko on 16-Sep-16.
 */
public class DateModel {

    private int year;
    private int month;
    private int day;
    private int hours;
    private int minutes;

    public DateModel(int year, int month, int day, int hours, int minutes) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hours = hours;
        this.minutes = minutes;
    }

    public DateModel(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hours = -1;
        this.minutes = -1;
    }

    public DateModel(int hours, int minutes) {
        this.year = -1;
        this.month = -1;
        this.day = -1;
        this.hours = hours;
        this.minutes = minutes;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public String getDateTime() { return day + "." + month + "." + year + "." + " " + hours + ":" + minutes; }

    public String getDate() { return day + "." + month + "." + year + "."; }

    public String getTime() { return hours + ":" + minutes; }
}
