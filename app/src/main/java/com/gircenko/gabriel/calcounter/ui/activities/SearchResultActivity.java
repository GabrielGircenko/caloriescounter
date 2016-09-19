package com.gircenko.gabriel.calcounter.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.gircenko.gabriel.calcounter.Constants;
import com.gircenko.gabriel.calcounter.R;
import com.gircenko.gabriel.calcounter.searchResult.ISearchResultView;
import com.gircenko.gabriel.calcounter.searchResult.SearchResultPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Gabriel Gircenko on 14-Sep-16.
 */
public class SearchResultActivity extends ActivityWithProgressDialog implements ISearchResultView {

    private SearchResultPresenter presenter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        ButterKnife.bind(this);

        presenter = new SearchResultPresenter(this);

        // TODO move this to presenter
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String userId = bundle.getString(Constants.BUNDLE_KEY_UID);
            String dateStart = bundle.getString(Constants.BUNDLE_KEY_DATE_START);
            String dateEnd = bundle.getString(Constants.BUNDLE_KEY_DATE_END);
            String timeStart = bundle.getString(Constants.BUNDLE_KEY_TIME_START);
            String timeEnd = bundle.getString(Constants.BUNDLE_KEY_TIME_END);

            // TODO show dialog
            presenter.search(userId, dateStart, dateEnd, timeStart, timeEnd);
        }
    }
}
