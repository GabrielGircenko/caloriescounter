package com.gircenko.gabriel.calcounter.models;

/**
 * Created by Gabriel Gircenko on 18-Sep-16.
 */
public class MealModelWithId {

    private MealModel meal;
    private String id;

    public MealModelWithId(MealModel meal, String id) {
        this.meal = meal;
        this.id = id;
    }

    public MealModel getMeal() {
        return meal;
    }

    public String getId() {
        return id;
    }
}
