package com.temp.dattex.withdraw;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.temp.dattex.R;
import com.temp.dattex.widget.TitleBar;

//新增提币地址
public class NewWithdrawActivity extends Activity {
    private TitleBar titleBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newwithdraw);
        initView();
    }

    private void initView() {
        titleBar = (TitleBar) findViewById(R.id.title_bar);
        titleBar.setLeftTwoClick(this);
    }
}
