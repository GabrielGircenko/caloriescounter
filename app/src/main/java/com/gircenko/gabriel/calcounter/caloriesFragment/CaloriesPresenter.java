package com.gircenko.gabriel.calcounter.caloriesFragment;

import com.gircenko.gabriel.calcounter.repos.calendar.CalendarInteractor;
import com.gircenko.gabriel.calcounter.repos.firebase.authentication.FirebaseAuthInteractor;

/**
 * Created by Gabriel Gircenko on 16-Sep-16.
 */
public class CaloriesPresenter implements ICaloriesPresenter {

    ICaloriesView view;
    CalendarInteractor calendarInteractor;
    FirebaseAuthInteractor firebaseAuthInteractor;

    public CaloriesPresenter(ICaloriesView view) {
        this.view = view;
        this.calendarInteractor = new CalendarInteractor();
        this.firebaseAuthInteractor = new FirebaseAuthInteractor();
    }

    @Override
    public void getDateAfterMealListClicked() {
        calendarInteractor.getDateTime();
    }
}
