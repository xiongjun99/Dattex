package com.temp.dattex.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;

<<<<<<< HEAD:DattEx/src/main/java/com/temp/dattex/widget/SettingItemView.java
import com.temp.dattex.R;
import com.temp.dattex.databinding.ViewSettingItemBinding;
=======
import com.temp.buda.R;
import com.temp.buda.databinding    .ViewSettingItemBinding;
>>>>>>> master:DattEx/src/main/java/com/temp/buda/widget/SettingItemView.java

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.temp.dattex.widget
 * @FileName     : SettingItemView.java
 * @Author       : chao
 * @Date         : 2020/6/20
 * @Email        : icechliu@gmail.com
 * @version      : V1
 *
 *                    .::::.
 *                  .::::::::.
 *                 :::::::::::  
 *             ..:::::::::::'
 *           '::::::::::::'
 *             .::::::::::
 *        '::::::::::::::..
 *             ..::::::::::::.
 *           ``::::::::::::::::
 *            ::::``:::::::::'        .:::.
 *           ::::'   ':::::'       .::::::::.
 *         .::::'      ::::     .:::::::'::::.
 *        .:::'       :::::  .:::::::::' ':::::.
 *       .::'        :::::.:::::::::'      ':::::.
 *      .::'         ::::::::::::::'         ``::::.
 *  ...:::           ::::::::::::'              ``::.
 * ```` ':.          ':::::::::'                  ::::..
 *                    '.:::::'                    ':'````..
 *************************************************************************/
public class SettingItemView extends FrameLayout {

    private ViewSettingItemBinding binding;

    public SettingItemView(@NonNull Context context) {
        super(context);

    }

    public SettingItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }


    public SettingItemView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SettingItemView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initView(Context context, AttributeSet attrs) {
//        View inflate = LayoutInflater.from(context).inflate(R.layout.view_setting_item, this, true);
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.view_setting_item, this, true);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SettingItemView);
        binding.textViewRightText.setText(typedArray.getString(R.styleable.SettingItemView_rightText));
        binding.textViewLeftText.setText(typedArray.getString(R.styleable.SettingItemView_leftText));
        typedArray.recycle();
    }


}
