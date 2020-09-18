package com.temp.buda.setting;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.common.framework.basic.BaseViewModel;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.temp.dattex.safe
 * @FileName     : SafeViewModel.java
 * @Author       : chao
 * @Date         : 2020/6/20
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
public class SettingViewModel extends BaseViewModel {
    public ObservableField<String> revise = new ObservableField<>("");

    public ObservableField<String> getRevise() {
        return revise;
    }
    public void setRevise(ObservableField<String> revise) {
        this.revise = revise;
    }
    public SettingViewModel(@NonNull Application application) {
        super(application);
    }
}
