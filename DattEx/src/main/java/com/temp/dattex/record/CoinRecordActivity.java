package com.temp.dattex.record;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;

import com.common.framework.basic.BaseActivity;
import com.exchange.utilslib.ToastUtil;
import com.temp.dattex.BR;
import com.temp.dattex.Constants;
import com.temp.dattex.R;
import com.temp.dattex.databinding.ActivityCoinRecordBinding;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.temp.dattex.record
 * @FileName     : CoinRecordActivity.java
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
public class CoinRecordActivity extends BaseActivity<ActivityCoinRecordBinding, CoinRecordViewModel> {

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_coin_record;
    }

    @Override
    public void initParam() {
        super.initParam();
        String stringExtra = getIntent().getStringExtra(Constants.KEY_COIN_NAME);
        if (!TextUtils.isEmpty(stringExtra)) {
            viewModel.setCoinName(stringExtra);
        } else {
            finish();
        }
    }

    @Override
    public int initVariableId() {
        return BR.coinRecordViewModel;
    }

    @Override
    public void initViewObservable() {

    }

    @Override
    public void initView() {
        super.initView();
        View emptyView = LayoutInflater
                .from(this)
                .inflate(R.layout.order_empty_layout, null);
        viewModel.adapter.setUseEmpty(true);
        viewModel.adapter.setEmptyView(emptyView);
    }
}
