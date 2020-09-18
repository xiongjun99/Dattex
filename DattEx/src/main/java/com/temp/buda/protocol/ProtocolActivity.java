package com.temp.buda.protocol;

import android.os.Bundle;

import com.common.framework.basic.BaseActivity;
import com.temp.buda.BR;
import com.temp.buda.R;
import com.temp.buda.databinding.ActivityProtocolBinding;

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
