package com.temp.dattex.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import androidx.databinding.DataBindingUtil;
import com.temp.dattex.R;

public class TradeDepthView extends RelativeLayout {

    public TradeDepthView(Context context) {
        super(context);
        initView(context);
    }

    public TradeDepthView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public TradeDepthView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @SuppressLint("NewApi")
    public TradeDepthView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    private void initView(Context context) {
        DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.view_trade_depth, this, true);
    }
}
