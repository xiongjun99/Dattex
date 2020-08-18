package com.temp.dattex.order.fragment;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.common.framework.basic.BaseViewModel;
import com.independ.framework.response.ResponseTransformer;
import com.temp.dattex.adapter.CurrentRecyclerAdapter;
import com.temp.dattex.bean.OrdersBean;
import com.temp.dattex.net.DataService;

import java.util.List;

/**
 * @Package: com.temp.dattex.order.fragment
 * @ClassName: CurrentOrderViewModel
 * @Description: java类作用描述
 * @Author: 李嘉伦
 * @CreateDate: 2020/5/17 16:30
 * @Email: 86152
 */

public class CurrentOrderViewModel extends BaseViewModel {
    private int page;
    public ObservableField<CurrentRecyclerAdapter> rcyAdapter = new ObservableField<>();


    public CurrentOrderViewModel(@NonNull Application application) {
        super(application);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onCreate() {
        super.onCreate();
        DataService.getInstance().getAllOrders(page,"").compose(ResponseTransformer.<OrdersBean>handleResult()).subscribe(
                o -> {
                    List<OrdersBean.OrderItemBean> rows = o.getRows();
                    if (null != rows) {
                        rcyAdapter.get().addData(rows);
                    }
                }, t -> {
                    int a = 4;
                }
        );
    }
}
