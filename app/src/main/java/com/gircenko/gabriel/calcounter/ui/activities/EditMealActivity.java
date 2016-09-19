package com.gircenko.gabriel.calcounter.ui.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;

import com.gircenko.gabriel.calcounter.Constants;
import com.gircenko.gabriel.calcounter.R;
import com.gircenko.gabriel.calcounter.editMeal.EditMealPresenter;
import com.gircenko.gabriel.calcounter.editMeal.IEditMealView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Gabriel Gircenko on 14-Sep-16.
 */
public class EditMealActivity extends ActivityWithProgressDialog implements IEditMealView {

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

    private EditMealPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_meal);
        ButterKnife.bind(this);

        presenter = new EditMealPresenter(this);

        // TODO move this to presenter
        String mealId = getIntent().getStringExtra(Constants.BUNDLE_KEY_MEAL_ID);

        if (mealId != null) {
            presenter.getMealByMealId(mealId);

        } else {
            presenter.intializeDateModel();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_meal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                showProgressDialog("Deleting... Please, wait.");
                presenter.attemptToDeleteMeal();
                return true;

            case R.id.action_save:
                showProgressDialog("Saving... Please, wait.");
//                String user = sp_user.getSelectedItem().toString();
                String description = et_description.getText().toString();
                String calories = et_calories.getText().toString();
                String date = et_date.getText().toString();
                String time = et_time.getText().toString();
                presenter.attemptToSaveMeal("", description, calories, date, time);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**{@inheritDoc}*/
    @Override
    public void setUser(String user) {
        // TODO
    }

    /**{@inheritDoc}*/
    @Override
    public void setEditDescriptionField(String description) {
        et_description.setText(description);
    }

    /**{@inheritDoc}*/
    @Override
    public void setEditCaloriesField(String calories) {
        et_calories.setText(calories);
    }

    /**{@inheritDoc}*/
    @Override
    public void setEditDateField(String date) {
        et_date.setText(date);
    }

    /**{@inheritDoc}*/
    @Override
    public void setEditTimeField(String time) {
        et_time.setText(time);
    }


    /**{@inheritDoc}*/
    @Override
    public void onMealSaveSuccessful() {
        dismissProgressDialogAndShowToast("Save successful.");
        finish();
    }

    /**{@inheritDoc}*/
    @Override
    public void onMealSaveFailed() {
        dismissProgressDialogAndShowToast("Save failed. Please, try again.");
    }

    /**{@inheritDoc}*/
    @Override
    public void onMealSaveFailedDueToIncorrectInput() {
        dismissProgressDialogAndShowToast("Please, fill all fields");
    }

    /**{@inheritDoc}*/
    @Override
    public void onMealDeleteSuccessful() {
        dismissProgressDialogAndShowToast("Delete successful.");
        finish();
    }

    /**{@inheritDoc}*/
    @Override
    public void onMealDeleteFailed() {
        dismissProgressDialogAndShowToast("Delete failed. Please, try again.");
    }

    @OnClick(R.id.et_date)
    public void onDateClicked() {
        presenter.editDate(this);
    }

    @OnClick(R.id.et_time)
    public void onTimeClicked() {
        presenter.editTime(this);
    }
}
