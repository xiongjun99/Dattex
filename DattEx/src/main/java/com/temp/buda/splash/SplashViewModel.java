package com.temp.buda.splash;

import android.app.Application;

import androidx.annotation.NonNull;

import com.common.framework.basic.BaseViewModel;
import com.exchange.utilslib.LooperUtil;
import com.temp.buda.home.HomeActivity;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.temp.dattex.splash
 * @FileName     : SplashViewModel.java
 * @Author       : chao
 * @Date         : 2020/5/20
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
public class SplashViewModel extends BaseViewModel {

    private String colorString = "红色";

    public String getColorString() {
        return colorString;
    }

    public void setColorString(String colorString) {
        this.colorString = colorString;
    }

    private Runnable runnable = () -> {
        LooperUtil.getHandler().removeCallbacksAndMessages(null);
        startActivity(HomeActivity.class);
        finish();
    };

    public SplashViewModel(@NonNull Application application) {
        super(application);
    }


    @Override
    public void onCreate() {
        super.onCreate();
//        LooperUtil.getHandler().postDelayed(runnable, 3000);
    }

    @Override
    public void onStart() {
        super.onStart();
        LooperUtil.getHandler().postDelayed(runnable, 3000);
    }

    @Override
    public void onStop() {
        super.onStop();
        LooperUtil.getHandler().removeCallbacksAndMessages(null);
    }
}
