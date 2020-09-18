package com.temp.buda.register;

import android.annotation.SuppressLint;
import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.common.framework.basic.BaseViewModel;
import com.common.framework.click.SingleClick;
import com.exchange.utilslib.ToastUtil;
import com.independ.framework.response.ResponseTransformer;
import com.temp.buda.R;
import com.temp.buda.net.DataService;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.temp.dattex.register
 * @FileName     : RegisterStepTwoModel.java
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

public class RegisterStepTwoViewModel extends BaseViewModel {

    private ObservableField<String> passWord = new ObservableField<>("");
    private ObservableField<String> passWordRepeat = new ObservableField<>("");

    private ObservableField<String> userName = new ObservableField<>("");
    private ObservableField<String> phoneCode = new ObservableField<>("");
    private ObservableField<String> inviteCode = new ObservableField<>("");
    private ObservableField<String> countryCode = new ObservableField<>("");

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

    public ObservableField<String> getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(ObservableField<String> countryCode) {
        this.countryCode = countryCode;
    }

    public ObservableField<String> getPassWord() {
        return passWord;
    }

    public void setPassWord(ObservableField<String> passWord) {
        this.passWord = passWord;
    }

    public ObservableField<String> getPassWordRepeat() {
        return passWordRepeat;
    }

    public void setPassWordRepeat(ObservableField<String> passWordRepeat) {
        this.passWordRepeat = passWordRepeat;
    }

    public RegisterStepTwoViewModel(@NonNull Application application) {
        super(application);
    }


    @SuppressLint("CheckResult")
    @SingleClick
    public void nextStep() {
        if (TextUtils.isEmpty(passWord.get())) {
            ToastUtil.show(getApplication(), getApplication().getResources().getString(R.string.text_password_limit));
        } else if (TextUtils.isEmpty(passWordRepeat.get())) {
            ToastUtil.show(getApplication(), getApplication().getResources().getString(R.string.text_input_pass_word_repeat));
        } else {
            DataService.getInstance().register(userName.get(), phoneCode.get(), inviteCode.get(), countryCode.get(), passWord.get(), passWordRepeat.get())
                    .compose(ResponseTransformer.<Object>handleResult())
                    .subscribe(o -> {
                        ToastUtil.show(getApplication(), getApplication().getResources().getString(R.string.text_regist_success));
                        getUC().getSetResultEvent().postValue(null);
                        finish();
                    }, throwable -> {
                        ToastUtil.show(getApplication(), throwable.getMessage());
                    });

        }
    }
}
