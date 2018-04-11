package com.midrive.learnerapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewParent;
import android.widget.Toast;

import com.midrive.learnerapp.R;
import com.midrive.learnerapp.api.ApiError;
import com.midrive.learnerapp.api.ApiHelper;
import com.midrive.learnerapp.fragment.BaseDialogFragment;
import com.midrive.learnerapp.fragment.CustomDialogFragment;
import com.midrive.learnerapp.fragment.ProgressDialogFragment;
import com.midrive.learnerapp.helper.RevealButtonHelper;
import com.midrive.learnerapp.view.ErrorTextInputLayout;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.List;

import io.realm.Realm;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by dylanturney on 01/08/2017.
 */

public class BaseActivity extends RxAppCompatActivity implements ApiHelper.ErrorCallback,
        BaseDialogFragment.CustomDialogListener, Validator.ValidationListener {

    protected final String TAG = this.getClass().getSimpleName();

    public static final String ERROR_FRAG_DIALOG = "ERROR_FRAG_DIALOG";
    public static final String SPINNER_DIALOG = "SPINNER_DIALOG";

    protected Realm mRealm;
    protected Handler mHandler = new Handler();

    private ProgressDialogFragment mSpinner;

    protected RevealButtonHelper mRevealHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRealm = Realm.getDefaultInstance();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mRealm != null) {
            mRealm.removeAllChangeListeners();
            mRealm.close();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onError(Throwable e) {
        hideSpinner();

        Toast.makeText(this, R.string.network_error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onErrorMessage(String errorMessage) {
        hideSpinner();

        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onApiError(ApiError apiError) {
        hideSpinner();

        FragmentManager fm = getSupportFragmentManager();
        CustomDialogFragment customDialogFragment = CustomDialogFragment.newInstance(
                0, apiError.getErrorMessage(), 0, android.R.string.ok
        );
        customDialogFragment.setCancelable(false);
        customDialogFragment.show(fm, ERROR_FRAG_DIALOG);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onButtonClick(DialogFragment dialog, View view) {
        if (dialog.getTag() != null && dialog.getTag().equals(ERROR_FRAG_DIALOG)) {
            dialog.dismiss();
        }
    }

    @Override
    public void onDismiss(DialogFragment dialog) {
        if (dialog != null && dialog.isAdded()) {
            dialog.dismiss();
        }
    }

    protected void showSpinner() {
        if (mSpinner == null) {
            mSpinner = ProgressDialogFragment.newInstance();
        } else if (mSpinner.isAdded()) {
            return;
        }

        mSpinner.show(getSupportFragmentManager(), SPINNER_DIALOG);
    }

    protected void hideSpinner() {
        if (mSpinner != null) {
            mSpinner.dismiss();
        }
    }

    @Override
    public void onValidationSucceeded() {
        if (mRevealHelper != null) {
            mRevealHelper.showBlueBackground(true);
        }
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        if (mRevealHelper == null) {
            return;
        }

        mRevealHelper.showBlueBackground(false);

        for (final ValidationError error : errors) {
            final ViewParent viewParent = error.getView().getParentForAccessibility();

            if (viewParent instanceof ErrorTextInputLayout) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ((ErrorTextInputLayout) viewParent)
                                .onError(error.getFailedRules().get(0).getMessage(
                                        BaseActivity.this));
                    }
                });
            }
        }
    }
}