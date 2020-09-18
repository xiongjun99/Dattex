package com.temp.buda.config;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import com.exchange.utilslib.LogUtil;
import com.exchange.utilslib.LooperUtil;
import com.exchange.utilslib.SPUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.independ.framework.response.BaseResponse;
import com.independ.framework.response.ResponseTransformer;
import com.temp.buda.Constants;
import com.temp.buda.bean.SocketDataBean;
import com.temp.buda.bean.SymbolConfigBean;
import com.temp.buda.net.DataService;
import com.temp.buda.net.WebSocket;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.temp.dattex.config
 * @FileName     : SymbolConfigs.java
 * @Author       : chao
 * @Date         : 2020/5/26
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
public class SymbolConfigs implements WebSocket.SocketListener {

    private float cnyRate = 0;

    public void setCnyRate(float setCnyRate) {
        this.cnyRate = setCnyRate;
    }

    public float getCnyRate() {
        return cnyRate;
    }

    private static volatile SymbolConfigs configs;
    private Map<String, SymbolConfigBean> configMap = new HashMap<>();

    @SuppressLint("CheckResult")
    public void init() {
        String param = (String) SPUtil.getParam(Constants.SP_KEY_SYMBOL_CONFIG, "");
        if (!TextUtils.isEmpty(param)) {
            List<SymbolConfigBean> symbolConfigBeans = new Gson().fromJson(param, new TypeToken<List<SymbolConfigBean>>() {
            }.getType());
            if (null != symbolConfigBeans && symbolConfigBeans.size() > 0) {
                int size = symbolConfigBeans.size();
                for (int i = 0; i < size; i++) {
                    SymbolConfigBean symbolConfigBean = symbolConfigBeans.get(i);
                    configMap.put(symbolConfigBean.getSymbol().toUpperCase(), symbolConfigBean);
                }

            }
        }

        Observable<BaseResponse<List<SymbolConfigBean>>> baseResponseObservable = DataService.getInstance().symbolConfig();
        baseResponseObservable.compose(ResponseTransformer.handleResult()).subscribe(o -> {
            if (null != o && o.size() > 0) {
//                Collections.sort(o, (symbolConfigBean, t1) -> symbolConfigBean.getSort() - t1.getSort());
                int size = o.size();
                for (int i = 0; i < size; i++) {
                    SymbolConfigBean symbolConfigBean = o.get(i);
                    configMap.put(symbolConfigBean.getSymbol().toUpperCase(), symbolConfigBean);
                }
                SPUtil.setParam(Constants.SP_KEY_SYMBOL_CONFIG, new Gson().toJson(o));
                WebSocket.getInstance().setSocketListener(this);
            }

        }, throwable -> {
            LooperUtil.getHandler().postDelayed(this::init, 5000);
        });
    }

    private SymbolConfigs() {

    }

    public static SymbolConfigs getInstance() {
        if (null == configs) {
            synchronized (SymbolConfigs.class) {
                if (null == configs) {
                    configs = new SymbolConfigs();
                }
            }
        }
        return configs;
    }

    public List<SymbolConfigBean> getSymbols() {
        return new LinkedList<>(configMap.values());
    }
    public SymbolConfigBean getSymbol(String symbol) {
        return configMap.get(symbol);
    }

    @Override
    public void open() {
    }

    @Override
    public void onMessage(String message) {
        if (!TextUtils.isEmpty(message)) {
            SocketDataBean socketDataBean = gson.fromJson(message, SocketDataBean.class);
            if (null != socketDataBean) {
                SocketDataBean.TickBean tick = socketDataBean.getTick();
                if (null != tick) {
                    String symbol = tick.getSymbol();
                    SymbolConfigBean symbolConfigBean = configMap.get(symbol.toUpperCase());
                    if (null != symbolConfigBean) {
                        symbolConfigBean.setId(tick.getId());
                        symbolConfigBean.setHigh(tick.getHigh());
                        symbolConfigBean.setOpen(tick.getOpen());
                        symbolConfigBean.setLow(tick.getLow());
                        symbolConfigBean.setClose(tick.getClose());
                        symbolConfigBean.setVol(tick.getVol());
                        symbolConfigBean.setChanges(tick.getChanges());
                    }
                }
            }
        }
    }

    @Override
    public void onClosed() {
        LogUtil.e("socket onClosed");
    }

    @Override
    public void onFailure() {
        LogUtil.e("socket onFailure");
    }

    Gson gson = new Gson();
}
