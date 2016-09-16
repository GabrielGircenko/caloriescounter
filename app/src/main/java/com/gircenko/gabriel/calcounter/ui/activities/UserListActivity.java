package com.gircenko.gabriel.calcounter.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gircenko.gabriel.calcounter.R;

import butterknife.ButterKnife;

/**
 * Created by Gabriel Gircenko on 15-Sep-16.
 */
public class UserListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        ButterKnife.bind(this);
    }
}
