package com.temp.buda.resetpwd;

import android.os.Bundle;

import com.common.framework.basic.BaseActivity;
import com.temp.buda.BR;
import com.temp.buda.R;
import com.temp.buda.databinding.ActivityResetPasswordBinding;

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
