package com.temp.dattex.withdraw;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.databinding.Observable;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

import com.common.framework.basic.BaseViewModel;
import com.common.framework.click.SingleClick;
import com.exchange.utilslib.ToastUtil;
import com.independ.framework.response.ResponseTransformer;
import com.temp.dattex.Constants;
import com.temp.dattex.R;
import com.temp.dattex.bean.AssetsBean;
import com.temp.dattex.bean.OTCcfgBean;
import com.temp.dattex.bean.WithdrawLimitBean;
import com.temp.dattex.binding.adapter.EditTextBinding;
import com.temp.dattex.binding.adapter.TitleBarClickBindingAdapter;
import com.temp.dattex.config.AssetsConfigs;
import com.temp.dattex.config.SymbolConfigs;
import com.temp.dattex.net.DataService;
import com.temp.dattex.record.CoinRecordActivity;
import com.temp.dattex.util.Utils;
import com.yzq.zxinglibrary.common.Constant;

import java.util.List;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.temp.dattex.withdraw
 * @FileName     : WithdrawViewModel.java
 * @Author       : chao
 * @Date         : 2020/5/19
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
public class WithdrawViewModel extends BaseViewModel implements TitleBarClickBindingAdapter.TitleRightClickListener, EditTextBinding.EditListener {

    private WithdrawLimitBean withdrawLimitBean;

    public WithdrawViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData scanLiveData = new MutableLiveData();
    private ObservableField<Float> balance = new ObservableField<>(0f);
    private ObservableField<String> withdrawAmount = new ObservableField<>("");
    private ObservableField<String> withdrawCoin = new ObservableField<>("");
    private ObservableField<String> realGetAmount = new ObservableField<>("");
    private ObservableField<String> serviceCharge = new ObservableField<>("");
    private ObservableField<String> withdrawAddress = new ObservableField<>("");
    private ObservableField<String> minWithdraw = new ObservableField<>("0.01");

    private ObservableField<String> buyRatio = new ObservableField<>("");
    private ObservableField<String> exchangeType = new ObservableField<>("");

    private ObservableField<String> sellnumber = new ObservableField<>("");

    private ObservableField<String> tips = new ObservableField<>("• 请勿向上述地址充值任何非USDT资产，否则资产将不可找回。\n" +
            "• 您充值至上述地址后，需要整个网络节点的确认，2次网络确认后到账，6次网络确认后可提币。\n" +
            "• 最小充值金额： " + minWithdraw.get() + " " + withdrawCoin + "，小于最小金额的充值将不会上账。");
    private ObservableField<String> price = new ObservableField<>("");
    private ObservableField<String> accountPrice = new ObservableField<>("");
    public ObservableField<List<OTCcfgBean>> otc = new ObservableField<>();
    private ObservableField<String> payType = new ObservableField<>("请选择");

    public ObservableField<String> getNumber() {
        return number;
    }

    public void setNumber(ObservableField<String> number) {
        this.number = number;
    }

    private ObservableField<String> number = new ObservableField<>("");

    public ObservableField<String> getPayType() {
        return payType;
    }

    public void setPayType(ObservableField<String> payType) {
        this.payType = payType;
    }

    public ObservableField<List<OTCcfgBean>> getOtc() {
        return otc;
    }

    public void setOtc(ObservableField<List<OTCcfgBean>> otc) {
        this.otc = otc;
    }
    public ObservableField<String> getSellnumber() {
        return sellnumber;
    }

    public void setSellnumber(ObservableField<String> sellnumber) {
        this.sellnumber = sellnumber;
    }


    public ObservableField<String> getAccountPrice() {
        return accountPrice;
    }

    public void setAccountPrice(ObservableField<String> accountPrice) {
        this.accountPrice = accountPrice;
    }


    public ObservableField<String> getPrice() {
        return price;
    }

    public void setPrice(ObservableField<String> price) {
        this.price = price;
    }

    public ObservableField<String> getBuyRatio() {
        return buyRatio;
    }

    public void setBuyRatio(ObservableField<String> buyRatio) {
        this.buyRatio = buyRatio;
    }

    public ObservableField<Float> getBalance() {
        return balance;
    }

    public void setBalance(ObservableField<Float> balance) {
        this.balance = balance;
    }

    public ObservableField<String> getWithdrawAmount() {
        return withdrawAmount;
    }

    public void setWithdrawAmount(ObservableField<String> withdrawAmount) {
        this.withdrawAmount = withdrawAmount;
    }
    public ObservableField<String> getExchangeType() {
        return exchangeType;
    }

    public void setExchangeType(ObservableField<String> exchangeType) {
        this.exchangeType = exchangeType;
    }
    public ObservableField<String> getWithdrawCoin() {
        return withdrawCoin;
    }

    public void setWithdrawCoin(ObservableField<String> withdrawCoin) {
        this.withdrawCoin = withdrawCoin;
    }

    public ObservableField<String> getRealGetAmount() {
        return realGetAmount;
    }

    public void setRealGetAmount(ObservableField<String> realGetAmount) {
        this.realGetAmount = realGetAmount;
    }

