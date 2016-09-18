package com.gircenko.gabriel.calcounter.models;

/**
 * Created by Gabriel Gircenko on 17-Sep-16.
 */
public class MealModel {

    private String userId;
    private String description;
    private long calories;
    /** In {@link com.gircenko.gabriel.calcounter.Constants#DATE_TIME_FORMAT} */
    private String date;

    public MealModel() {}

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getCalories() {
        return calories;
    }

    public void setCalories(long calories) {
        this.calories = calories;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
