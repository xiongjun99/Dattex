package com.temp.buda.resetpwd;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Intent;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.common.framework.basic.BaseViewModel;
import com.common.framework.click.SingleClick;
import com.exchange.utilslib.LogUtil;
import com.exchange.utilslib.LooperUtil;
import com.exchange.utilslib.ToastUtil;
import com.independ.framework.response.ResponseTransformer;
import com.temp.buda.Constants;
import com.temp.buda.R;
import com.temp.buda.country.CountryActivity;
import com.temp.buda.database.LoginInfo;
import com.temp.buda.net.DataService;


public class ResetPasswordViewModel extends BaseViewModel {
    private int requestCountry = 23;


    public ResetPasswordViewModel(@NonNull Application application) {
        super(application);
        sendCodeText.set(application.getResources().getString(R.string.text_send_code));
    }

    private ObservableField<String> mobile = new ObservableField<>("");
    private ObservableField<String> againPassword = new ObservableField<>("");
    private ObservableField<String> countryCode = new ObservableField<String>("+86");
    private ObservableField<String> countryName = new ObservableField<String>("中国");
    private ObservableField<String> phoneCode = new ObservableField<>("");
    private ObservableField<String> sendCodeText = new ObservableField<>("");

    public ObservableField<String> getSendCodeText() {
        return sendCodeText;
    }

    public void setSendCodeText(ObservableField<String> sendCodeText) {
        this.sendCodeText = sendCodeText;
    }

    public ObservableField<String> getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(ObservableField<String> phoneCode) {
        this.phoneCode = phoneCode;
    }


    public ObservableField<String> getMobile() {
        return mobile;
    }

    public void setMobile(ObservableField<String> mobile) {
        this.mobile = mobile;
    }

    public ObservableField<String> getCountryName() {
        return countryName;
    }
    public ObservableField<String> getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(ObservableField<String> countryCode) {
        this.countryCode = countryCode;
    }

    public void setCountryName(ObservableField<String> countryName) {
        this.countryName = countryName;
    }
    public ObservableField<String> getAgainPassword() {
        return againPassword;
    }

    public void setAgainPassword(ObservableField<String> againPassword) {
        this.againPassword = againPassword;
    }

    @SuppressLint("CheckResult")
    @SingleClick
    public void resetDone() {
        if (TextUtils.isEmpty(mobile.get())){
            ToastUtil.show(getApplication(),"手机号不能为空");
            return;
        }
        if (TextUtils.isEmpty(phoneCode.get())){
            ToastUtil.show(getApplication(),"验证码不能为空");
            return;
        }
        if (againPassword.get().length()<=5){
            ToastUtil.show(getApplication(),"密码不能小于6位");
            return;
        }
        DataService.getInstance().resetPassword(mobile.get(),phoneCode.get(),countryCode.get(),againPassword.get()).
                compose(ResponseTransformer.handleResult())
                .subscribe(s -> {
                    finish();
                    ToastUtil.show(getApplication(), getApplication().getResources().getString(R.string.text_reset_password_success));
                }, throwable -> ToastUtil.show(getApplication(), throwable.getMessage()));
    }
    @SingleClick
    public void chooseCountryCode() {
        LogUtil.e("chooseCountryCode");
        startActivity(CountryActivity.class, requestCountry);
    }
    @SuppressLint("CheckResult")
    @SingleClick
    public void sendPhoneCode() {
        if (TextUtils.isEmpty(mobile.get())||mobile.get().length()!=20){
            ToastUtil.show(getApplication(),"请填写正确的手机号");
            return;
        }
        try {
            final int i = Integer.parseInt(sendCodeText.get());
            if (i == 1) {
                sendCodeText.set(getApplication().getResources().getString(R.string.text_send_code));
            } else {
                sendCodeText.set(String.valueOf(i - 1));
                LooperUtil.getHandler().postDelayed(this::sendPhoneCode, 1000);
            }
        } catch (Exception e) {
            sendCodeText.set("60");
            LooperUtil.getHandler().postDelayed(this::sendPhoneCode, 1000);
            DataService.getInstance().sendCodeReset(mobile.get(),countryCode.get()).compose(ResponseTransformer.<Object>handleResult()).subscribe(o -> {
            }, t -> {
                ToastUtil.show(getApplication(), t.getMessage());
            });
        }

    }

    
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == requestCountry && data != null) {
            countryCode.set(data.getStringExtra(Constants.KEY_COUNTRY_CODE));
            getCountryName().set(data.getStringExtra(Constants.KEY_COUNTRY_NAME));
        }
    }
}
