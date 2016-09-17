package com.gircenko.gabriel.calcounter.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.gircenko.gabriel.calcounter.R;
import com.gircenko.gabriel.calcounter.settings.ISettingsView;
import com.gircenko.gabriel.calcounter.settings.SettingsPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Gabriel Gircenko on 14-Sep-16.
 */
public class SettingsActivity extends ActivityWithProgressDialog implements ISettingsView {

    @BindView(R.id.et_expected_calories)
    EditText et_expected_calories;

    private SettingsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);

        presenter = new SettingsPresenter(this);
        presenter.getExpectedCalories();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                showProgressDialog("Saving... Please, wait.");
                String expectedCalories = et_expected_calories.getText().toString();
                presenter.saveExpectedCalories(expectedCalories);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void wrongInput() {
        dismissProgressDialogAndShowToast("Please, enter expected calories.");
    }

    @Override
    public void onSuccess(boolean isSuccess) {
        if (isSuccess) {
            dismissProgressDialogAndShowToast("Saved successfully.");
            finish();

        } else {
            dismissProgressDialogAndShowToast("Save unsuccessful. Please, try again.");
        }
    }

    @Override
    public void setExpectedCalories(String expectedCalories) {
        et_expected_calories.setText(expectedCalories);
    }
}
