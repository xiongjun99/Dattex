package com.temp.buda.home;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.common.framework.basic.BaseActivity;
import com.independ.framework.response.ResponseTransformer;
import com.temp.buda.BR;
import com.temp.buda.R;
import com.temp.buda.adapter.HomeFragmentPagerAdapter;
import com.temp.buda.databinding.ActivityHomeBinding;
import com.temp.buda.net.DataService;
import com.temp.buda.util.DialogUtil;
import com.temp.buda.util.UpdateDialogViewModel;
import com.temp.buda.util.Utils;
import com.tencent.bugly.Bugly;

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

                    if (Integer.valueOf(d.getAndroid().getVersion().replace(".",""))>Integer.valueOf(Utils.getVersion(this).replace(".",""))){
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
                        System.out.println("-----------最新版本无需更新");
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
//        Utils.titlebar(this);
    }
}
