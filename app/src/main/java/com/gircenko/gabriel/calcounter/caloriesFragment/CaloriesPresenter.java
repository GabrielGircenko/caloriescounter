package com.gircenko.gabriel.calcounter.caloriesFragment;

import com.gircenko.gabriel.calcounter.models.StartOrEnd;
import com.gircenko.gabriel.calcounter.repos.calendar.CalendarInteractor;

/**
 * Created by Gabriel Gircenko on 16-Sep-16.
 */
public class CaloriesPresenter implements ICaloriesPresenter {

    private ICaloriesView view;
    private CalendarInteractor calendarInteractor;

    public CaloriesPresenter(ICaloriesView view) {
        this.view = view;
        this.calendarInteractor = new CalendarInteractor();
    }
}
