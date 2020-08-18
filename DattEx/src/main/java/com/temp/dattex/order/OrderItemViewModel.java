package com.temp.dattex.order;

import android.app.Application;

import androidx.annotation.NonNull;

import com.common.framework.basic.AppManager;
import com.common.framework.basic.BaseViewModel;
import com.common.framework.click.SingleClick;
import com.temp.dattex.util.CoverDialogViewModel;
import com.temp.dattex.util.DialogUtil;

/**
 * @Package: com.temp.dattex.order
 * @ClassName: OrderItemViewModel
 * @Description: java类作用描述
 * @Author: 李嘉伦
 * @CreateDate: 2020/5/18 21:47
 * @Email: 86152
 */
public class OrderItemViewModel extends BaseViewModel {

    public OrderItemViewModel(@NonNull Application application) {
        super(application);
    }

    @SingleClick
    public void cover(long orderId) {
        CoverDialogViewModel viewModel = new CoverDialogViewModel(getApplication());
        viewModel.setOrderId(orderId);
        DialogUtil.showCoverDialog(AppManager.getActivityStack().peek(), viewModel);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
