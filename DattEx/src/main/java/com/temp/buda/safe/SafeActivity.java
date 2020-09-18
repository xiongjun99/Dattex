package com.temp.buda.safe;

import android.os.Bundle;

import com.common.framework.basic.BaseActivity;
import com.temp.buda.BR;
import com.temp.buda.R;
import com.temp.buda.databinding.ActivitySafeBinding;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.temp.dattex.safe
 * @FileName     : SafeActivity.java
 * @Author       : chao
 * @Date         : 2020/6/20
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
public class SafeActivity extends BaseActivity<ActivitySafeBinding, SafeViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_safe;
    }

    @Override
    public int initVariableId() {
        return BR.safeViewModel;
    }

    @Override
    public void initViewObservable() {

    }
}
