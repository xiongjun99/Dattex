package com.temp.dattex.resetpwd;

import android.os.Bundle;

import com.common.framework.basic.BaseActivity;
import com.temp.dattex.BR;
import com.temp.dattex.R;
import com.temp.dattex.databinding.ActivityResetPasswordBinding;

public class ResetPasswordActivity extends BaseActivity<ActivityResetPasswordBinding, ResetPasswordViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_reset_password;
    }

    @Override
    public int initVariableId() {
        return BR.resetPasswordViewModel;
    }

    @Override
    public void initViewObservable() {

    }
}
