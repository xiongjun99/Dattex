package com.temp.dattex.home;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.common.framework.basic.AppManager;
import com.common.framework.basic.BaseActivity;
import com.exchange.utilslib.ToastUtil;
import com.independ.framework.client.RetrofitClient;
import com.independ.framework.response.ResponseTransformer;
import com.temp.dattex.BR;
import com.temp.dattex.R;
import com.temp.dattex.adapter.HomeFragmentPagerAdapter;
import com.temp.dattex.databinding.ActivityHomeBinding;
import com.temp.dattex.factory.FragmentFactory;
import com.temp.dattex.net.DataService;
import com.temp.dattex.util.CoinRecordFilerViewModel;
import com.temp.dattex.util.DialogUtil;
import com.temp.dattex.util.UpdateDialogViewModel;
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
    public void initView() {
        super.initView();
        String Many = getIntent().getStringExtra("many");
        if (Many!=null){
            if (Many.equals("0")||Many.equals("1")){
                viewModel.checkBottom(2);
            }
        }
        DataService.getInstance().UpDate().compose(ResponseTransformer.handleResult()).subscribe(
                d -> {
                    if (!d.getAndroid().getVersion().equals(Utils.getVersion(this))){
                        UpdateDialogViewModel updateDialogViewModel = new UpdateDialogViewModel();
                        updateDialogViewModel.setNewVersionInfo(d.getAndroid().getMemo().replace("\\n", "\n"));
                        updateDialogViewModel.setNewVersionName("V"+d.getAndroid().getVersion());
                        updateDialogViewModel.setLinkUrl(d.getAndroid().getDownloadLink());
                        DialogUtil.showUpdateDialog(this,updateDialogViewModel);
                        String[] sourceStrArray = d.getAndroid().getForcedUpdateVer().split(",");
                        for (int i = 0; i < sourceStrArray.length; i++) {
                            if (sourceStrArray[i].equals(Utils.getVersion(this))){
                                System.out.println("------强制更新");
                                updateDialogViewModel.setOpen(true);
                            } else {
                                updateDialogViewModel.setOpen(false);
                                System.out.println("------可以关闭");
                            }
                        }
                    } else {
                        System.out.println("-------最新版本无需更新");
                    }
                }, t -> {
//                    ToastUtil.show(this,"获取版本更新失败...");
                }
        );
    }

    @Override
    public void initParam() {
        super.initParam();
        Bugly.init(getApplicationContext(), "177e35b69c", false);
        HomeFragmentPagerAdapter baseFragmentPagerAdapter = new HomeFragmentPagerAdapter(getSupportFragmentManager());
        viewModel.pagerAdapter.set(baseFragmentPagerAdapter);
    }

}
