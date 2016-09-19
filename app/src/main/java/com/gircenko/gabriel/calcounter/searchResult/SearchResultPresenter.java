package com.gircenko.gabriel.calcounter.searchResult;

import com.gircenko.gabriel.calcounter.Constants;
import com.gircenko.gabriel.calcounter.models.MealModel;
import com.gircenko.gabriel.calcounter.repos.firebase.database.FirebaseDataInteractor;
import com.gircenko.gabriel.calcounter.repos.firebase.database.OnMealDataListener;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Gabriel Gircenko on 19-Sep-16.
 */
public class SearchResultPresenter implements ISearchResultPresenter, OnMealDataListener {

    private ISearchResultView view;
    private FirebaseDataInteractor firebaseDataInteractor;
    /** String first key is the date in format {@link Constants#DATE_FORMAT} and the second key is the mealId for easier search*/
    private TreeMap<String, TreeMap<String, MealModel>> map = new TreeMap<>();

    public SearchResultPresenter(ISearchResultView view) {
        this.view = view;
        this.firebaseDataInteractor = new FirebaseDataInteractor();
    }

    @Override
    public void search(String userId, String dateStart, String dateEnd, String timeStart, String timeEnd) {
        firebaseDataInteractor.searchMeals(userId, dateStart, dateEnd, timeStart, timeEnd, this);
    }

    @Override
    public void onGotNewMeal(String mealId, MealModel meal) {
        add(mealId, meal);
    }

    @Override
    public void onMealChanged(String mealId, MealModel meal) {
        add(mealId, meal);
        remove(mealId);
    }

    @Override
    public void onMealRemoved(String mealId) {
        remove(mealId);
    }

    private void add(String mealId, MealModel meal) {
        String date = meal.getDate();
        TreeMap<String, MealModel> subMap = new TreeMap<>();
        if (map.containsKey(date)) {
            subMap = map.get(date);

        } else {
            // TODO add header in view
        }

        subMap.put(mealId, meal);
        map.put(date, subMap);

        // TODO update view
    }

    private void remove(String mealId) {
        // TODO
        MealModel previousMeal = null;
        Iterator<Map.Entry<String, TreeMap<String, MealModel>>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, TreeMap<String, MealModel>> entry = iterator.next();
            if (entry.getValue().containsKey(mealId)) {
                previousMeal = entry.getValue().get(mealId);
                map.get(entry.getKey()).remove(mealId);
                break;
            }
        }

        if (previousMeal != null) {

            // TODO update view and remove header if necessary
        }
    }
}
