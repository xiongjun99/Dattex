package com.temp.dattex.protocol;

import android.os.Bundle;

import com.common.framework.basic.BaseActivity;
import com.temp.dattex.BR;
import com.temp.dattex.R;
import com.temp.dattex.databinding.ActivityProtocolBinding;

public class ProtocolActivity extends BaseActivity<ActivityProtocolBinding, ProtocolViewModel> {

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_protocol;
    }

    @Override
    public int initVariableId() {
        return BR.protocolViewModel;
    }

    @Override
    public void initViewObservable() {

    }
}
