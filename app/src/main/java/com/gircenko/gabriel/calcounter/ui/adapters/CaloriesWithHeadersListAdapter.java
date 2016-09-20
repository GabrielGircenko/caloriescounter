package com.gircenko.gabriel.calcounter.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gircenko.gabriel.calcounter.R;
import com.gircenko.gabriel.calcounter.models.MealModelWithId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Gabriel Gircenko on 18-Sep-16.
 */
public class CaloriesWithHeadersListAdapter extends BaseAdapter {

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_HEADER = 1;

    /** key is date */
    private Map<String, List<MealModelWithId>> mData = new TreeMap<>();
    private List<MealModelWithId> mArray = new ArrayList<>();

    private LayoutInflater inflater;

    public CaloriesWithHeadersListAdapter(Context context) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addItems(List<MealModelWithId> meals) {
        String date = meals.get(0).getDate();
        while (mData.containsKey(date)) {
            mData.remove(date);
        }

        meals.add(0, new MealModelWithId(null, null, null));
        mData.put(date, meals);
        mArray = new ArrayList<>();
        for(Map.Entry<String, List<MealModelWithId>> entry : mData.entrySet()) {
            mArray.addAll(entry.getValue());
        }

        notifyDataSetChanged();;
    }

    @Override
    public int getCount() {
        return mArray.size();        // TODO test if it returns the correct number
    }

    @Override
    public MealModelWithId getItem(int position) {
        return mArray.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        if (mArray.get(position).getMeal() != null) return VIEW_TYPE_ITEM;
        else return VIEW_TYPE_HEADER;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderItem holder;
        int viewType = this.getItemViewType(position);

        if (convertView == null) {
            holder = new ViewHolderItem();
            switch (viewType) {
                case VIEW_TYPE_ITEM:
                    convertView = inflater.inflate(R.layout.item_item, null);
                    holder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
                    holder.tv_description = (TextView) convertView.findViewById(R.id.tv_description);
                    holder.tv_calories = (TextView) convertView.findViewById(R.id.tv_calories);
                    break;

                case VIEW_TYPE_HEADER:
                    convertView = inflater.inflate(R.layout.item_header, null);
                    holder.tv_time = (TextView) convertView.findViewById(R.id.tv_header);
                    break;
            }

            convertView.setTag(holder);

        } else {
            holder = (ViewHolderItem) convertView.getTag();
        }

        if (viewType == VIEW_TYPE_ITEM) {
            MealModelWithId meal = getItem(position);
            holder.tv_time.setText(meal.getMeal().getTime());
            holder.tv_description.setText(meal.getMeal().getDescription());
            holder.tv_calories.setText(String.valueOf(meal.getMeal().getCalories()));

        } else if (viewType == VIEW_TYPE_HEADER) {
            MealModelWithId meal = getItem(position + 1);   //  to get the date
            holder.tv_time.setText(meal.getDate());
        }

        return convertView;
    }

    static class ViewHolderItem {
        public TextView tv_time;    // used for both header and item
        public TextView tv_description;
        public TextView tv_calories;
    }
}
