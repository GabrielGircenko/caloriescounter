package com.gircenko.gabriel.calcounter.models;

/**
 * Created by Gabriel Gircenko on 18-Sep-16.
 */
public class MealModelWithId {

    private MealModel meal;
    private String mealId;
    private String date;

    public MealModelWithId(MealModel meal, String mealId, String date) {
        this.meal = meal;
        this.mealId = mealId;
        this.date = date;
    }

    public MealModel getMeal() {
        return meal;
    }

    public String getMealId() {
        return mealId;
    }

    public String getDate() {
        return date;
    }

    public void setMeal(MealModel meal) {
        this.meal = meal;
    }

    public void setMealId(String mealId) {
        this.mealId = mealId;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
