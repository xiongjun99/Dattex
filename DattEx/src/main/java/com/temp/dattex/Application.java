package com.temp.dattex;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.framework.basic.AppManager;
import com.common.framework.basic.BaseActivity;
import com.common.framework.basic.BaseApplication;
import com.exchange.utilslib.SPUtil;
import com.exchange.utilslib.ToastUtil;
import com.independ.framework.client.RetrofitClient;
import com.independ.framework.response.ResponseTransformer;
import com.temp.dattex.config.SymbolConfigs;
import com.temp.dattex.login.LoginActivity;
import com.temp.dattex.net.ApiAddress;
import com.temp.dattex.net.DataService;
import com.temp.dattex.net.WebSocket;
import com.temp.dattex.util.Utils;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.crashreport.CrashReport;

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
