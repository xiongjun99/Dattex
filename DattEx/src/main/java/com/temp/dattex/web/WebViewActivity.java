package com.temp.dattex.web;

import android.os.Bundle;
import android.text.TextUtils;

import androidx.databinding.library.baseAdapters.BR;

import com.common.framework.basic.BaseActivity;
import com.temp.dattex.R;
import com.temp.dattex.databinding.ActivityWebBinding;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.temp.dattex.web
 * @FileName     : WebViewActivity.java
 * @Author       : chao
 * @Date         : 2020/5/15
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
public class WebViewActivity extends BaseActivity<ActivityWebBinding, WebViewModel> {

    public static final String KEY_PARAM_URL = "KEY_PARAM_URL";
    public static final String KEY_PARAM_TITLE = "KEY_PARAM_TITLE";

    @Override
    public void initParam() {
        String paramUrl = getIntent().getStringExtra(KEY_PARAM_URL);
        String paramTitle = getIntent().getStringExtra(KEY_PARAM_TITLE);
        if (!TextUtils.isEmpty(paramUrl)) {
            viewModel.getLoadUrl().set(paramUrl);
        }
        if (!TextUtils.isEmpty(paramTitle)) {
            viewModel.getTitleText().set(paramTitle);
        }
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_web;
    }

    @Override
    public int initVariableId() {
        return BR.webViewModel;
    }

    @Override
    public void initViewObservable() {

    }
}
