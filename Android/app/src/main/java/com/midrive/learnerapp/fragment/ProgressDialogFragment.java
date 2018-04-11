package com.midrive.learnerapp.fragment;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.midrive.learnerapp.R;
import com.midrive.learnerapp.databinding.FragmentProgressDialogBinding;

/**
 * Created by dylanturney on 01/08/2017.
 */

public class ProgressDialogFragment extends DialogFragment {

    private FragmentProgressDialogBinding mBinding;

    public static ProgressDialogFragment newInstance() {
        ProgressDialogFragment frag = new ProgressDialogFragment();
        frag.setCancelable(false);
        return frag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setStyle(STYLE_NO_FRAME, R.style.ProgressDialogTheme);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        mBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_progress_dialog, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View v, Bundle onSavedInstanceState) {
        super.onViewCreated(v, onSavedInstanceState);

        mBinding.ivSpinner.setBackgroundResource(R.drawable.spinner);

        // Get the background, which has been compiled to an AnimationDrawable object.
        AnimationDrawable frameAnimation = (AnimationDrawable) mBinding.ivSpinner.getBackground();

        // Start the animation (looped playback by default).
        frameAnimation.start();
    }
}