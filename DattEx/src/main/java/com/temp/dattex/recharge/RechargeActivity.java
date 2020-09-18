package com.temp.dattex.recharge;

import android.os.Bundle;

import com.common.framework.basic.BaseActivity;
import com.temp.dattex.BR;
import com.temp.dattex.R;
import com.temp.dattex.databinding.ActivityRechargeBinding;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.temp.dattex.recharge
 * @FileName     : RechargeActivity.java
 * @Author       : chao
 * @Date         : 2020/5/19
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
public class RechargeActivity extends BaseActivity<ActivityRechargeBinding, RechargeViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_recharge;
    }

    @Override
    public int initVariableId() {
        return BR.rechargeViewModel;
    }

    @Override
    public void initViewObservable() {

    }
}
