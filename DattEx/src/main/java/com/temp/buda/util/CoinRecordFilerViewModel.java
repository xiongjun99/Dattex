package com.temp.buda.util;

import android.app.Dialog;
import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import com.common.framework.basic.BaseViewModel;
import com.common.framework.click.SingleClick;
import com.temp.buda.Application;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.temp.dattex.util
 * @FileName     : CoinRecordFitlerViewModel.java
 * @Author       : chao
 * @Date         : 2020/5/20
 * @Email        : icechliu@gmail.com
 * @version      : V1
 *
 *                    .::::.
 *                  .::::::::.
 *                 :::::::::::  
 *             ..:::::::::::'
 *           '::::::::::::'
 *             .::::::::::
 *        '::::::::::::::..
 *             ..::::::::::::.
 *           ``::::::::::::::::
 *            ::::``:::::::::'        .:::.
 *           ::::'   ':::::'       .::::::::.
 *         .::::'      ::::     .:::::::'::::.
 *        .:::'       :::::  .:::::::::' ':::::.
 *       .::'        :::::.:::::::::'      ':::::.
 *      .::'         ::::::::::::::'         ``::::.
 *  ...:::           ::::::::::::'              ``::.
 * ```` ':.          ':::::::::'                  ::::..
 *                    '.:::::'                    ':'````..
 *************************************************************************/
public class CoinRecordFilerViewModel extends BaseViewModel {

    public CoinRecordFilerViewModel(@NonNull Application application) {
        super(application);
    }

    public enum Filters {
        ALL(-1), RECHARGE(0), WITHDRAW(1), FEE(2), TRADE(3), INVITE(4), REGISTER(5), RECHARGE_1(6);
        public int value;
        Filters(int value) {
            this.value = value;
        }
    }

    private Dialog dialog;

    private ObservableField<Filters> filter = new ObservableField<>(Filters.ALL);

    public Dialog getDialog() {
        return dialog;
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    public ObservableField<Filters> getFilter() {
        return filter;
    }

    public void setFilter(ObservableField<Filters> filter) {
        this.filter = filter;
    }

    @SingleClick
    public void reset() {
        getFilter().set(Filters.ALL);
    }

    @SingleClick
    public void ensure() {
        if (null != getDialog()) {
            getDialog().dismiss();
            setDialog(null);
        }
        if (null != onEnsureListener) {
            onEnsureListener.onEnsure(filter.get().value);
            onEnsureListener = null;
        }
    }

    @SingleClick
    public void setFilterType(Filters filter) {
        getFilter().set(filter);
    }

    public interface OnEnsureListener {
        void onEnsure(int value);
    }
    private CoinRecordFilerViewModel.OnEnsureListener onEnsureListener;

    public void setOnEnsureListener(CoinRecordFilerViewModel.OnEnsureListener onEnsureListener) {
        this.onEnsureListener = onEnsureListener;
    }
}
