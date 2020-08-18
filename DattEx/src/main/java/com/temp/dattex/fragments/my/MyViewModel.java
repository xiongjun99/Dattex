package com.temp.dattex.fragments.my;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.common.framework.basic.AppManager;
import com.common.framework.basic.BaseViewModel;
import com.common.framework.click.SingleClick;
import com.exchange.utilslib.LogUtil;
import com.temp.dattex.Constants;
import com.temp.dattex.R;
import com.temp.dattex.auth.AuthActivity;
import com.temp.dattex.config.AssetsConfigs;
import com.temp.dattex.database.LoginInfo;
import com.temp.dattex.login.LoginActivity;
import com.temp.dattex.net.ApiAddress;
import com.temp.dattex.order.OrderActivity;
import com.temp.dattex.record.CoinRecordActivity;
import com.temp.dattex.safe.SafeActivity;
import com.temp.dattex.setting.SettingActivity;
import com.temp.dattex.util.DialogUtil;
import com.temp.dattex.wallet.WalletActivity;
import com.temp.dattex.web.WebViewActivity;
import com.temp.dattex.withdraw.WithdrawActivity;

/**
 * @Package: com.temp.dattex.my
 * @ClassName: MyViewModel
 * @Description: java类作用描述
 * @Author: 李嘉伦
 * @CreateDate: 2020/5/16 18:52
 * @Email: 86152
 */

public class MyViewModel extends BaseViewModel {
    private static final int MY_VIEW_REQUEST_CODE = 50;
    private static final int MY_VIEW_REQUEST_AUTH = 51;

    public ObservableField<Boolean> isLogin = new ObservableField<>(false);
    public ObservableField<String> assets = new ObservableField<>("");
    public ObservableField<String> account = new ObservableField<>("");

    public MyViewModel(@NonNull Application application) {
        super(application);
        initUserInfo();
    }

    @SingleClick
    public void login() {
        startActivityForFragment(LoginActivity.class, null, MY_VIEW_REQUEST_CODE);
    }

    @SingleClick
    public void coinRecord() {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.KEY_COIN_NAME, "USDT");
        startActivity(CoinRecordActivity.class, bundle);
    }


    private void initUserInfo() {
        LogUtil.d("是否登录：" + LoginInfo.isSign());
        isLogin.set(LoginInfo.isSign());
        account.set(!isLogin.get() ? getApplication().getResources().getString(R.string.title_unlogin) : LoginInfo.getAccount());
        assets.set(AssetsConfigs.getInstance().getCnyTotal());
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MY_VIEW_REQUEST_CODE) {
            LogUtil.d("登录成功");
            initUserInfo();
        }

    }

    public void loginOut() {
        if (isLogin.get()) {
            LoginInfo.signOut();
            initUserInfo();
        }
    }

    @SingleClick
    public void nameAuth() {
        startActivityForFragment(AuthActivity.class, null, MY_VIEW_REQUEST_AUTH);
    }

    @SingleClick
    public void order() {
        startActivity(OrderActivity.class);
    }

    @SingleClick
    public void withdraw() {
        startActivity(WithdrawActivity.class);
    }

    @SingleClick
    public void recharge() {
        startActivity(WalletActivity.class);
    }

    @SingleClick
    public void invited() {
        Bundle bundle = new Bundle();
        bundle.putString(WebViewActivity.KEY_PARAM_URL, ApiAddress.USER_INVITED_URL);
        startActivity(WebViewActivity.class, bundle);
    }

    @SingleClick
    public void helpCenter() {
        Bundle bundle = new Bundle();
        bundle.putString(WebViewActivity.KEY_PARAM_URL, ApiAddress.USER_INVITED_URL);
        startActivity(WebViewActivity.class, bundle);
    }

    @SingleClick
    public void safeCenter() {
        if (LoginInfo.isSign()) {
            startActivity(SafeActivity.class);
        } else {
            startActivity(LoginActivity.class);
        }
    }

    @SingleClick
    public void customerService() {
        Bundle bundle = new Bundle();
        bundle.putString(WebViewActivity.KEY_PARAM_URL, ApiAddress.USER_INVITED_URL);
        startActivity(WebViewActivity.class, bundle);
    }

    @SingleClick
    public void setting() {
        startActivity(SettingActivity.class);
    }
    @Override
    public void onResume() {
        super.onResume();
        initUserInfo();
    }
}
