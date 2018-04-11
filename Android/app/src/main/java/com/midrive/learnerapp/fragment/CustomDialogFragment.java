package com.midrive.learnerapp.fragment;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.midrive.learnerapp.R;
import com.midrive.learnerapp.databinding.FragmentCustomDialogBinding;

/**
 * Created by dylanturney on 01/08/2017.
 */

public class CustomDialogFragment extends BaseDialogFragment implements View.OnClickListener {

    private FragmentCustomDialogBinding mBinding;

    private final static String KEY_TITLE = "TITLE";
    private final static String KEY_COPY = "COPY";
    private final static String KEY_STRING_COPY = "STRING_COPY";
    private final static String KEY_IMAGE = "IMAGE";
    private final static String KEY_BUTTON = "BUTTON";
    private final static String KEY_SECONDARY_BUTTON = "SECONDARY_BUTTON";

    public static CustomDialogFragment newInstance(@StringRes int title, String copy,
                                                   @DrawableRes int image, @StringRes int button) {
        CustomDialogFragment frag = new CustomDialogFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_TITLE, title);
        args.putString(KEY_STRING_COPY, copy);
        args.putInt(KEY_IMAGE, image);
        args.putInt(KEY_BUTTON, button);
        frag.setArguments(args);
        return frag;
    }

    public static CustomDialogFragment newInstance(@StringRes int title, @StringRes int copy,
                                                   @StringRes int button) {

        return newInstance(title, copy, 0, button, 0);
    }

    public static CustomDialogFragment newInstance(@StringRes int title, @StringRes int copy,
                                                   @DrawableRes int image, @StringRes int button) {

        return newInstance(title, copy, image, button, 0);
    }

    public static CustomDialogFragment newInstance(@StringRes int title, @StringRes int copy,
                                                   @DrawableRes int image, @StringRes int button,
                                                   @StringRes int secondaryButton) {
        CustomDialogFragment frag = new CustomDialogFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_TITLE, title);
        args.putInt(KEY_COPY, copy);
        args.putInt(KEY_IMAGE, image);
        args.putInt(KEY_BUTTON, button);
        args.putInt(KEY_SECONDARY_BUTTON, secondaryButton);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_custom_dialog, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Fetch arguments from bundle and set title
        @StringRes int titleRes = getArguments().getInt(KEY_TITLE, 0);
        @StringRes int copyRes = getArguments().getInt(KEY_COPY, 0);
        String copy = getArguments().getString(KEY_STRING_COPY, "");
        @DrawableRes int imageRes = getArguments().getInt(KEY_IMAGE, 0);
        @StringRes int buttonRes = getArguments().getInt(KEY_BUTTON, 0);
        @StringRes int secondaryButtonRes = getArguments().getInt(KEY_SECONDARY_BUTTON, 0);

        if (titleRes != 0) {
            mBinding.tvTitle.setText(titleRes);
        } else {
            mBinding.tvTitle.setVisibility(View.GONE);
        }

        if (copyRes != 0) {
            mBinding.tvCopy.setText(copyRes);
        } else {
            mBinding.tvCopy.setText(copy);
        }

        if (imageRes != 0) {
            mBinding.ivImage.setImageResource(imageRes);
        } else {
            mBinding.ivImage.setVisibility(View.GONE);
        }

        if (buttonRes != 0) {
            mBinding.bButton.setText(buttonRes);
        }

        if (secondaryButtonRes != 0) {
            mBinding.bSecondaryButton.setText(secondaryButtonRes);
            mBinding.bSecondaryButton.setVisibility(View.VISIBLE);
            mBinding.bSecondaryButton.setOnClickListener(this);
        }

        mBinding.bButton.setOnClickListener(this);

        if (this.isCancelable()) {
            mBinding.bCancel.setOnClickListener(this);
        } else {
            mBinding.bCancel.setVisibility(View.GONE);
        }
    }

}