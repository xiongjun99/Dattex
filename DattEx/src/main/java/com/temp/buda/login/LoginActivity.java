package com.temp.buda.login;

import android.os.Bundle;

import com.common.framework.basic.BaseActivity;
import com.temp.buda.BR;
import com.temp.buda.Constants;
import com.temp.buda.R;
import com.temp.buda.databinding.ActivityLoginBinding;

public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> {

    private Class<?> clazz;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_login;
    }

    @Override
    public int initVariableId() {
        return BR.loginViewModel;
    }


    @Override
    public void initViewObservable() {

    }

    @Override
    public void initParam() {
        super.initParam();
        String className = getIntent().getStringExtra(Constants.KEY_CLASS_NAME);
        viewModel.setNextPage(className);

    }
}
