package com.temp.buda.splash;

import android.os.Bundle;

import com.common.framework.basic.BaseActivity;
import com.temp.buda.BR;
import com.temp.buda.R;
import com.temp.buda.config.AssetsConfigs;
import com.temp.buda.databinding.ActivitySplashBinding;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.temp.dattex.splash
 * @FileName     : SplashActivity.java
 * @Author       : chao
 * @Date         : 2020/5/20
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
public class SplashActivity extends BaseActivity<ActivitySplashBinding, SplashViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_splash;
    }

    @Override
    public int initVariableId() {
        return BR.splashViewModel;
    }

    @Override
    public void initViewObservable() {
    }

    @Override
    public void initView() {
        super.initView();
        AssetsConfigs.getInstance().freshCoin();
    }
}
