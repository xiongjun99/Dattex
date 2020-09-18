package com.temp.buda.protocol;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.common.framework.basic.BaseViewModel;
import com.exchange.utilslib.ToastUtil;
import com.independ.framework.response.ResponseTransformer;
import com.temp.buda.net.DataService;


public class ProtocolViewModel extends BaseViewModel {

    public ProtocolViewModel(@NonNull Application application) {
        super(application);
    }

    private ObservableField<String> protocol = new ObservableField<>("");

    public ObservableField<String> getProtocol() {
        return protocol;
    }

    public void setProtocol(ObservableField<String> protocol) {
        this.protocol = protocol;
    }

    @SuppressLint("CheckResult")
    @Override
    public void onStart() {
        super.onStart();
        DataService.getInstance().userProtocol().compose(ResponseTransformer.<String>handleResult())
                .subscribe(s -> protocol.set(s), throwable -> ToastUtil.show(getApplication(), throwable.getMessage()));
    }
}
