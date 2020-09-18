package com.temp.buda.withdraworwallet;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.databinding.Observable;

import com.common.framework.basic.BaseActivity;
import com.temp.buda.BR;
import com.temp.buda.Constants;
import com.temp.buda.R;
import com.temp.buda.databinding.ActivityWithWalletBinding;

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
        viewModel.getType().addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                viewModel.getData();
            }
        });

    }

    @Override
    public void initView() {
        super.initView();
        View emptyView = LayoutInflater
                .from(this)
                .inflate(R.layout.order_empty_layout, null);
        TextView tvEmptyName = (TextView)emptyView.findViewById(R.id.tv_empty_name);
        tvEmptyName.setText("暂无记录");
        viewModel.adapter.setUseEmpty(true);
        viewModel.adapter.setEmptyView(emptyView);
    }
}
