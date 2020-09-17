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
import com.temp.dattex.database.LoginInfo;
import com.temp.dattex.net.DataService;


public class ResetPasswordViewModel extends BaseViewModel {
    public ResetPasswordViewModel(@NonNull Application application) {
        super(application);
    }
    private ObservableField<String> oldPassword = new ObservableField<>("");
    private ObservableField<String> againPassword = new ObservableField<>("");
    private ObservableField<String> password = new ObservableField<>("");
    public ObservableField<String> getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(ObservableField<String> oldPassword) {
        this.oldPassword = oldPassword;
    }
    public ObservableField<String> getAgainPassword() {
        return againPassword;
    }

    public void setAgainPassword(ObservableField<String> againPassword) {
        this.againPassword = againPassword;
    }

    public ObservableField<String> getPassword() {
        return password;
    }

    public void setPassword(ObservableField<String> password) {
        this.password = password;
    }

    @SuppressLint("CheckResult")
    @SingleClick
    public void resetDone() {
        if (!oldPassword.get().equals(LoginInfo.getPassWord())){
            ToastUtil.show(getApplication(),"请输入正确的密码");
            return;
        }
        if (!password.get().equals(againPassword.get())){
            ToastUtil.show(getApplication(),"请输入相同的新密码");
            return;
        }
        DataService.getInstance().resetPassword(oldPassword.get(),againPassword.get()).
                compose(ResponseTransformer.handleResult())
                .subscribe(s -> {
                    finish();
                    ToastUtil.show(getApplication(), getApplication().getResources().getString(R.string.text_reset_password_success));
                }, throwable -> ToastUtil.show(getApplication(), throwable.getMessage()));
    }
}
