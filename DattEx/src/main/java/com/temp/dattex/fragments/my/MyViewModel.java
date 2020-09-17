package com.temp.dattex.fragments.my;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.common.framework.basic.BaseViewModel;
import com.common.framework.click.SingleClick;
import com.exchange.utilslib.LogUtil;
import com.exchange.utilslib.ToastUtil;
import com.independ.framework.response.ResponseTransformer;
import com.temp.dattex.Constants;
import com.temp.dattex.R;
import com.temp.dattex.auth.AuthActivity;
import com.temp.dattex.bean.NewAssetsBean;
import com.temp.dattex.config.AssetsConfigs;
import com.temp.dattex.database.LoginInfo;
import com.temp.dattex.help.HelpActivity;
import com.temp.dattex.invite.InviteActivity;
import com.temp.dattex.login.LoginActivity;
import com.temp.dattex.net.DataService;
import com.temp.dattex.order.OrderActivity;
import com.temp.dattex.web.WebViewActivity;
import com.temp.dattex.withdraworwallet.WithdrawOrWalletActivity;
import com.temp.dattex.safe.SafeActivity;
import com.temp.dattex.setting.SettingActivity;
import com.temp.dattex.util.Utils;
import com.temp.dattex.wallet.WalletActivity;
import com.temp.dattex.withdraw.WithdrawActivity;

import java.math.BigDecimal;

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
    public ObservableField<String> balance = new ObservableField<>("0.0000000");
    public ObservableField<String> frozen = new ObservableField<>("0.0");
    public ObservableField<String> cnyprice = new ObservableField<>("0.0");
    public ObservableField<String> nameAuth = new ObservableField<>("实名认证");

    public ObservableField<String> getCnyprice() {
        return cnyprice;
    }

    public void setCnyprice(ObservableField<String> cnyprice) {
        this.cnyprice = cnyprice;
    }

    public ObservableField<String> getFrozen() {
        return frozen;
    }

    public void setFrozen(ObservableField<String> frozen) {
        this.frozen = frozen;
    }
    public ObservableField<String> getBalance() {
        return balance;
    }
    public ObservableField<String> getNameAuth() {
        return nameAuth;
    }

    public void setNameAuth(ObservableField<String> nameAuth) {
        this.nameAuth = nameAuth;
    }

    public void setBalance(ObservableField<String> balance) {
        this.balance = balance;
    }
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
//        Bundle bundle = new Bundle();
//        bundle.putString(Constants.KEY_COIN_NAME, "USDT");
//        bundle.putInt(Constants.REQUEST_KEY_INOROUT, 2);
//        startActivity(CoinRecordActivity.class, bundle);
        Bundle bundle = new Bundle();
        bundle.putString(Constants.KEY_COIN_NAME, "USDT");
        startActivity(WithdrawOrWalletActivity.class, bundle);

    }


    private void initUserInfo() {
        LogUtil.d("是否登录：" + LoginInfo.isSign());
        isLogin.set(LoginInfo.isSign());
        account.set(!isLogin.get() ? getApplication().getResources().getString(R.string.title_unlogin) : LoginInfo.getAccount());
        assets.set(AssetsConfigs.getInstance().getCnyTotal());
        balance.set("0.0000000");
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MY_VIEW_REQUEST_CODE) {
            LogUtil.d("登录成功");
            initUserInfo();
        }else if (requestCode == MY_VIEW_REQUEST_AUTH) {
//         nameAuth.set("实名认证中");
            onResume();
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
        if (LoginInfo.isCertification().startsWith("0")) {
            startActivityForFragment(AuthActivity.class, null, MY_VIEW_REQUEST_AUTH);
        }
    }

    @SingleClick
    public void order() {
        startActivity(OrderActivity.class);
    }

    @SingleClick
    public void withdraw() {
        if (LoginInfo.isCertification().startsWith("2")){
            Bundle data = new Bundle();
            data.putString("balance",balance.get());
            startActivity(WithdrawActivity.class,data);
        } else {
            Toast.makeText(getApplication(),"请先实名认证",Toast.LENGTH_LONG).show();
        }
    }

    @SingleClick
    public void recharge() {
        if (LoginInfo.isCertification().startsWith("2")){
            startActivity(WalletActivity.class);
         } else {
            Toast.makeText(getApplication(),"请先实名认证",Toast.LENGTH_LONG).show();
        }
    }

    @SingleClick
    public void invited() {
        startActivity(InviteActivity.class);
    }

    @SingleClick
    public void helpCenter() {
        startActivity(HelpActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putString(WebViewActivity.KEY_PARAM_URL, ApiAddress.USER_INVITED_URL);
//        startActivity(WebViewActivity.class, bundle);
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
        bundle.putString(WebViewActivity.KEY_PARAM_URL, "http://kf.buda.tc/php/app.php?widget-mobile");
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
        if (LoginInfo.isSign()) {
            freshAssetsByCoinId("USDT");
        }
    }
    @SuppressLint("CheckResult")
    public void freshAssetsByCoinId(String CoinId) {
        DataService.getInstance().getAssetsByCoinId(CoinId).compose(ResponseTransformer.<NewAssetsBean>handleResult()).subscribe(
                assetsBean -> {
                    if(null != assetsBean) {
                      AssetsConfigs.getInstance().getNewAssetsItemBeanMap().put("USDT",assetsBean);
                        BigDecimal bg = new BigDecimal(assetsBean.getBalance());
                        balance.set(bg.toPlainString());
                        System.out.println("-------余额"+assetsBean.getBalance());
                        System.out.println("-------余额"+balance.get());
                        frozen.set("冻结: "+assetsBean.getFrozen());
                        LoginInfo.getisCertification(assetsBean.getIsCertification());
                        cnyprice.set("≈"+" "+ Utils.keepTwo(Double.valueOf(assetsBean.getCnyprice())*Double.valueOf(assetsBean.getBalance()))+" CNY");
                        System.out.println("--------认证"+assetsBean.getIsCertification());
                        if (assetsBean.getIsCertification().equals("1")){
                                nameAuth.set("审核中");
                            } else if (assetsBean.getIsCertification().equals("2")){
                                nameAuth.set("已实名认证");
                            } else if (assetsBean.getIsCertification().equals("3")){
                                nameAuth.set("未通过实名认证");
                            } else {
                            nameAuth.set("实名认证");
                        }
                    }
                }, t -> {
                    System.out.println("-------no----根据币种ID获取对应币种的会员资产钱包信息");
                }
        );
    }
}
