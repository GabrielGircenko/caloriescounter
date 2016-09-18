package com.gircenko.gabriel.calcounter.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.gircenko.gabriel.calcounter.ui.fragments.CaloriesFragment;

import butterknife.ButterKnife;

/**
 * Created by Gabriel Gircenko on 16-Sep-16.
 */
public class CaloriesPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {

    public static final int PAGE_COUNT = 7;
    private CaloriesFragment[] fragments = new CaloriesFragment[PAGE_COUNT];

    public CaloriesPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (fragments[position] == null) {
            fragments[position] = new CaloriesFragment();
        }
        // TODO add data to calories fragment
        return fragments[position];
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}
