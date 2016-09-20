package com.gircenko.gabriel.calcounter.ui.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Gabriel Gircenko on 21-Sep-16.
 */
public class UserSpinnerAdapter extends ArrayAdapter<String> {

    private List<String> ids = new ArrayList<>();

    public UserSpinnerAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void addItem(String userId, String name) {
        ids.add(userId);
        super.add(name);
    }

    public String getUserId(int selectedItemPosition) {
        return ids.get(selectedItemPosition);
    }

    @Override
    public String getItem(int position) {
        return super.getItem(position);
    }
}
