package com.temp.dattex.order.fragment;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.common.framework.basic.BaseViewModel;
import com.independ.framework.response.ResponseTransformer;
import com.temp.dattex.bean.OrdersBean;
import com.temp.dattex.net.DataService;

import java.util.List;

/**
 * @Package: com.temp.dattex.order.fragment
 * @ClassName: HistoryOrderViewModel
 * @Description: java类作用描述
 * @Author: 李嘉伦
 * @CreateDate: 2020/5/18 21:24
 * @Email: 86152
 */
public class HistoryOrderViewModel extends BaseViewModel {
    private int page;
    public ObservableField<BaseQuickAdapter<OrdersBean.OrderItemBean, BaseViewHolder>> rcyAdapter = new ObservableField<>();

    public HistoryOrderViewModel(@NonNull Application application) {
        super(application);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onCreate() {
        super.onCreate();
        DataService.getInstance().getAllOrders(2,page, "").compose(ResponseTransformer.<OrdersBean>handleResult()).subscribe(
                o -> {
                    List<OrdersBean.OrderItemBean> rows = o.getRows();
                    if (null != rows) {
                        rcyAdapter.get().addData(rows);
                        page++;
                    }
                }, t -> {

                }
        );
    }
}
