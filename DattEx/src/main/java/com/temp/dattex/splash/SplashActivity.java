package com.temp.dattex.splash;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.framework.basic.AppManager;
import com.common.framework.basic.BaseActivity;
import com.exchange.utilslib.LooperUtil;
import com.exchange.utilslib.ToastUtil;
import com.independ.framework.client.RetrofitClient;
import com.independ.framework.response.ResponseTransformer;
import com.temp.dattex.BR;
import com.temp.dattex.R;
import com.temp.dattex.bean.UpdateBean;
import com.temp.dattex.config.AssetsConfigs;
import com.temp.dattex.databinding.ActivitySplashBinding;
import com.temp.dattex.home.HomeActivity;
import com.temp.dattex.net.ApiAddress;
import com.temp.dattex.net.DataService;
import com.temp.dattex.util.CoinRecordFilerViewModel;
import com.temp.dattex.util.CoverDialogViewModel;
import com.temp.dattex.util.DialogUtil;
import com.temp.dattex.util.UpdateDialogViewModel;
import com.temp.dattex.util.Utils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.temp.dattex.splash
 * @FileName     : SplashActivity.java
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
public class SplashActivity extends BaseActivity<ActivitySplashBinding, SplashViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_splash;
    }

    @Override
    public int initVariableId() {
        return BR.splashViewModel;
    }

    @Override
    public void initViewObservable() {
    }

    @Override
    public void initView() {
        super.initView();
        AssetsConfigs.getInstance().freshCoin();
    }
}
