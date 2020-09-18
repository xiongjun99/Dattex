package com.temp.buda.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.temp.buda.R;

/**
 * @Package: com.temp.dattex.widget
 * @ClassName: RightView
 * @Description: java类作用描述
 * @Author: 李嘉伦
 * @CreateDate: 2020/5/16 19:57
 * @Email: 86152
 */

public class RightView extends RelativeLayout {

    private TextView tvOrder;
    private ImageView ivRight;
    private ImageView ivOrder;
    private View line;

    public RightView(Context context) {
        super(context);
    }

    public RightView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }


    public RightView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    public RightView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    @SuppressLint({"Recycle", "CustomViewStyleable"})
    private void init(Context context, AttributeSet attrs) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.view_right_layout, this, true);
        ivRight = inflate.findViewById(R.id.iv_right);
        ivOrder = inflate.findViewById(R.id.iv_order);
        tvOrder = inflate.findViewById(R.id.tv_order);
        line = inflate.findViewById(R.id.line);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RightViewArr);
        setTitleText(typedArray.getString(R.styleable.RightViewArr_titleContent));
//        setTitleText(context.getResources().getString(typedArray.getResourceId(R.styleable.RightViewArr_titleContent, R.string.text_null)));
        setArrowIcon(typedArray.getResourceId(R.styleable.RightViewArr_arrowIcon, 0));
        setContentIcon(typedArray.getResourceId(R.styleable.RightViewArr_contentIcon, 0));
        setTextColor(typedArray.getColor(R.styleable.RightViewArr_contentColor, Color.BLACK));
        setLineVisible(typedArray.getBoolean(R.styleable.RightViewArr_lineVisible, false));
    }

    private void setLineVisible(boolean visible) {
        line.setVisibility(visible ? VISIBLE : GONE);
    }

    private void setTextColor(int color) {
        tvOrder.setTextColor(color);
    }

    private void setContentIcon(int resourceId) {
        ivOrder.setImageResource(resourceId);
    }

    private void setArrowIcon(int resourceId) {
        ivRight.setImageResource(resourceId);
    }

    private void setTitleText(String string) {
        tvOrder.setText(string);
    }


}
