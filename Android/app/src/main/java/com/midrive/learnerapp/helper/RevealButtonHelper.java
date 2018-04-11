package com.midrive.learnerapp.helper;

import android.animation.Animator;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;

import io.codetail.animation.ViewAnimationUtils;

/**
 * Created by dylanturney on 01/08/2017.
 */

public class RevealButtonHelper {

    public static final String TAG = RevealButtonHelper.class.getSimpleName();

    private Animator mAnimator;
    private AnimationState mAnimationState = AnimationState.disabled;

    private FrameLayout flReveal;
    private Button bContinue;

    public RevealButtonHelper(FrameLayout flReveal, Button bContinue) {
        this.flReveal = flReveal;
        this.bContinue = bContinue;
    }

    public void showBlueBackground(final boolean show) {

        if ((mAnimationState == AnimationState.enabled
                || mAnimationState == AnimationState.animatingEnabled) && show) {
            return;
        }

        if ((mAnimationState == AnimationState.disabled
                || mAnimationState == AnimationState.animatingDisabled) && !show) {
            return;
        }

        if (!ViewCompat.isAttachedToWindow(flReveal)) {
            return;
        }

        int cx = (flReveal.getLeft() + flReveal.getRight()) / 2;
        int cy = (flReveal.getTop() + flReveal.getBottom()) / 2;

        if (!show) {
            // get the final radius for the clipping circle
            int finalRadius = Math.max(flReveal.getWidth(), flReveal.getHeight());
            mAnimator = ViewAnimationUtils.createCircularReveal(flReveal, cx, cy, finalRadius, 0);
        } else {
            // get the final radius for the clipping circle
            int dx = Math.max(cx, flReveal.getWidth() - cx);
            int dy = Math.max(cy, flReveal.getHeight() - cy);
            float finalRadius = (float) Math.hypot(dx, dy);
            mAnimator = ViewAnimationUtils.createCircularReveal(flReveal, cx, cy, 0, finalRadius);
        }

        mAnimator.removeAllListeners();
        mAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        mAnimator.setDuration(200);
        mAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                if (show) {
                    flReveal.setVisibility(View.VISIBLE);
                }

                mAnimationState = show ?
                        AnimationState.animatingEnabled : AnimationState.animatingDisabled;
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                if (!show) {
                    flReveal.setVisibility(View.INVISIBLE);
                }

                mAnimationState = show ?
                        AnimationState.enabled : AnimationState.disabled;
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        mAnimator.start();

        bContinue.setEnabled(show);
    }

    public enum AnimationState {
        enabled, animatingEnabled, disabled, animatingDisabled
    }
}