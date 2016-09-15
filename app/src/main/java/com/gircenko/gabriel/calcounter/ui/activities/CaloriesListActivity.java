package com.gircenko.gabriel.calcounter.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gircenko.gabriel.calcounter.R;
import com.gircenko.gabriel.calcounter.main.MainPresenter;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.ButterKnife;

/**
 * Created by Gabriel Gircenko on 15-Sep-16.
 */
public class CaloriesListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calories_list);

        ButterKnife.bind(this);
    }
}
