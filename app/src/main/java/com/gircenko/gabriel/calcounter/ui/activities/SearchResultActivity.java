package com.gircenko.gabriel.calcounter.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.gircenko.gabriel.calcounter.Constants;
import com.gircenko.gabriel.calcounter.R;
import com.gircenko.gabriel.calcounter.models.MealModelWithId;
import com.gircenko.gabriel.calcounter.searchResult.ISearchResultView;
import com.gircenko.gabriel.calcounter.searchResult.SearchResultPresenter;
import com.gircenko.gabriel.calcounter.ui.adapters.CaloriesWithHeadersListAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Gabriel Gircenko on 14-Sep-16.
 */
public class SearchResultActivity extends ActivityWithProgressDialog implements ISearchResultView {

    @BindView(R.id.lv_meal_list)
    ListView lv_meal_list;

    private SearchResultPresenter presenter;
    private CaloriesWithHeadersListAdapter adapter;
    private String userId;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        ButterKnife.bind(this);

        presenter = new SearchResultPresenter(this);

        adapter = new CaloriesWithHeadersListAdapter(this);
        lv_meal_list.setAdapter(adapter);
        lv_meal_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(SearchResultActivity.this, EditMealActivity.class);
                MealModelWithId meal = (MealModelWithId) adapterView.getItemAtPosition(i);
                intent.putExtra(Constants.BUNDLE_KEY_UID, userId);
                intent.putExtra(Constants.BUNDLE_KEY_MEAL_ID, meal.getMealId());
                intent.putExtra(Constants.BUNDLE_KEY_DATE, meal.getDate());
                startActivity(intent);
            }
        });

        // TODO move this to presenter
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            userId = bundle.getString(Constants.BUNDLE_KEY_UID);
            String dateStart = bundle.getString(Constants.BUNDLE_KEY_DATE_START);
            String dateEnd = bundle.getString(Constants.BUNDLE_KEY_DATE_END);
            String timeStart = bundle.getString(Constants.BUNDLE_KEY_TIME_START);
            String timeEnd = bundle.getString(Constants.BUNDLE_KEY_TIME_END);

            // TODO show dialog
            presenter.search(userId, dateStart, dateEnd, timeStart, timeEnd);
        }
    }

    @Override
    public void addMeals(List<MealModelWithId> meals) {
        adapter.addItems(meals);
    }
}
