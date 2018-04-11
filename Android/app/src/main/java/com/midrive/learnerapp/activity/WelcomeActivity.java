package com.midrive.learnerapp.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.midrive.learnerapp.R;
import com.midrive.learnerapp.databinding.ActivityWelcomeBinding;
import com.midrive.learnerapp.helper.RevealButtonHelper;

public class WelcomeActivity extends BaseActivity {

    private ActivityWelcomeBinding mBinding;

    private RevealButtonHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_welcome);

        mHelper = new RevealButtonHelper(mBinding.flReveal, mBinding.bContinue);

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mHelper.showBlueBackground(true);
            }
        }, 500);
    }

    public void submit(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
