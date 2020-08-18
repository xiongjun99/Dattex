package com.temp.dattex.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.framework.basic.BaseViewModel;
import com.temp.dattex.R;
import com.temp.dattex.binding.adapter.TitleBarClickBindingAdapter;


public class TitleBar extends RelativeLayout {

    private TextView textViewTitle;
    private ImageView imageViewTitleLeftIcon;
    private ImageView imageViewTitleRightIcon;

    public TitleBar(Context context) {
        super(context);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.view_title_bar, this, true);
        textViewTitle = inflate.findViewById(R.id.text_view_title);
        imageViewTitleLeftIcon = inflate.findViewById(R.id.image_view_title_left_icon);
        imageViewTitleRightIcon = inflate.findViewById(R.id.image_view_title_right_icon);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CommonTitleBar);
        setTitleColor(typedArray.getColor(R.styleable.CommonTitleBar_titleTextColor, Color.BLACK));
        setLeftIcon(typedArray.getResourceId(R.styleable.CommonTitleBar_leftIcon, 0));
        setRightIcon(typedArray.getResourceId(R.styleable.CommonTitleBar_rightIcon, 0));
        setTitleSize(typedArray.getDimension(R.styleable.CommonTitleBar_titleTextSize, 16));
        setTitleText(context.getResources().getString(typedArray.getResourceId(R.styleable.CommonTitleBar_titleText, R.string.text_null)));

    }

    private void setTitleSize(float dimension) {
        textViewTitle.setTextSize(TypedValue.COMPLEX_UNIT_DIP, dimension);
    }

    private void setRightIcon(int resourceId) {
        imageViewTitleRightIcon.setImageResource(resourceId);
    }

    private void setLeftIcon(int resourceId) {
        imageViewTitleLeftIcon.setImageResource(resourceId);
    }

    public void setTitleText(String string) {
        textViewTitle.setText(string);
    }

    private void setTitleColor(int color) {
        textViewTitle.setTextColor(color);
    }

    public void setLeftClick(final BaseViewModel viewModel) {
        imageViewTitleLeftIcon.setOnClickListener(v -> viewModel.finish());
    }
    public void setLeftTwoClick(Activity activity) {
        imageViewTitleLeftIcon.setOnClickListener(view -> activity.finish());
    }
    public void setRightClick(TitleBarClickBindingAdapter.TitleRightClickListener rightClickListener) {
        imageViewTitleRightIcon.setOnClickListener(v -> rightClickListener.rightClick());
    }
}
