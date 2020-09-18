package com.temp.dattex.order;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.common.framework.basic.BaseViewModel;
import com.temp.dattex.adapter.OrderFragmentAdapter;

/**
 * @Package: com.temp.dattex.order
 * @ClassName: OrderViewModel
 * @Description: java类作用描述
 * @Author: 李嘉伦
 * @CreateDate: 2020/5/17 15:23
 * @Email: 86152
 */

public class OrderViewModel extends BaseViewModel {



    public ObservableField<OrderFragmentAdapter> pagerAdapter = new ObservableField<>();
    public ObservableField<Boolean> touch = new ObservableField<>(true);

    public OrderViewModel(@NonNull Application application) {
        super(application);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onCreate() {
        super.onCreate();

    }
}
