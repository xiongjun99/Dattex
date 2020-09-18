package com.temp.buda.home;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.common.framework.basic.BaseViewModel;
import com.common.framework.click.SingleClick;
import com.temp.buda.Constants;
import com.temp.buda.adapter.HomeFragmentPagerAdapter;
import com.temp.buda.config.AssetsConfigs;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.temp.dattex.home
 * @FileName     : HomeViewModel.java
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
public class HomeViewModel extends BaseViewModel {

    public ObservableField<Boolean> homeState = new ObservableField<>(true);
    public ObservableField<Boolean> tradeState = new ObservableField<>(false);
    public ObservableField<Boolean> marketState = new ObservableField<>(false);
    public ObservableField<Boolean> myState = new ObservableField<>(false);
    public ObservableField<Integer> currentIndex = new ObservableField<>(Constants.INDEX_ZEO);
    public ObservableField<HomeFragmentPagerAdapter> pagerAdapter = new ObservableField<>();
    public ObservableField<Boolean> touch = new ObservableField<>(false);

    public HomeViewModel(@NonNull Application application) {
        super(application);
    }


    @SingleClick
    public void checkBottom(int index) {
        if (index != currentIndex.get()) {
            currentIndex.set(index);
            homeState.set(index == Constants.INDEX_ZEO);
            marketState.set(index == Constants.INDEX_ONE);
            tradeState.set(index == Constants.INDEX_TWO);
            myState.set(index == Constants.INDEX_THREE);
        } else {
            // TODO: 2020/5/16  下拉刷新数据
        }

    }

    @SuppressLint("CheckResult")
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onResume() {
        super.onResume();
//        AssetsConfigs.getInstance().freshCoin();
//        AssetsConfigs.getInstance().freshAssets();
        if (AssetsConfigs.getInstance().getCoinBeanHashMap().size()==0 &&AssetsConfigs.getInstance().getNewAssetsItemBeanMap().size()==0){
            AssetsConfigs.getInstance().freshCoin();
        }
    }

}
