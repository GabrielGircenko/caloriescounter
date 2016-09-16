package com.gircenko.gabriel.calcounter.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.gircenko.gabriel.calcounter.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Gabriel Gircenko on 15-Sep-16.
 */
public class MealListActivity extends AppCompatActivity {

    @BindView(R.id.lv_meal_list)
    ListView lv_meal_list;
    @BindView(R.id.tv_user)
    TextView tv_user;
    @BindView(R.id.tv_total_calories)
    TextView tv_total_calories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calories_list);
        ButterKnife.bind(this);
    }
}
