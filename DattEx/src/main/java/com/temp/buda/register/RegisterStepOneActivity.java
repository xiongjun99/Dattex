package com.temp.buda.register;

import android.os.Bundle;

import com.common.framework.basic.BaseActivity;
import com.temp.buda.BR;
import com.temp.buda.R;
import com.temp.buda.databinding.ActivityRegisterStepOneBinding;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.temp.dattex
 * @FileName     : RegisteActivity.java
 * @Author       : chao
 * @Date         : 2020/5/13
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

public class RegisterStepOneActivity extends BaseActivity<ActivityRegisterStepOneBinding, RegisterStepOneViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_register_step_one;
    }

    @Override
    public int initVariableId() {
        return BR.registerStepOneViewModel;
    }

    @Override
    public void initViewObservable() {

    }
}
