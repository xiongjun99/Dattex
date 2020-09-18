package com.temp.buda.register;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.common.framework.basic.BaseViewModel;
import com.common.framework.click.SingleClick;
import com.exchange.utilslib.LooperUtil;
import com.exchange.utilslib.ToastUtil;
import com.independ.framework.response.ResponseTransformer;
import com.temp.buda.Constants;
import com.temp.buda.R;
import com.temp.buda.country.CountryActivity;
import com.temp.buda.net.DataService;
import com.temp.buda.web.WebViewActivity;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.temp.dattex.register
 * @FileName     : RegisterStepOneViewModel.java
 * @Author       : chao
 * @Date         : 2020/5/13
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

public class RegisterStepOneViewModel extends BaseViewModel {

    private int reqeustCountry = 0x10;
    private int reqeustNextStep = 0x11;

    private ObservableField<Boolean> protocolChecked = new ObservableField<>(false);
    private ObservableField<String> userName = new ObservableField<>("");
    private ObservableField<String> phoneCode = new ObservableField<>("");
    private ObservableField<String> inviteCode = new ObservableField<>("");
    private ObservableField<String> countryName = new ObservableField<>("中国");
    private ObservableField<String> sendCodeText = new ObservableField<>("");

    public ObservableField<Boolean> getProtocolChecked() {
        return protocolChecked;
    }

    private String countryCode = "+86";

    public void setProtocolChecked(ObservableField<Boolean> protocolChecked) {
        this.protocolChecked = protocolChecked;
    }

    public ObservableField<String> getUserName() {
        return userName;
    }

    public void setUserName(ObservableField<String> userName) {
        this.userName = userName;
    }

    public ObservableField<String> getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(ObservableField<String> phoneCode) {
        this.phoneCode = phoneCode;
    }

    public ObservableField<String> getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(ObservableField<String> inviteCode) {
        this.inviteCode = inviteCode;
    }

    public ObservableField<String> getCountryName() {
        return countryName;
    }

    public void setCountryName(ObservableField<String> countryName) {
        this.countryName = countryName;
    }

    public ObservableField<String> getSendCodeText() {
        return sendCodeText;
    }

    public void setSendCodeText(ObservableField<String> sendCodeText) {
        this.sendCodeText = sendCodeText;
    }

    public RegisterStepOneViewModel(@NonNull Application application) {
        super(application);
        sendCodeText.set(application.getResources().getString(R.string.text_send_code));
    }


    @SingleClick
    public void changeProtocolCheck() {
        protocolChecked.set(!protocolChecked.get());
    }


    @SingleClick
    public void chooseCountry() {
        startActivity(CountryActivity.class, reqeustCountry);
    }

    @SuppressLint("CheckResult")
    @SingleClick
    public void sendPhoneCode() {
        if (TextUtils.isEmpty(userName.get())||userName.get().length()!=11){
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
            DataService.getInstance().sendPhoneMessage(userName.get(),countryCode).compose(ResponseTransformer.<Object>handleResult()).subscribe(o -> {
            }, t -> {
                ToastUtil.show(getApplication(), t.getMessage());
            });
        }

    }

    @SingleClick
    public void nextStep() {
        if (TextUtils.isEmpty(userName.get())) {
            ToastUtil.show(getApplication(), getApplication().getResources().getString(R.string.text_input_phone_code));
        } else if (TextUtils.isEmpty(phoneCode.get())) {
            ToastUtil.show(getApplication(), getApplication().getResources().getString(R.string.text_input_phone_code));
        } else if (TextUtils.isEmpty(inviteCode.get())) {
            ToastUtil.show(getApplication(), getApplication().getResources().getString(R.string.text_input_invited_code));
        } else if (!protocolChecked.get()) {
            ToastUtil.show(getApplication(), getApplication().getResources().getString(R.string.text_need_agree_protocol));
        } else {
            Bundle bundle = new Bundle();
            bundle.putString(Constants.KEY_ACCOUNT, userName.get());
            bundle.putString(Constants.KEY_PHONE_CODE, phoneCode.get());
            bundle.putString(Constants.KEY_INVITED_CODE, inviteCode.get());
            bundle.putString(Constants.KEY_COUNTRY_CODE, countryCode);
            startActivity(RegisterStepTwoActivity.class, bundle, reqeustNextStep);
        }
    }

    @SingleClick
    public void openProtocol() {
        Bundle bundle = new Bundle();
        bundle.putString(WebViewActivity.KEY_PARAM_TITLE, "协议");
        bundle.putString(WebViewActivity.KEY_PARAM_URL, "http://45.132.238.178/#/article?id=1");
        startActivity(WebViewActivity.class, bundle);

//        Intent it = new Intent(getApplication(), WebViewActivity.class);
//        it.putExtra(WebViewActivity.KEY_PARAM_TITLE, "协议");
//        it.putExtra(WebViewActivity.KEY_PARAM_URL, "http://45.132.238.178/#/article?id=1");
//        startActivity(it);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == reqeustCountry && data != null) {
            countryCode = data.getStringExtra(Constants.KEY_COUNTRY_CODE);
            getCountryName().set(data.getStringExtra(Constants.KEY_COUNTRY_NAME));
        } else if (requestCode == reqeustNextStep) {
            finish();
        }
    }
}


