package com.gircenko.gabriel.calcounter.caloriesFragment;

import com.gircenko.gabriel.calcounter.repos.calendar.CalendarInteractor;
import com.gircenko.gabriel.calcounter.repos.firebase.FirebaseInteractor;

/**
 * Created by Gabriel Gircenko on 16-Sep-16.
 */
public class CaloriesPresenter implements ICaloriesPresenter {

    ICaloriesView view;
    CalendarInteractor calendarInteractor;
    FirebaseInteractor firebaseInteractor;

    public CaloriesPresenter(ICaloriesView view) {
        this.view = view;
        this.calendarInteractor = new CalendarInteractor();
        this.firebaseInteractor = new FirebaseInteractor();
    }

    @Override
    public void getDateAfterMealListClicked() {
        calendarInteractor.getDateTime();
    }
}
