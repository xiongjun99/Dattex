package com.temp.dattex.util;

import android.app.Dialog;

import com.common.framework.click.SingleClick;
import com.temp.dattex.bean.SymbolConfigBean;
import com.temp.dattex.config.SymbolConfigs;

import java.util.List;

public class SwitchSymbolDialogViewModel {

    private final List<SymbolConfigBean> symbols;

    public SwitchSymbolDialogViewModel() {
        symbols = SymbolConfigs.getInstance().getSymbols();
    }

    private Dialog dialog;

    public Dialog getDialog() {
        return dialog;
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    public int getSymbolSize() {
        if (null != symbols) {
            return symbols.size();
        } else {
            return 0;
        }

    }

    @SingleClick
    public void closeDialog() {
        if (null != getDialog()) {
            getDialog().dismiss();
            setDialog(null);
        }
    }

    public SymbolConfigBean getSymbolBean(int position) {
        return symbols.get(position);
    }

    public void setSymbol(String coinSymbol, String baseSymbol) {
        if (null != onSymbolSet) {
            onSymbolSet.onSymbolSet(coinSymbol, baseSymbol);
        }
        if (null != getDialog()) {
            getDialog().dismiss();
            setDialog(null);
        }
        onSymbolSet = null;
    }

    private OnSymbolSet onSymbolSet;

    public void setOnSymbolSet(OnSymbolSet onSymbolSet) {
        this.onSymbolSet = onSymbolSet;
    }

    public interface OnSymbolSet {
        void onSymbolSet(String coinSymbol, String baseSymbol);
    }
}
