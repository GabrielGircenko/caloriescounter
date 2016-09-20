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
import java.util.List;

/**
 * Created by Gabriel Gircenko on 18-Sep-16.
 */
public class CaloriesListAdapter extends BaseAdapter {

    private final int VIEW_TYPE_ITEM = 102;

    private List<MealModelWithId> mealItems = new ArrayList<>();

    private LayoutInflater inflater;

    public CaloriesListAdapter(Context context) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setItemList(List<MealModelWithId> meals) {
        mealItems = meals;
        notifyDataSetChanged();
    }

    public void removeItem(String mealId) {
        for (MealModelWithId meal : mealItems) {
            if (mealId.equals(meal.getMealId())) {
                mealItems.remove(meal);
                notifyDataSetChanged();
                return;
            }
        }
    }

    @Override
    public int getCount() {
        return mealItems.size();
    }

    @Override
    public MealModelWithId getItem(int position) {
        return mealItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return VIEW_TYPE_ITEM;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
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
            }

            convertView.setTag(holder);

        } else {
            holder = (ViewHolderItem) convertView.getTag();
        }

        holder.tv_time.setText(mealItems.get(position).getMeal().getTime());
        holder.tv_description.setText(mealItems.get(position).getMeal().getDescription());
        holder.tv_calories.setText(String.valueOf(mealItems.get(position).getMeal().getCalories()));

        return convertView;
    }

    static class ViewHolderItem {
        public TextView tv_time;
        public TextView tv_description;
        public TextView tv_calories;
    }
}
