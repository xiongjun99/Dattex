package com.temp.buda.login;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.common.framework.basic.AppManager;
import com.common.framework.basic.BaseViewModel;
import com.common.framework.click.SingleClick;
import com.exchange.utilslib.LogUtil;
import com.exchange.utilslib.ToastUtil;
import com.independ.framework.response.ResponseTransformer;
import com.temp.buda.Constants;
import com.temp.buda.R;
import com.temp.buda.bean.LoginBean;
import com.temp.buda.country.CountryActivity;
import com.temp.buda.database.LoginInfo;
import com.temp.buda.net.DataService;
import com.temp.buda.register.RegisterStepOneActivity;
import com.temp.buda.resetpwd.ResetPasswordActivity;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.temp.dattex.login
 * @FileName     : LoginViewModel.java
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

public class LoginViewModel extends BaseViewModel {

    private int requestCountry = 22;

    private ObservableField<String> userName = new ObservableField<String>("");
    private ObservableField<String> passWord = new ObservableField<String>("");
    private ObservableField<String> countryCode = new ObservableField<String>("+86");
    private ObservableField<String> countryName = new ObservableField<String>("中国");

    private String nextPage;

    public String getNextPage() {
        return nextPage;
    }

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }

    public ObservableField<String> getUserName() {
        return userName;
    }

    public void setUserName(ObservableField<String> userName) {
        this.userName = userName;
    }

    public ObservableField<String> getPassWord() {
        return passWord;
    }

    public void setPassWord(ObservableField<String> passWord) {
        this.passWord = passWord;
    }

    public ObservableField<String> getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(ObservableField<String> countryCode) {
        this.countryCode = countryCode;
    }

    public ObservableField<String> getCountryName() {
        return countryName;
    }

    public void setCountryName(ObservableField<String> countryName) {
        this.countryName = countryName;
    }

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    @SingleClick
    public void chooseCountryCode() {
        LogUtil.e("chooseCountryCode");
        startActivity(CountryActivity.class, requestCountry);
    }


    @SingleClick
    public void loginCancel() {
        LogUtil.e("loginCancel");
        finish();
    }


    @SingleClick
    public void resetPassWord() {
        LogUtil.e("resetPassWord");
        startActivity(ResetPasswordActivity.class);
    }

    @SingleClick
    public void registeredAccount() {
        LogUtil.e("registeredAccount");
        startActivity(RegisterStepOneActivity.class);
    }

    @SuppressLint("CheckResult")
    @SingleClick
    public void login(View view) {
        if (TextUtils.isEmpty(userName.get())) {
            ToastUtil.show(getApplication(), getApplication().getResources().getText(R.string.text_input_phone_number));
        } else if (TextUtils.isEmpty(passWord.get())) {
            ToastUtil.show(getApplication(), getApplication().getResources().getText(R.string.text_input_password));
        } else {
            InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
            DataService.getInstance().userLogin(userName.get(), passWord.get(), countryCode.get())
                    .compose(ResponseTransformer.<LoginBean>handleResult())
                    .subscribe(loginBean -> {
                        //缓存登录信息到sp   后面处理下网络库同步的问题
                        LoginInfo.sign(
                                passWord.get(),
                                loginBean.getAccount(),
                                loginBean.getToken(),
                                loginBean.getMemberId(),
                                loginBean.getRecode(),loginBean.getIsCertification());
                        if (TextUtils.isEmpty(nextPage)) {
                            finishFragmentResult();
                        } else {
                            //如果有指定下一个页面直接进入下一个页面
                            Class<?> clazz = Class.forName(nextPage);
                            Activity peek = AppManager.getActivityStack().peek();
                            Intent intent = new Intent(peek, clazz);
                            peek.startActivity(intent);
                        }
                        finish();
                    }, throwable -> {
                        ToastUtil.show(getApplication(), throwable.getMessage());
                    });
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == requestCountry && data != null) {
            getCountryCode().set(data.getStringExtra(Constants.KEY_COUNTRY_CODE));
            getCountryName().set(data.getStringExtra(Constants.KEY_COUNTRY_NAME));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
