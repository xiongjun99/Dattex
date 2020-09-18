package com.temp.buda.util;

import android.app.Dialog;

import androidx.databinding.ObservableField;

import com.common.framework.click.SingleClick;

public class PlaceAnOrderDialogModel {

    private ObservableField<String> upStopPrice = new ObservableField("");
    private ObservableField<String> downStopPrice = new ObservableField("");
    private ObservableField<String> upStopPercent = new ObservableField("100");
    private ObservableField<String> downStopPercent = new ObservableField("100");
    private Dialog dialog;
    public Dialog getDialog() {
        return dialog;
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    public ObservableField<String> getUpStopPrice() {
        return upStopPrice;
    }

    public void setUpStopPrice(ObservableField<String> upStopPrice) {
        this.upStopPrice = upStopPrice;
    }

    public ObservableField<String> getDownStopPrice() {
        return downStopPrice;
    }

    public void setDownStopPrice(ObservableField<String> downStopPrice) {
        this.downStopPrice = downStopPrice;
    }

    public ObservableField<String> getUpStopPercent() {
        return upStopPercent;
    }

    public void setUpStopPercent(ObservableField<String> upStopPercent) {
        this.upStopPercent = upStopPercent;
    }

    public ObservableField<String> getDownStopPercent() {
        return downStopPercent;
    }

    public void setDownStopPercent(ObservableField<String> downStopPercent) {
        this.downStopPercent = downStopPercent;
    }

    @SingleClick
    public void closeDialog() {
        if (null != getDialog()) {
            getDialog().dismiss();
            setDialog(null);
        }
    }

    @SingleClick
    public void ensureOrder() {
        if (null != onEnsureListener) {
            float stopLossRate = Float.parseFloat(downStopPercent.get()) / 100;
            float stopProfitRate = Float.parseFloat(upStopPercent.get()) / 100;
            onEnsureListener.onEnsure(stopProfitRate, stopLossRate);
            if (null != getDialog()) {
                getDialog().dismiss();
                setDialog(null);
            }
            onEnsureListener = null;
        }
    }

    @SingleClick
    public void downStopPercentPlus() {
        String text = downStopPercent.get();
        int i = Integer.parseInt(text) + 10;
        if (i < 10) {
            i = 10;
        } else if (i > 100) {
            i = 100;
        }
        downStopPrice.set(String.valueOf((100 - i) / 100f * price));
        downStopPercent.set(String.valueOf(i));
    }

    @SingleClick
    public void downStopPercentSub() {
        String text = downStopPercent.get();
        int i = Integer.parseInt(text) - 10;
        if (i < 10) {
            i = 10;
        } else if (i > 100) {
            i = 100;
        }
        downStopPrice.set(String.valueOf((100 - i) / 100f * price));
        downStopPercent.set(String.valueOf(i));
    }

    @SingleClick
    public void upStopPercentPlus() {
        String text = upStopPercent.get();
        int i = Integer.parseInt(text) + 10;
        if (i < 10) {
            i = 10;
        } else if (i > 100) {
            i = 100;
        }
        upStopPrice.set(String.valueOf((100 + i) / 100f * price));
        upStopPercent.set(String.valueOf(i));
    }

    @SingleClick
    public void upStopPercentSub() {
        String text = upStopPercent.get();
        int i = Integer.parseInt(text) - 10;
        if (i < 10) {
            i = 10;
        } else if (i > 100) {
            i = 100;
        }
        upStopPrice.set(String.valueOf((100 + i) / 100f * price));
        upStopPercent.set(String.valueOf(i));
    }

    private OnEnsureListener onEnsureListener;

    public void setOnEnsureListener(OnEnsureListener onEnsureListener) {
        this.onEnsureListener = onEnsureListener;
    }

    public void setPrice(String s) {
        upStopPrice.set(s);
        downStopPrice.set(s);
        this.price = Float.parseFloat(s);
    }

    private float price;

    public interface OnEnsureListener {
        void onEnsure(float stopProfitRate, float stopLossRate);
    }
}
