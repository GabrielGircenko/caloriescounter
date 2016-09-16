package com.gircenko.gabriel.calcounter.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.gircenko.gabriel.calcounter.ui.fragments.CaloriesFragment;

/**
 * Created by Gabriel Gircenko on 16-Sep-16.
 */
public class CaloriesPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {

    final int PAGE_COUNT = 7;

    public CaloriesPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        CaloriesFragment fragment = new CaloriesFragment();
        // TODO add data to calories fragment
        return fragment;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}
