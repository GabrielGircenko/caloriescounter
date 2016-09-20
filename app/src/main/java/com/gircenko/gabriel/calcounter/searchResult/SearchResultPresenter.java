package com.gircenko.gabriel.calcounter.searchResult;

import com.gircenko.gabriel.calcounter.models.MealModelWithId;
import com.gircenko.gabriel.calcounter.repos.calendar.CalendarInteractor;
import com.gircenko.gabriel.calcounter.repos.firebase.database.FirebaseDataInteractor;
import com.gircenko.gabriel.calcounter.repos.firebase.database.OnMealListDataListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Gabriel Gircenko on 19-Sep-16.
 */
public class SearchResultPresenter implements ISearchResultPresenter, OnMealListDataListener {

    private ISearchResultView view;
    private FirebaseDataInteractor firebaseDataInteractor;
    private CalendarInteractor calendarInteractor;
    /** String key is date */
    private Map<String, MealModelWithId> map = new TreeMap<>();
    private String userId, dateStart, dateEnd, timeStart, timeEnd;
    private int totalCalories;

    public SearchResultPresenter(ISearchResultView view) {
        this.view = view;
        this.firebaseDataInteractor = new FirebaseDataInteractor();
        this.calendarInteractor = new CalendarInteractor();
        this.totalCalories = 0;
    }

    @Override
    public void search(String userId, String dateStart, String dateEnd, String timeStart, String timeEnd) {
        this.userId = userId;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        firebaseDataInteractor.getMealsByUser(userId, this);
    }

    @Override
    public void onMealsChanged(List<MealModelWithId> meals) {
        if (!meals.isEmpty() && calendarInteractor.isDateInRange(meals.get(0).getDate(), dateStart, dateEnd)) {
            List<MealModelWithId> mealsToRemove = new ArrayList<>();
            Iterator<MealModelWithId> iterator = meals.iterator();
            while (iterator.hasNext()) {
                MealModelWithId meal = iterator.next();
                if (!calendarInteractor.isTimeInRange(meal.getMeal().getTime(), timeStart, timeEnd)) {
                    mealsToRemove.add(meal);
                }
            }

            meals.removeAll(mealsToRemove);
            if (!meals.isEmpty()) {
                view.addMeals(meals);
            }

        } else {
            // TODO if you add another check to the if statement
        }
    }
}
