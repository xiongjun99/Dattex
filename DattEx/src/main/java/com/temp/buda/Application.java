package com.temp.buda;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import com.common.framework.basic.AppManager;
import com.common.framework.basic.BaseActivity;
import com.common.framework.basic.BaseApplication;
import com.exchange.utilslib.SPUtil;
import com.independ.framework.client.RetrofitClient;
import com.independ.framework.response.ResponseTransformer;
import com.temp.buda.config.SymbolConfigs;
import com.temp.buda.login.LoginActivity;
import com.temp.buda.net.ApiAddress;
import com.temp.buda.net.DataService;
import com.temp.buda.net.WebSocket;
import java.util.HashMap;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.temp.dattex
 * @FileName     : Application.java
 * @Author       : chao
 * @Date         : 2020/5/14
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
public class Application extends BaseApplication implements SPUtil.SpClearListener {
    public static String URL;
    @SuppressLint("CheckResult")
    @Override
    public void onCreate() {
        super.onCreate();
        SPUtil.initSp(this);
        SPUtil.setSpClearListener(this);
        RetrofitClient.getInstance().initRetrofit(ApiAddress.BASE_URL);
        SymbolConfigs.getInstance().init();
        DataService.getInstance().getUsdtRate().compose(ResponseTransformer.<Double>handleResult()).subscribe(
                d -> {
                    SPUtil.setParam(Constants.SP_KEY_USDT_CNY_RATE, String.valueOf(d));
                    SymbolConfigs.getInstance().setCnyRate(d.floatValue());
                }, t -> {

                }
        );
      WebSocket.getInstance().init(ApiAddress.WEB_SOCKET_URL);
//    CrashReport.initCrashReport(this, "177e35b69c", true);
    }

    public HashMap<String, Object> createRequestParams() {
        HashMap<String, Object> params = new HashMap<>();
        return params;
    }

    @Override
    public void onSpCleared() {
        Activity peek = AppManager.getActivityStack().peek();
        if (peek instanceof BaseActivity && ((BaseActivity) peek).needLogin()) {
            Intent intent = new Intent(peek, LoginActivity.class);
            intent.putExtra(Constants.KEY_CLASS_NAME, peek.getClass().getName());
            peek.startActivity(intent);
            peek.finish();
        }
    }
}
