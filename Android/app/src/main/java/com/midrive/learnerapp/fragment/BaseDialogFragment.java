package com.midrive.learnerapp.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.view.View;

import com.midrive.learnerapp.R;

/**
 * Created by dylanturney on 01/08/2017.
 */

public class BaseDialogFragment extends DialogFragment implements View.OnClickListener {


    // Use this instance of the interface to deliver action events
    protected CustomDialogListener mListener;
    protected Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            mContext = context;
            // Instantiate the CustomDialogListener so we can send events to the host
            mListener = (CustomDialogListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement CustomDialogListener");
        }
    }

    @Override
    public void onClick(View v) {
        if (mListener != null) {
            if (v.getId() == R.id.bCancel) {
                mListener.onDismiss(this);
                return;
            }
            mListener.onButtonClick(this, v);
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (mListener != null) {
            mListener.onDismiss(this);
        }
    }

    public interface CustomDialogListener {
        void onButtonClick(DialogFragment dialog, View view);
        void onDismiss(DialogFragment dialog);
    }

}