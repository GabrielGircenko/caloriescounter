package com.gircenko.gabriel.calcounter.search;

import android.content.Context;

import com.gircenko.gabriel.calcounter.models.StartOrEnd;

/**
 * Created by Gabriel Gircenko on 19-Sep-16.
 */
public interface ISearchPresenter {

    void initializeDates();

    void editDate(Context context, StartOrEnd startOrEnd);

    void editTime(Context context, StartOrEnd startOrEnd);
}
