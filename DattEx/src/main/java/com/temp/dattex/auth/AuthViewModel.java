package com.temp.dattex.auth;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.PopupMenu;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.common.framework.basic.BaseApplication;
import com.common.framework.basic.BaseViewModel;
import com.common.framework.bus.SingleLiveEvent;
import com.common.framework.click.SingleClick;
import com.common.framework.commad.ResponseCommand;
import com.exchange.utilslib.LogUtil;
import com.exchange.utilslib.ToastUtil;
import com.independ.framework.response.BaseResponse;
import com.independ.framework.response.ResponseTransformer;
import com.independ.framework.scheduler.RxScheduler;
import com.temp.dattex.Constants;
import com.temp.dattex.R;
import com.temp.dattex.country.CountryActivity;
import com.temp.dattex.net.DataService;

import io.reactivex.Scheduler;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * @Package: com.temp.dattex.auth
 * @ClassName: AuthViewModel
 * @Description: java类作用描述
 * @Author: 李嘉伦
 * @CreateDate: 2020/5/14 21:51
 * @Email: 86152
 */

public class AuthViewModel extends BaseViewModel implements
        AdapterView.OnItemClickListener,
        PopupWindow.OnDismissListener {


    private static final int REQUEST_COUNTRY = 53;

    /**
     * 名字
     */
    public ObservableField<String> name = new ObservableField<>("");
    /**
     * 身份证号
     */
    public ObservableField<String> id = new ObservableField<>("");
    /**
     * 国家
     */
    public ObservableField<String> country = new ObservableField<>("中国");
    /**
     * 国家类型，true中国，中国以外 false
     */
    public ObservableField<Boolean> countyType = new ObservableField<>(true);
    /**
     * 其他国家的id
     */
    public ObservableField<String> orderCountyId = new ObservableField<>("");
    /**
     * 其他国家的名
     */
    public ObservableField<String> orderCountyName = new ObservableField<>("");
    /**
     * 其他国家的姓
     */
    public ObservableField<String> orderCountrySurname = new ObservableField<>("");
    /**
     * pop按钮状态
     */
    public ObservableField<Boolean> btState = new ObservableField<>(false);

    /**
     * pop 默认选中的Position
     */
    public ObservableField<Integer> pPosition = new ObservableField<>(0);
    //封装一个界面发生改变的观察者
    public UIChangeObservable uc = new UIChangeObservable();


    public static class UIChangeObservable {
        //密码开关观察者
        SingleLiveEvent<Boolean> pop = new SingleLiveEvent<>();
    }


    public AuthViewModel(@NonNull Application application) {
        super(application);
    }


    @SingleClick
    public void popDown() {
        btState.set(!btState.get());
        uc.pop.setValue(btState.get());
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onDismiss() {
        btState.set(false);
    }

    /**
     * 跳转国家
     */
    @SingleClick
    public void countrySelected() {
        startActivity(CountryActivity.class, REQUEST_COUNTRY);
    }

    /**
     * 提交按钮
     */
    @SuppressLint("CheckResult")
    @SingleClick
    public void commit() {
        //提交身份验证
        if (countyType.get()) {
            if (TextUtils.isEmpty(name.get())) {
                ToastUtil.show(getApplication(), getApplication().getResources().getString(R.string.please_input_name));
                return;
            } else if (TextUtils.isEmpty(id.get())) {
                ToastUtil.show(getApplication(), getApplication().getResources().getString(R.string.please_input_id));
                return;
            }
        } else {
            if (TextUtils.isEmpty(orderCountyId.get())) {
                ToastUtil.show(getApplication(), getApplication().getResources().getString(R.string.please_input_Iden_num));
                return;
            } else if (TextUtils.isEmpty(orderCountyName.get())) {
                ToastUtil.show(getApplication(), getApplication().getResources().getString(R.string.please_input_order_name));
                return;
            } else if (TextUtils.isEmpty(orderCountrySurname.get())) {
                ToastUtil.show(getApplication(), getApplication().getResources().getString(R.string.please_input_surname));
                return;
            }
        }
        // TODO: 2020/5/17 身份验证接口
        DataService.getInstance()
                .commitAuth(id.get(), name.get()).
                compose(ResponseTransformer.handleResult())
                .subscribe(o -> {

                }, throwable -> {
                    String message = throwable.getMessage();
                    ToastUtil.show(BaseApplication.getInstance(), message);
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_COUNTRY && data != null) {
            String code = data.getStringExtra(Constants.KEY_COUNTRY_CODE);
            String name = data.getStringExtra(Constants.KEY_COUNTRY_NAME);
            countyType.set(code.equals(Constants.COUNTRY_CODE_CHINA));
            country.set(name);
        }
    }
}
