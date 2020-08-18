package com.temp.dattex.util;

import android.annotation.SuppressLint;
import android.app.Application;
import android.app.Dialog;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.common.framework.basic.BaseApplication;
import com.common.framework.basic.BaseViewModel;
import com.common.framework.click.SingleClick;
import com.exchange.utilslib.ToastUtil;
import com.independ.framework.response.ResponseTransformer;
import com.temp.dattex.net.DataService;

/**
 * @Package: com.temp.dattex.util
 * @ClassName: CoverDialogViewModel
 * @Description: java类作用描述
 * @Author: 李嘉伦
 * @CreateDate: 2020/5/18 23:29
 * @Email: 86152
 */
public class CoverDialogViewModel extends BaseViewModel {
    ObservableField<Dialog> dialog = new ObservableField<>();
    private long orderId;

    public CoverDialogViewModel(@NonNull Application application) {
        super(application);
    }

    @SingleClick
    public void closeDialog() {
        if (dialog.get() != null) {
            dialog.get().dismiss();
            dialog.set(null);
        }
    }

    /**
     * 平仓确认
     */
    @SuppressLint("CheckResult")
    @SingleClick
    public void coverConfirm() {
        DataService.getInstance().placePosition(orderId).compose(ResponseTransformer.handleResult()).subscribe(
                b -> closeDialog(), t -> ToastUtil.show(BaseApplication.getInstance(), t.getMessage())


        );
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
}
