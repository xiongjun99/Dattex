package com.temp.dattex.withdraworwallet;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import com.common.framework.basic.BaseActivity;
import com.temp.dattex.BR;
import com.temp.dattex.Constants;
import com.temp.dattex.R;
import com.temp.dattex.databinding.ActivityWithWalletBinding;

public class WithdrawOrWalletActivity extends BaseActivity<ActivityWithWalletBinding, WithDrawWalletViewModel> {

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_with_wallet;
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
        return BR.withdrawWalletViewModel;
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
