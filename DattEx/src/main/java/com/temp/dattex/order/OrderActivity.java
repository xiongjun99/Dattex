package com.temp.dattex.order;

import android.content.Intent;
import android.os.Bundle;

import com.common.framework.basic.BaseActivity;
import com.temp.dattex.BR;
import com.temp.dattex.Constants;
import com.temp.dattex.R;
import com.temp.dattex.adapter.OrderFragmentAdapter;
import com.temp.dattex.database.LoginInfo;
import com.temp.dattex.databinding.ActivityOrderBinding;
import com.temp.dattex.login.LoginActivity;


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
    public boolean needLogin() {
        return true;
    }
}
