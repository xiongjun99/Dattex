package com.temp.dattex.util;

import android.app.Dialog;

import androidx.databinding.ObservableField;

import com.common.framework.click.SingleClick;

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
public class CoinRecordFilerViewModel {

    public enum Filters {
        ALL(0), IN(1), OUT(2), WITHDRAW(3), RECHARGE(4);
        private int value;

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
    }

    @SingleClick
    public void setFilterType(Filters filter) {
        getFilter().set(filter);
    }
}
