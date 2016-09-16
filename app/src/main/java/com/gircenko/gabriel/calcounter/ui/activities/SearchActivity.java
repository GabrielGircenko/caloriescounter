package com.gircenko.gabriel.calcounter.ui.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;

import com.gircenko.gabriel.calcounter.R;

import butterknife.ButterKnife;

/**
 * Created by Gabriel Gircenko on 14-Sep-16.
 */
public class SearchActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
    }
}
