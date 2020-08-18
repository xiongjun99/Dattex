package com.temp.dattex.binding.adapter;

import android.widget.Button;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

/**
 * Created by 李嘉伦.
 * Date: 2020/5/15
 * Time: 下午 1:42
 */
public class ButtonBinding {

    @BindingAdapter("selected")
    public static void setSelected(Button button, boolean b) {
        button.setSelected(b);
    }

    @BindingAdapter("textSelected")
    public static void setTextSelected(TextView textView,boolean b){
        textView.setSelected(b);
    }
}
