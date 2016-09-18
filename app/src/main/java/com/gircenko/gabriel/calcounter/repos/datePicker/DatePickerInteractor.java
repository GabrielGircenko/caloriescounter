package com.gircenko.gabriel.calcounter.repos.datePicker;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;

/**
 * Created by Gabriel Gircenko on 17-Sep-16.
 */
public class DatePickerInteractor implements IDatePickerInteractor {

    DatePickerDialog dialog;

    public DatePickerInteractor(Context context, DatePickerDialog.OnDateSetListener listener, int year, int month, int day) {
        dialog = new DatePickerDialog(context, listener, year, month, day);
    }

    /**{@inheritDoc}*/
    @Override
    public void showDialog() {
        dialog.show();
    }
}
