package com.gircenko.gabriel.calcounter.ui.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.gircenko.gabriel.calcounter.R;
import com.gircenko.gabriel.calcounter.search.ISearchView;
import com.gircenko.gabriel.calcounter.search.SearchPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Gabriel Gircenko on 14-Sep-16.
 */
public class SearchActivity extends ActivityWithProgressDialog implements ISearchView {

    @BindView(R.id.tv_date_start)
    TextView tv_date_start;
    @BindView(R.id.tv_date_end)
    TextView tv_date_end;
    @BindView(R.id.tv_time_start)
    TextView tv_time_start;
    @BindView(R.id.tv_time_end)
    TextView tv_time_end;

    private SearchPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        presenter = new SearchPresenter(this);

        presenter.initializeDates();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                showProgressDialog("Searching... Please, wait.");
                // TODO
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void setDateStart(String date) {
        tv_date_start.setText(date);
    }

    @Override
    public void setDateEnd(String date) {
        tv_date_end.setText(date);
    }

    @Override
    public void setTimeStart(String time) {
        tv_time_start.setText(time);
    }

    @Override
    public void setTimeEnd(String time) {
        tv_date_end.setText(time);
    }

    @OnClick(R.id.tv_date_start)
    public void onDateStart() {

    }

    @OnClick(R.id.tv_date_end)
    public void onDateEnd() {

    }

    @OnClick(R.id.tv_time_start)
    public void onTimeStart() {

    }

    @OnClick(R.id.tv_time_end)
    public void onTimeEnd() {

    }
}
