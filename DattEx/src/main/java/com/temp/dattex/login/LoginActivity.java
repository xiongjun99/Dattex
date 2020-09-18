package com.temp.dattex.login;

import android.os.Bundle;

import com.common.framework.basic.BaseActivity;
import com.temp.dattex.BR;
import com.temp.dattex.Constants;
import com.temp.dattex.R;
import com.temp.dattex.databinding.ActivityLoginBinding;

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
