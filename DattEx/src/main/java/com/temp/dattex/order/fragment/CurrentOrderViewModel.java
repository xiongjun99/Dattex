package com.temp.dattex.order.fragment;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.recyclerview.widget.DiffUtil;

import com.common.framework.basic.BaseApplication;
import com.common.framework.basic.BaseViewModel;
import com.exchange.utilslib.ToastUtil;
import com.independ.framework.response.ResponseTransformer;
import com.temp.dattex.adapter.CurrentRecyclerAdapter;
import com.temp.dattex.bean.OrdersBean;
import com.temp.dattex.database.LoginInfo;
import com.temp.dattex.net.DataService;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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
    private Timer timer;

    public CurrentOrderViewModel(@NonNull Application application) {
        super(application);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onCreate() {
        super.onCreate();
        freshOrderList();
//        DataService.getInstance().getAllOrders(page,"").compose(ResponseTransformer.<OrdersBean>handleResult()).subscribe(
//                o -> {
//                    List<OrdersBean.OrderItemBean> rows = o.getRows();
//                    if (null != rows) {
//                        rcyAdapter.get().addData(rows);
//                    }
//                }, t -> {
//                    int a = 4;
//                }
//        );
    }

    @Override
    public void onResume() {
        super.onResume();
        if (LoginInfo.isSign()) {
            freshOrderList();
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    freshOrderList();
                }
            }, 2000, 2000);
        }
    }
    @Override
    public void onPause() {
        super.onPause();
        if (null != timer) {
            timer.cancel();
            timer = null;
        }
    }
    @SuppressLint("CheckResult")
    private void freshOrderList() {
        DataService.getInstance().getAllOrders("",1,page, "").compose(ResponseTransformer.<OrdersBean>handleResult()).subscribe(
                bean -> {
                    List<OrdersBean.OrderItemBean> rows = bean.getRows();

                    DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                        @Override
                        public int getOldListSize() {
                            return rcyAdapter.get().getData() == null ? 0 : rcyAdapter.get().getData().size();
                        }

                        @Override
                        public int getNewListSize() {
                            return null == rows ? 0 : rows.size();
                        }

                        @Override
                        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                            return true;
                        }

                        @Override
                        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                            return false;
                        }
                    });
                    rcyAdapter.get().setNewData(rows);
                    diffResult.dispatchUpdatesTo(rcyAdapter.get());
                }, t -> ToastUtil.show(BaseApplication.getInstance(), t.getMessage())
        );
    }
}
