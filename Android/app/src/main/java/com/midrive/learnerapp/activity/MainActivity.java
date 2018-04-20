package com.midrive.learnerapp.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.midrive.learnerapp.R;
import com.midrive.learnerapp.databinding.ActivityMainBinding;
import com.midrive.learnerapp.model.User;
import com.midrive.learnerapp.navigator.Navigator;

public class MainActivity extends BaseActivity {

    private static final int SPLASH_DURATION = 2000;

    private ActivityMainBinding mBinding;
    private Navigator navigator = new Navigator(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        User user = User.getCurrentUser(mRealm);

        if (user == null) {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    goToWelcomeActivity();
                }
            }, SPLASH_DURATION);
        } else {
            goToDashboardActivity();
        }
    }

    private void goToWelcomeActivity() {
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
        finish();
    }

    private void goToDashboardActivity() {
        navigator.showLessons();
    }
}
