package com.temp.dattex.kline;

import android.os.Bundle;

import androidx.databinding.library.baseAdapters.BR;

import com.common.framework.basic.BaseActivity;
import com.temp.dattex.R;
import com.temp.dattex.databinding.ActivityKlineBinding;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.temp.dattex.kline
 * @FileName     : KlineActivity.java
 * @Author       : chao
 * @Date         : 2020/5/15
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
public class KlineActivity extends BaseActivity<ActivityKlineBinding, KlineViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_kline;
    }

    @Override
    public int initVariableId() {
        return BR.klineViewModel;
    }

    @Override
    public void initViewObservable() {
//        binding.recyclerViewKlineBottomList
    }

    @Override
    public void initView() {
        super.initView();
        int width = getWindowManager().getDefaultDisplay().getWidth();
        binding.klineChartView.setOverScrollRange(width / 5 + 1);

    }
}
