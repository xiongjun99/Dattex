package com.temp.buda.withdraw;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.databinding.Observable;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

import com.common.framework.basic.BaseViewModel;
import com.common.framework.bus.SingleLiveEvent;
import com.common.framework.click.SingleClick;
import com.exchange.utilslib.ToastUtil;
import com.independ.framework.response.ResponseTransformer;
import com.temp.buda.Constants;
import com.temp.buda.bean.NewAssetsBean;
import com.temp.buda.bean.OTCcfgBean;
import com.temp.buda.bean.WithdrawLimitBean;
import com.temp.buda.binding.adapter.EditTextBinding;
import com.temp.buda.binding.adapter.TitleBarClickBindingAdapter;
import com.temp.buda.config.AssetsConfigs;
import com.temp.buda.net.DataService;
import com.temp.buda.record.CoinRecordActivity;
import com.temp.buda.util.Utils;
import com.temp.buda.wallet.WalletModel;
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
public class WithdrawViewModel extends BaseViewModel implements TitleBarClickBindingAdapter.TitleRightClickListener, EditTextBinding.EditListener, AdapterView.OnItemClickListener, PopupWindow.OnDismissListener {

    private WithdrawLimitBean withdrawLimitBean;

    public WithdrawViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData scanLiveData = new MutableLiveData();
    private ObservableField<String> balance = new ObservableField<>();
    private ObservableField<String> withdrawAmount = new ObservableField<>("");
    public ObservableField<String> minAmount = new ObservableField<>("");
    public ObservableField<String> MaxAmount = new ObservableField<>("");
    private ObservableField<String> withdrawCoin = new ObservableField<>("");
    private ObservableField<String> realGetAmount = new ObservableField<>("");
    private ObservableField<String> serviceCharge = new ObservableField<>("0.0000000");
    private ObservableField<String> withdrawAddress = new ObservableField<>("");
    private ObservableField<String> minWithdraw = new ObservableField<>("0.01");
    private ObservableField<String> AdressAmount = new ObservableField<>("0.0000000");
    private ObservableField<String> buyRatio = new ObservableField<>("");
    private ObservableField<String> exchangeType = new ObservableField<>("");
    private ObservableField<String> sellnumber = new ObservableField<>("");
    private ObservableField<String> tips = new ObservableField<>("• 请勿向上述地址充值任何非USDT资产，否则资产将不可找回。\n" +
            "• 您充值至上述地址后，需要整个网络节点的确认，2次网络确认后到账，6次网络确认后可提币。\n" +
            "• 最小充值金额： " + minWithdraw.get() + " " + withdrawCoin + "，小于最小金额的充值将不会上账。");
    private ObservableField<String> price = new ObservableField<>("");
    public ObservableField<String> accountPrice = new ObservableField<>("0.000");
    public ObservableField<List<OTCcfgBean>> otc = new ObservableField<>();
    private ObservableField<String> payType = new ObservableField<>("请选择");
    private ObservableField<String> VerificationCode = new ObservableField<>("");
    public ObservableField<String> OtcminAmount = new ObservableField<>("");
    public ObservableField<String> OtcMaxAmount = new ObservableField<>("");
    public ObservableField<Integer> pPosition = new ObservableField<>(0);

    public ObservableField<String> getVerificationCode() {
        return VerificationCode;
    }
    public ObservableField<Integer> popStaus = new ObservableField<>(0);
    public ObservableField<String> unit = new ObservableField<>();

    public ObservableField<String> getUnit() {
        return unit;
    }

    public void setUnit(ObservableField<String> unit) {
        this.unit = unit;
    }

    public WalletModel.UIChangeObservable uc = new WalletModel.UIChangeObservable();

    public ObservableField<String> getMaxAmount() {
        return MaxAmount;
    }

    public void setMaxAmount(ObservableField<String> maxAmount) {
        MaxAmount = maxAmount;
    }

    public ObservableField<String> getOtcminAmount() {
        return OtcminAmount;
    }

    public void setOtcminAmount(ObservableField<String> otcminAmount) {
        OtcminAmount = otcminAmount;
    }


    public ObservableField<String> getOtcMaxAmount() {
        return OtcMaxAmount;
    }

    public void setOtcMaxAmount(ObservableField<String> otcMaxAmount) {
        OtcMaxAmount = otcMaxAmount;
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//        if (getExchangeType().get().equals(otc.get().get(0).getData().getOtcCfgs().get(i).getCurrency())){
//            getAccountPrice().set("");
//            getNumber().set("");
//            getUnit().set("");
//        } else {
//            getExchangeType().set(String.valueOf(otc.get().get(0).getData().getOtcCfgs().get(i).getCurrency()));
//            getPrice().set(String.valueOf(otc.get().get(0).getData().getOtcCfgs().get(i).getSellRatio()));
//            getUnit().set(otc.get().get(0).getData().getOtcCfgs().get(i).getSymbol());
//        }
        uc.pop.setValue(false);
    }

