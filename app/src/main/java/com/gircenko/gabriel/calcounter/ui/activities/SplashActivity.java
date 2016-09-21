package com.gircenko.gabriel.calcounter.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.gircenko.gabriel.calcounter.Constants;
import com.gircenko.gabriel.calcounter.R;
import com.gircenko.gabriel.calcounter.caloriesFragment.OnCaloriesFragmentListener;
import com.gircenko.gabriel.calcounter.main.IMainView;
import com.gircenko.gabriel.calcounter.main.MainPresenter;
import com.gircenko.gabriel.calcounter.splash.main.ISplashPresenter;
import com.gircenko.gabriel.calcounter.splash.main.ISplashView;
import com.gircenko.gabriel.calcounter.splash.main.SplashPresenter;
import com.gircenko.gabriel.calcounter.ui.adapters.CaloriesPagerAdapter;
import com.gircenko.gabriel.calcounter.ui.fragments.CaloriesFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Gabriel Gircenko on 13-Sep-16.
 */
public class SplashActivity extends Activity implements ISplashView {

    private SplashPresenter presenter;
    private int SPLASH_DISPLAY_LENGTH = 2300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        presenter = new SplashPresenter(this);
        presenter.validateCurrentUser();
    }

    /**{@inheritDoc}*/
    @Override
    public void userLoggedIn() {
        waitThanStartActivity(MainActivity.class);
    }

    /**{@inheritDoc}*/
    @Override
    public void userNotLoggedInGoToLogin() {
        waitThanStartActivity(LoginActivity.class);
    }

    private void waitThanStartActivity(final Class activityClass) {
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                SplashActivity.this.finish();
                startActivity(new Intent(SplashActivity.this, activityClass));
            }

        }, SPLASH_DISPLAY_LENGTH);
    }
}
