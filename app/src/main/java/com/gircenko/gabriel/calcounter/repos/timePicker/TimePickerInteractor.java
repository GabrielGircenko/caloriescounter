package com.gircenko.gabriel.calcounter.repos.timePicker;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;

/**
 * Created by Gabriel Gircenko on 17-Sep-16.
 */
public class TimePickerInteractor implements ITimePickerInteractor {

    TimePickerDialog dialog;

    public TimePickerInteractor(Context context, TimePickerDialog.OnTimeSetListener listener, int hour, int minute) {
        dialog = new TimePickerDialog(context, listener, hour, minute, true);
    }

    @Override
    public void showDialog() {
        dialog.show();
    }
}
