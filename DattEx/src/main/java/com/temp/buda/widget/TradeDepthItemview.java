package com.temp.buda.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import androidx.databinding.DataBindingUtil;

import com.temp.buda.R;

public class TradeDepthItemview extends RelativeLayout {
    public TradeDepthItemview(Context context) {
        super(context);
        initView(context);
    }

    public TradeDepthItemview(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public TradeDepthItemview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public TradeDepthItemview(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    private void initView(Context context) {
        DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.view_trade_depth_item,this,true);
    }
}
