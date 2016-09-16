package com.gircenko.gabriel.calcounter.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gircenko.gabriel.calcounter.R;

/**
 * Created by Gabriel Gircenko on 15-Sep-16.
 */
public class SearchResultFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_result, container, false);
    }
}
