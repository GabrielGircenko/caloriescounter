package com.gircenko.gabriel.calcounter.searchResult;

import com.gircenko.gabriel.calcounter.models.MealModelWithId;

import java.util.List;

/**
 * Created by Gabriel Gircenko on 19-Sep-16.
 */
public interface ISearchResultView {

    void addMeals(List<MealModelWithId> meals);
}