    @Override
    public void onDismiss() {

    }

    public static class UIChangeObservable {
        //密码开关观察者
        public SingleLiveEvent<Boolean> pop = new SingleLiveEvent<>();
    }
    public void setVerificationCode(ObservableField<String> verificationCode) {
        VerificationCode = verificationCode;
    }

    public ObservableField<String> getNumber() {
        return number;
    }

    public void setNumber(ObservableField<String> number) {
        this.number = number;
    }

    public ObservableField<String> number = new ObservableField<>("");

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

    public ObservableField<String> getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(ObservableField<String> minAmount) {
        this.minAmount = minAmount;
    }
    public ObservableField<String> getPrice() {
        return price;
    }
    public ObservableField<String> getAdressAmount() {
        return AdressAmount;
    }

    public void setAdressAmount(ObservableField<String> adressAmount) {
        AdressAmount = adressAmount;
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

    public ObservableField<String> getBalance() {
        return balance;
    }

    public void setBalance(ObservableField<String> balance) {
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
             getWithdrawAmount().set(s);
            if (!TextUtils.isEmpty(s)){
                if (Utils.compareTo(getWithdrawAmount().get(),balance.get())==false){
                    getWithdrawAmount().set(balance.get());
                    AdressAmount.set(Utils.subtraction(getWithdrawAmount().get(),serviceCharge.get()));
                }else {
                    getWithdrawAmount().set(s);
                    AdressAmount.set(Utils.subtraction(getWithdrawAmount().get(),serviceCharge.get()));
                }
            }else {
                AdressAmount.set("0.0000000");
            }
        }
    };

    @SingleClick
    public void AddressTakeOut() {

    }

    @SingleClick
    public void SellUSDT() {

    }

    @SingleClick
    public void withdrawAll() {
//        if (null != withdrawLimitBean && balance.get() > withdrawLimitBean.getMinOut()) {
//            float v = balance.get() * withdrawLimitBean.getFeeRate();
//            if (v < withdrawLimitBean.getMinFee()) {
//                v = withdrawLimitBean.getMinFee();
//            }
//            withdrawAmount.set(String.valueOf(balance.get() - v));
//            serviceCharge.set(String.valueOf(v));
//        }
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
//     startActivity(WithdrawListActivity.class,1);
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
        bundle.putInt(Constants.REQUEST_KEY_INOROUT, 1);
        bundle.putString(Constants.KEY_COIN_NAME, withdrawCoin.get());
        startActivity(CoinRecordActivity.class, bundle);
    }

    @Override
    public void afterTextChanged(EditText view, String s) {
        view.setSelection(s.length());
        number.set(s);
        if (!TextUtils.isEmpty(s)){
            accountPrice.set(Utils.format4(String.valueOf(Utils.keepTwo(Float.valueOf(s.toString())*Float.valueOf(price.get())))));
        }else {
            accountPrice.set("0.000");
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
//        getOtcData();
        withdrawCoin.set("USDT");
        NewAssetsBean coinInfo = AssetsConfigs.getInstance().getCoinInfo("USDT");
        DataService.getInstance().withdrawLimit(withdrawCoin.get()).compose(ResponseTransformer.<WithdrawLimitBean>handleResult()).subscribe(
                bean -> {
                        withdrawLimitBean = bean;
                        minAmount.set(""+(int)withdrawLimitBean.getMinOut());
                        MaxAmount.set(""+(int)withdrawLimitBean.getMaxOut());
                        serviceCharge.set(Utils.format8(String.valueOf(bean.getWithdrawFee())));
                }, t -> {
                    ToastUtil.show(getApplication(),t.getMessage());
                }
        );
    }

    @SuppressLint("CheckResult")
    private void getOtcData() {
        DataService.getInstance().getOtcCfg().compose(ResponseTransformer.handleResult()).subscribe(
                b -> {
                    if(b!=null){
//                        exchangeType.set(otc.get().get(0).getData().getOtcCfgs().get(0).getCurrency());
//                        price.set(""+otc.get().get(0).getData().getOtcCfgs().get(0).getSellRatio());
//                        otc.set(b);
//                        OtcminAmount.set(Utils.format0(otc.get().get(0).getData().getPayTypes().get(0).getMinOut()));
//                        OtcMaxAmount.set(Utils.format0(otc.get().get(0).getData().getPayTypes().get(0).getMaxOut()));
//                        unit.set(otc.get().get(0).getData().getOtcCfgs().get(0).getSymbol());
                    }else {
                        ToastUtil.show(getApplication(),"获取配置失败");
                    }
                }, t -> {
                    ToastUtil.show(getApplication(),t.getMessage());
                });
    }
}
