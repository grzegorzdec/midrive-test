package com.midrive.learnerapp.view;

import android.content.Context;
import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dylanturney on 01/08/2017.
 */

public class ErrorTextInputLayout extends TextInputLayout
        implements TextWatcher, View.OnFocusChangeListener {

    private static int ERROR_DISPLAY_LENGTH = 3000;

    private Handler mHandler = new Handler();

    private EditText mEditText;
    private String mOriginalHint;
    private List<ErrorTextInputListener> mListeners = new ArrayList<>();

    private boolean mHasBeenTouched = false;
    private boolean mHasLostFocus = false;

    public ErrorTextInputLayout(Context context) {
        this(context, null);
    }

    public ErrorTextInputLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ErrorTextInputLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        handleEditText(getEditText());
    }

    public void addListener(ErrorTextInputListener errorTextInputListener) {
        mListeners.add(errorTextInputListener);
    }

    public void removeListener(ErrorTextInputListener errorTextInputListener) {
        mListeners.remove(errorTextInputListener);
    }

    private void handleEditText(EditText editText) {
        mEditText = editText;

        if (mEditText != null) {
            mEditText.addTextChangedListener(this);
            mEditText.setOnFocusChangeListener(this);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        notifyListeners();
    }

    private void notifyListeners() {
        for (ErrorTextInputListener listener : mListeners) {
            listener.requiresValidation(this, mEditText);
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (!hasFocus) {
            mHasLostFocus = true;
            notifyListeners();
        } else {
            mHasBeenTouched = true;
        }
    }

    public void onError(String error) {
        if (TextUtils.isEmpty(mOriginalHint)) {
            mOriginalHint = getHint().toString();
        }

        if ((getEditText() != null && getEditText().getText().toString().isEmpty())) {
            returnViewFromError();
            return;
        }

        if (mHasBeenTouched && mHasLostFocus) {
            mHandler.removeCallbacksAndMessages(null);
            setHint(error);
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    returnViewFromError();
                }
            }, ERROR_DISPLAY_LENGTH);
        }
    }

    public void returnViewFromError() {
        setHint(mOriginalHint);
    }

    public interface ErrorTextInputListener {
        void requiresValidation(TextInputLayout textInputLayout, EditText editText);
    }
}