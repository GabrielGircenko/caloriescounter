package com.gircenko.gabriel.calcounter.editMeal;

import com.gircenko.gabriel.calcounter.repos.calendar.CalendarInteractor;
import com.gircenko.gabriel.calcounter.repos.firebase.FirebaseInteractor;

/**
 * Created by Gabriel Gircenko on 16-Sep-16.
 */
public class EditMealPresenter implements IEditMealPresenter {

    IEditMealView view;
    CalendarInteractor calendarInteractor;
    FirebaseInteractor firebaseInteractor;

    public EditMealPresenter(IEditMealView view) {
        this.view = view;
        this.calendarInteractor = new CalendarInteractor();
        this.firebaseInteractor = new FirebaseInteractor();
    }

    @Override
    public void setDateModel(String date) {
        calendarInteractor.setDateModel(date);
    }

    @Override
    public void intializeDateModel() {
        calendarInteractor.initializeDateModel();
    }
}
