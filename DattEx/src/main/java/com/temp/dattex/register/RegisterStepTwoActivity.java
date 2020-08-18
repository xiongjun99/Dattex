package com.temp.dattex.register;

import android.os.Bundle;

import androidx.databinding.ObservableField;
import androidx.databinding.library.baseAdapters.BR;

import com.common.framework.basic.BaseActivity;
import com.temp.dattex.Constants;
import com.temp.dattex.R;
import com.temp.dattex.databinding.ActivityRegisterStepTwoBinding;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.temp.dattex.register
 * @FileName     : RegisterStepTwoActivity.java
 * @Author       : chao
 * @Date         : 2020/5/14
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
public class RegisterStepTwoActivity extends BaseActivity<ActivityRegisterStepTwoBinding, RegisterStepTwoViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_register_step_two;
    }

    @Override
    public void initParam() {
        Bundle extras = getIntent().getExtras();
        if (null != extras) {
            String userName = extras.getString(Constants.KEY_ACCOUNT);
            String phoneCode = extras.getString(Constants.KEY_PHONE_CODE);
            String invitedCode = extras.getString(Constants.KEY_INVITED_CODE);
            String countryCode = extras.getString(Constants.KEY_COUNTRY_CODE);

            viewModel.setUserName(new ObservableField<>(userName));
            viewModel.setPhoneCode(new ObservableField<>(phoneCode));
            viewModel.setInviteCode(new ObservableField<>(invitedCode));
            viewModel.setCountryCode(new ObservableField<>(countryCode));

        } else {
            finish();
        }
    }

    @Override
    public int initVariableId() {
        return BR.registerStepTwoModel;
    }

    @Override
    public void initViewObservable() {

    }
}
