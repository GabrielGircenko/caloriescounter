package com.gircenko.gabriel.calcounter.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.gircenko.gabriel.calcounter.R;
import com.gircenko.gabriel.calcounter.caloriesFragment.OnCaloriesFragmentListener;
import com.gircenko.gabriel.calcounter.main.IMainView;
import com.gircenko.gabriel.calcounter.main.MainPresenter;
import com.gircenko.gabriel.calcounter.ui.adapters.CaloriesPagerAdapter;
import com.gircenko.gabriel.calcounter.ui.fragments.CaloriesFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Gabriel Gircenko on 13-Sep-16.
 */
public class MainActivity extends AppCompatActivity implements IMainView, OnCaloriesFragmentListener {

    @BindView(R.id.vp_calories)
    ViewPager vp_calories;

    private MainPresenter presenter;
    private CaloriesPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter = new MainPresenter(this);
        presenter.validateCurrentUser();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                startActivity(new Intent(this, SearchActivity.class));
                return true;

            case R.id.action_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;

            case R.id.action_logout:
                logoutClicked();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**{@inheritDoc}*/
    @OnClick(R.id.fab)
    @Override
    public void fabClicked() {
        startActivity(new Intent(this, EditMealActivity.class));
    }

    /**{@inheritDoc}*/
    @Override
    public void logoutClicked() {
        presenter.signOut();
        finish();
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void applyTotalCalories(int page, String totalCalories) {
        ((CaloriesFragment) adapter.getItem(page)).setTotalCalories(totalCalories);
    }

    @Override
    public void applyDate(int page, String date) {
        ((CaloriesFragment) adapter.getItem(page)).setDate(date);
    }

    /**{@inheritDoc}*/
    @Override
    public void userLoggedIn() {
        adapter = new CaloriesPagerAdapter(getSupportFragmentManager());
        vp_calories.setAdapter(adapter);
        vp_calories.setCurrentItem(CaloriesPagerAdapter.PAGE_COUNT - 1);  // sets last day as default

        presenter.applyDatesToPages();
        presenter.getMealsByCurrentUser();
    }

    /**{@inheritDoc}*/
    @Override
    public void userNotLoggedInGoToLogin() {
        finish();
        startActivity(new Intent(this, LoginActivity.class));
    }

    /**{@inheritDoc}*/
    @Override
    public void goToMealListActivity(String date) {
        // TODO finish
        startActivity(new Intent(this, MealListActivity.class));
    }
}
