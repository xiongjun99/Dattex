package com.temp.dattex.widget;

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

import com.temp.dattex.R;

/**
 * @Package: com.temp.dattex.widget
 * @ClassName: LeftDrawbleView
 * @Description: java类作用描述
 * @Author: 李嘉伦
 * @CreateDate: 2020/5/16 23:43
 * @Email: 86152
 */

public class LeftDrawableView extends RelativeLayout {

    private ImageView view_left;
    private TextView tvContent;
    private RelativeLayout layout;

    public LeftDrawableView(Context context) {
        super(context);
    }

    public LeftDrawableView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }


    public LeftDrawableView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }


    public LeftDrawableView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context, attrs);
    }

    @SuppressLint("Recycle")
    private void initView(Context context, AttributeSet attrs) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.view_left_drawalbe_layout, this, true);
        view_left = inflate.findViewById(R.id.iv_left);
        tvContent = inflate.findViewById(R.id.tv_content);
        layout = inflate.findViewById(R.id.layout);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LeftDrawableView);
        setContentText(typedArray.getString(R.styleable.LeftDrawableView_leftContent));
        setIcon(typedArray.getResourceId(R.styleable.LeftDrawableView_leftContentICon, 0));
        setLayoutBackGround(typedArray.getResourceId(R.styleable.LeftDrawableView_leftBackground, 0));
        setContentTextColor(typedArray.getColor(R.styleable.LeftDrawableView_leftContentColor, Color.BLACK));
    }

    private void setContentTextColor(int color) {
        tvContent.setTextColor(color);
    }

    private void setLayoutBackGround(int resourceId) {
        layout.setBackgroundResource(resourceId);
    }

    private void setIcon(int resourceId) {
        view_left.setImageResource(resourceId);
    }

    private void setContentText(String string) {
        tvContent.setText(string);
    }

}
