package com.gircenko.gabriel.calcounter.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Spinner;

import com.gircenko.gabriel.calcounter.Constants;
import com.gircenko.gabriel.calcounter.R;
import com.gircenko.gabriel.calcounter.editMeal.EditMealPresenter;
import com.gircenko.gabriel.calcounter.editMeal.IEditMealView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Gabriel Gircenko on 14-Sep-16.
 */
public class EditMealActivity extends AppCompatActivity implements IEditMealView {

    @BindView(R.id.sp_user)
    protected Spinner sp_user;
    @BindView(R.id.et_description)
    protected EditText et_description;
    @BindView(R.id.et_calories)
    protected EditText et_calories;
    @BindView(R.id.et_date)
    protected EditText et_date;
    @BindView(R.id.et_time)
    protected EditText et_time;

    EditMealPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_meal);
        ButterKnife.bind(this);

        presenter = new EditMealPresenter(this);

        String date = savedInstanceState.getString(Constants.BUNDLE_KEY_DATE);
        if (date != null) {


        } else {
            presenter.intializeDateModel();
        }
    }


}