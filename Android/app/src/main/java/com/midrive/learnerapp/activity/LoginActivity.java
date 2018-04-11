package com.midrive.learnerapp.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;

import com.midrive.learnerapp.R;
import com.midrive.learnerapp.api.ApiHelper;
import com.midrive.learnerapp.databinding.ActivityLoginBinding;
import com.midrive.learnerapp.helper.RevealButtonHelper;
import com.midrive.learnerapp.model.Login;
import com.midrive.learnerapp.model.User;
import com.midrive.learnerapp.view.ErrorTextInputLayout;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.trello.rxlifecycle2.android.ActivityEvent;

import io.reactivex.functions.Consumer;

public class LoginActivity extends BaseActivity
        implements ErrorTextInputLayout.ErrorTextInputListener {

    private ActivityLoginBinding mBinding;

    @NotEmpty
    private EditText etEmail;
    @Password(min = 5)
    private EditText etPassword;

    private Validator mValidator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        setSupportActionBar(mBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mRevealHelper = new RevealButtonHelper(mBinding.flReveal, mBinding.bContinue);

        etEmail = mBinding.etEmail;
        etPassword = mBinding.etPassword;

        etPassword.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

        mBinding.tilEmail.addListener(this);
        mBinding.tilPassword.addListener(this);

        mValidator = new Validator(this);
        mValidator.setValidationListener(this);

    }

    @Override
    public void requiresValidation(TextInputLayout textInputLayout, EditText editText) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mValidator.validate();
            }
        });
    }

    public void submit(View view) {
        showSpinner();

        ApiHelper.login(mRealm, this, new Login(mBinding.etEmail.getText().toString(),
                mBinding.etPassword.getEditableText().toString()), this)
                .compose(this.<User>bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(User user) throws Exception {
                        goToNextActivity();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    private void goToNextActivity() {
        // TODO: Go to lesson list screen
        hideSpinner();
    }
}
