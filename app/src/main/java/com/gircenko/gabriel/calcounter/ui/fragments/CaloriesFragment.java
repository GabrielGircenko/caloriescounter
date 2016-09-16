package com.gircenko.gabriel.calcounter.ui.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gircenko.gabriel.calcounter.R;
import com.gircenko.gabriel.calcounter.caloriesFragment.CaloriesPresenter;
import com.gircenko.gabriel.calcounter.caloriesFragment.ICaloriesView;
import com.gircenko.gabriel.calcounter.caloriesFragment.OnCaloriesFragmentListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Gabriel Gircenko on 15-Sep-16.
 */
public class CaloriesFragment extends Fragment implements ICaloriesView {

    @BindView(R.id.tv_date)
    TextView tv_date;
    @BindView(R.id.tv_calories)
    TextView tv_calories;
    @BindView(R.id.tv_empty)
    TextView tv_empty;

    CaloriesPresenter presenter;
    OnCaloriesFragmentListener callback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        callback = (OnCaloriesFragmentListener) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_calories, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(view);

        presenter = new CaloriesPresenter(this);
    }

    @OnClick(R.id.rl_calories)
    @Override
    public void goToMealListClicked() {
        presenter.getDateAfterMealListClicked();
    }

    @Override
    public void goToMealList(String date) {
        callback.goToMealListActivity(date);
    }
}
