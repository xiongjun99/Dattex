package com.temp.dattex.resetpwd;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.common.framework.basic.BaseViewModel;
import com.common.framework.click.SingleClick;
import com.exchange.utilslib.LooperUtil;
import com.exchange.utilslib.ToastUtil;
import com.independ.framework.response.ResponseTransformer;
import com.temp.dattex.R;
import com.temp.dattex.net.DataService;


public class ResetPasswordViewModel extends BaseViewModel {
    public ResetPasswordViewModel(@NonNull Application application) {
        super(application);
    }

    private ObservableField<String> userName = new ObservableField<>("");
    private ObservableField<String> phoneCode = new ObservableField<>("");
    private ObservableField<String> password = new ObservableField<>("");
    private ObservableField<String> sendCodeText = new ObservableField<>("发送验证码");

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

    public ObservableField<String> getPassword() {
        return password;
    }

    public void setPassword(ObservableField<String> password) {
        this.password = password;
    }

    public ObservableField<String> getSendCodeText() {
        return sendCodeText;
    }

    public void setSendCodeText(ObservableField<String> sendCodeText) {
        this.sendCodeText = sendCodeText;
    }

    @SingleClick
    public void sendPhoneCode() {
        try {
            final int i = Integer.parseInt(sendCodeText.get());
            if (i == 1) {
                sendCodeText.set(getApplication().getResources().getString(R.string.text_send_code));
            } else {
                sendCodeText.set(String.valueOf(i - 1));
                LooperUtil.getHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sendPhoneCode();
                    }
                }, 1000);
            }
        } catch (Exception e) {
            sendCodeText.set("60");
            LooperUtil.getHandler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    sendPhoneCode();
                }
            }, 1000);
        }
    }

    @SuppressLint("CheckResult")
    @SingleClick
    public void resetDone() {
        DataService.getInstance().userProtocol().
                compose(ResponseTransformer.handleResult())
                .subscribe(s -> {
                    finish();
                    ToastUtil.show(getApplication(), getApplication().getResources().getString(R.string.text_reset_password_success));
                }, throwable -> ToastUtil.show(getApplication(), throwable.getMessage()));

    }
}
