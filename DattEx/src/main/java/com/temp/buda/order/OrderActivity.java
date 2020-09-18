package com.temp.buda.order;

import android.content.Intent;
import android.os.Bundle;

import com.common.framework.basic.BaseActivity;
import com.temp.buda.BR;
import com.temp.buda.Constants;
import com.temp.buda.R;
import com.temp.buda.adapter.OrderFragmentAdapter;
import com.temp.buda.database.LoginInfo;
import com.temp.buda.databinding.ActivityOrderBinding;
import com.temp.buda.login.LoginActivity;


public class OrderActivity extends BaseActivity<ActivityOrderBinding, OrderViewModel> {


    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_order;
    }

    @Override
    public int initVariableId() {
        return BR.orderViewModel;
    }

    @Override
    public void initViewObservable() {
        if (needLogin() && !LoginInfo.isSign()) {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.putExtra(Constants.KEY_CLASS_NAME, getClass().getName());
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void initParam() {
        super.initParam();
        OrderFragmentAdapter adapter = new OrderFragmentAdapter(getSupportFragmentManager());
        viewModel.pagerAdapter.set(adapter);
        binding.tb.setupWithViewPager(binding.pager);
    }

    @Override
    public void initView() {
        super.initView();
    }

    @Override
    public boolean needLogin() {
        return true;
    }
}