    public ObservableField<String> getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(ObservableField<String> serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public ObservableField<String> getWithdrawAddress() {
        return withdrawAddress;
    }

    public void setWithdrawAddress(ObservableField<String> withdrawAddress) {
        this.withdrawAddress = withdrawAddress;
    }
    public void WithdrawAddress(String address) {
        withdrawAddress.set(address);
    }
    public ObservableField<String> getTips() {
        return tips;
    }

    public void setTips(ObservableField<String> tips) {
        this.tips = tips;
    }

    private Observable.OnPropertyChangedCallback withdrawAmountChangeCallBack = new Observable.OnPropertyChangedCallback() {
        @Override
        public void onPropertyChanged(Observable sender, int propertyId) {
            String s = getWithdrawAmount().get();
            if (!TextUtils.isEmpty(s)) {
                if (s.equals(".")) {
                    s = "0.";
                } else if (s.indexOf(".") != s.lastIndexOf(".")) {
                    s = s.substring(0, s.length() - 1);
                } else if (s.startsWith("0") && !s.equals("0") && !s.startsWith("0.")) {
                    s = s.substring(1);
                }
            }
            if (TextUtils.isEmpty(s)) {
                serviceCharge.set("0.00");
            } else {
                float v = Float.parseFloat(s) * withdrawLimitBean.getFeeRate();
                serviceCharge.set(String.valueOf(v < withdrawLimitBean.getMinFee() ? withdrawLimitBean.getMinFee() : v));
            }
            getWithdrawAmount().set(s);
        }
    };

    @SuppressLint("CheckResult")
    @SingleClick
    public void doWithdraw() {
        DataService.getInstance().withdrawCoin(withdrawCoin.get(), withdrawAmount.get(), withdrawAddress.get()).compose(ResponseTransformer.handleResult()).<Object>subscribe(
                b -> {
                    finish();
                }, t -> {

                }
        );
    }
    @SingleClick
    public void AddressTakeOut() {

    }

    @SingleClick
    public void SellUSDT() {

    }

    @SingleClick
    public void withdrawAll() {
        if (null != withdrawLimitBean && balance.get() > withdrawLimitBean.getMinOut()) {
            float v = balance.get() * withdrawLimitBean.getFeeRate();
            if (v < withdrawLimitBean.getMinFee()) {
                v = withdrawLimitBean.getMinFee();
            }
            withdrawAmount.set(String.valueOf(balance.get() - v));
            serviceCharge.set(String.valueOf(v));
        }
    }
    @SingleClick
    public void Confirm_Sell() {
        if (Integer.valueOf(number.get())<200){
            number.set("");
            return;
        }
    }
    @SingleClick
    public void scanQrCode() {
        scanLiveData.postValue(null);
    }

    //添加提币地址响应
    @SingleClick
    public void AddwithdrawAddress() {
//    startActivity(NewWithdrawActivity.class);
    }
    //选择提币地址响应
    @SingleClick
    public void SelectwithdrawAddress() {
        startActivity(WithdrawListActivity.class);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 扫描二维码/条码回传
        if (requestCode == 0x1110 && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                String content = data.getStringExtra(Constant.CODED_CONTENT);
                getWithdrawAddress().set(content);
            }
        }
    }

    @SingleClick
    @Override
    public void rightClick() {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.KEY_COIN_NAME, withdrawCoin.get());
        startActivity(CoinRecordActivity.class, bundle);
    }

    @Override
    public void afterTextChanged(EditText view, String s) {
        view.setSelection(s.length());
        number.set(s);
        if (!TextUtils.isEmpty(s.toString())){
            accountPrice.set(String.valueOf(Utils.keepTwo(Float.valueOf(s.toString())*Float.valueOf(price.get()))));
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        getWithdrawAmount().addOnPropertyChangedCallback(withdrawAmountChangeCallBack);
    }

    @Override
    public void onStop() {
        super.onStop();
        getWithdrawAmount().removeOnPropertyChangedCallback(withdrawAmountChangeCallBack);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onCreate() {
        super.onCreate();
        sellnumber.set("可卖："+AssetsConfigs.getInstance().getCoinInfo("USDT").getBalance()+" USDT");
        getOtcData();
//        DataService.getInstance().withdrawLimit(withdrawCoin.get()).compose(ResponseTransformer.<WithdrawLimitBean>handleResult()).subscribe(
//                bean -> {
//                    if (bean.getAllowOut()) {
//                        withdrawLimitBean = bean;
//                    } else {
//                        finish();
//                    }
//                }, t -> {
//
//                }
//        );

//        AssetsBean.AssetsItemBean coinInfo = AssetsConfigs.getInstance().getCoinInfo(withdrawCoin.get().toUpperCase());
//        if (null != coinInfo && !TextUtils.isEmpty(coinInfo.getBalance())) {
//            balance.set(Float.parseFloat(coinInfo.getBalance()));
//        }
    }
    @SuppressLint("CheckResult")
    private void getOtcData() {
        DataService.getInstance().getOtcCfg().compose(ResponseTransformer.handleResult()).subscribe(
                b -> {
                    if(b!=null && b.size() != 0){
                        exchangeType.set(b.get(0).getCurrency());
                        price.set(""+b.get(0).getSellRatio());
                        otc.set(b);
                    }else {
                        ToastUtil.show(getApplication(),"获取OTC配置失败");
                        finish();
                    }
                }, t -> {
                });
    }
}
