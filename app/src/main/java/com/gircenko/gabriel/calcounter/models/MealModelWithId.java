package com.gircenko.gabriel.calcounter.models;

import com.gircenko.gabriel.calcounter.Constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Gabriel Gircenko on 18-Sep-16.
 */
public class MealModelWithId implements Comparable<MealModel> {

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

    @Override
    public int compareTo(MealModel s) {
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
        Date thisDate;
        Date sDate;
        try {
            thisDate = sdf.parse(meal.getDate());
            sDate = sdf.parse(s.getDate());

        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }

        return thisDate.compareTo(sDate);
    }
}
