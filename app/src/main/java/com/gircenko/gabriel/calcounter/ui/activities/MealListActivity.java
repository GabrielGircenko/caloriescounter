package com.gircenko.gabriel.calcounter.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.gircenko.gabriel.calcounter.Constants;
import com.gircenko.gabriel.calcounter.R;
import com.gircenko.gabriel.calcounter.mealList.IMealListView;
import com.gircenko.gabriel.calcounter.mealList.MealListPresenter;
import com.gircenko.gabriel.calcounter.models.MealModel;
import com.gircenko.gabriel.calcounter.models.MealModelWithId;
import com.gircenko.gabriel.calcounter.ui.adapters.CaloriesListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Gabriel Gircenko on 15-Sep-16.
 */
public class MealListActivity extends ActivityWithProgressDialog implements IMealListView {

    @BindView(R.id.lv_meal_list)
    ListView lv_meal_list;
    @BindView(R.id.tv_user)
    TextView tv_user;
    @BindView(R.id.tv_total_calories)
    TextView tv_total_calories;

    private MealListPresenter presenter;
    private CaloriesListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calories_list);
        ButterKnife.bind(this);

        presenter = new MealListPresenter(this);

        // TODO move this to presenter
        adapter = new CaloriesListAdapter(this);
        lv_meal_list.setAdapter(adapter);
        lv_meal_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MealListActivity.this, EditMealActivity.class);
                intent.putExtra(Constants.BUNDLE_KEY_MEAL_ID, ((MealModelWithId) adapterView.getItemAtPosition(i)).getId());
                startActivity(intent);
            }
        });

        // TODO move this to presenter
        String date = null;
        String userId = null;
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            date = bundle.getString(Constants.BUNDLE_KEY_DATE);
            userId = bundle.getString(Constants.BUNDLE_KEY_UID);
        }

        if (userId != null) {
            if (date != null) {
                setTitle(date);
                presenter.getMealsByUserAndDate(userId, date);

            } else {
                presenter.getMealsByUser(userId);
            }
        }

        presenter.getUserEmail();
    }

    /**{@inheritDoc}*/
    @Override
    public void addMeal(String mealId, MealModel meal) {
        MealModelWithId mealModelWithId = new MealModelWithId(meal, mealId);
        adapter.addItem(mealModelWithId);
    }

    /**{@inheritDoc}*/
    @Override
    public void removeMeal(String mealId) {
        adapter.removeItem(mealId);
    }

    /**{@inheritDoc}*/
    @Override
    public void setTotalCalories(String totalCalories) {
        tv_total_calories.setText(totalCalories);
    }

    /**{@inheritDoc}*/
    @Override
    public void setUserEmail(String email) {
        tv_user.setText(email);
    }
}
