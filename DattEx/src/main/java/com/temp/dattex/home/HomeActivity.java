package com.temp.dattex.home;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.common.framework.basic.BaseActivity;
import com.independ.framework.client.RetrofitClient;
import com.temp.dattex.BR;
import com.temp.dattex.R;
import com.temp.dattex.adapter.HomeFragmentPagerAdapter;
import com.temp.dattex.databinding.ActivityHomeBinding;
import com.temp.dattex.util.Utils;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.crashreport.CrashReport;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.temp.dattex.home
 * @FileName     : HomeActivity.java
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
@SuppressLint("Registered")
public class HomeActivity extends BaseActivity<ActivityHomeBinding, HomeViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_home;
    }

    @Override
    public int initVariableId() {
        return BR.homeViewModel;
    }

    @Override
    public void initViewObservable() {

    }

    @Override
    public void initParam() {
        super.initParam();
        Bugly.init(getApplicationContext(), "177e35b69c", false);
        HomeFragmentPagerAdapter baseFragmentPagerAdapter = new HomeFragmentPagerAdapter(getSupportFragmentManager());
        viewModel.pagerAdapter.set(baseFragmentPagerAdapter);
    }
}
