package com.temp.buda.widget.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ViewFlipper;

/**
 * Created by CK on 2019/4/11.
 */
public class HomeViewFlipper extends ViewFlipper {
    public HomeViewFlipper(Context context) {
        super(context);
    }

    public HomeViewFlipper(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDetachedFromWindow() {
        try {
            super.onDetachedFromWindow();
        }
        catch (IllegalArgumentException e) {
            stopFlipping();
        }
    }
}
