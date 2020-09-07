package com.temp.dattex.util;

import android.app.Dialog;
import android.os.Handler;
import android.text.TextUtils;

import com.common.framework.basic.BaseApplication;
import com.common.framework.click.SingleClick;
import com.exchange.utilslib.LooperUtil;
import com.exchange.utilslib.SPUtil;
import com.exchange.utilslib.ToastUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.independ.framework.response.BaseResponse;
import com.independ.framework.response.ResponseTransformer;
import com.temp.dattex.Constants;
import com.temp.dattex.bean.InfoBySymbolBean;
import com.temp.dattex.bean.MarketListBean;
import com.temp.dattex.bean.SymbolConfigBean;
import com.temp.dattex.config.SymbolConfigs;
import com.temp.dattex.net.DataService;
import com.temp.dattex.net.WebSocket;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;

public class SwitchSymbolDialogViewModel {

    private  List<SymbolConfigBean> symbols;

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
