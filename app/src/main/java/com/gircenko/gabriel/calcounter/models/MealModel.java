package com.gircenko.gabriel.calcounter.models;

/**
 * Created by Gabriel Gircenko on 17-Sep-16.
 */
public class MealModel {

    private String description;
    private long calories;
    /** In {@link com.gircenko.gabriel.calcounter.Constants#TIME_FORMAT} */
    private String time;

    public MealModel() {}

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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
