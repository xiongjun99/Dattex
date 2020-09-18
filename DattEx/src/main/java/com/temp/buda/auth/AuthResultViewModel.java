package com.temp.buda.auth;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.common.framework.basic.BaseViewModel;

/**
 * @Package: com.temp.dattex.auth
 * @ClassName: AuthResultViewModel
 * @Description: java类作用描述
 * @Author: 李嘉伦
 * @CreateDate: 2020/5/14 23:45
 * @Email: 86152
 */
public class AuthResultViewModel extends BaseViewModel {

    public ObservableField<String> national = new ObservableField<>("");
    public ObservableField<String> name = new ObservableField<>("");
    public ObservableField<String> type = new ObservableField<>("身份证");
    public ObservableField<String> num = new ObservableField<>("");

    public AuthResultViewModel(@NonNull Application application) {
        super(application);
    }


}
