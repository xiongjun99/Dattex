package com.temp.dattex.auth;

import android.os.Bundle;

import com.common.framework.basic.BaseActivity;
import com.temp.dattex.BR;
import com.temp.dattex.R;
import com.temp.dattex.databinding.ActivityAuthResultBinding;

public class AuthResultActivity extends BaseActivity<ActivityAuthResultBinding, AuthResultViewModel> {


    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_auth_result;
    }

    @Override
    public int initVariableId() {
        return BR.authResultViewModel;
    }

    @Override
    public void initViewObservable() {

    }
}
