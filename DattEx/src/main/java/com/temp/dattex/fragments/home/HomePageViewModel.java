package com.temp.dattex.fragments.home;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.common.framework.basic.BaseApplication;
import com.common.framework.basic.BaseViewModel;
import com.common.framework.click.SingleClick;
import com.exchange.utilslib.ToastUtil;
import com.independ.framework.response.ResponseTransformer;
import com.temp.dattex.Constants;
import com.temp.dattex.R;
import com.temp.dattex.adapter.MarketRecyclerAdapter;
import com.temp.dattex.bean.BannerItemBean;
import com.temp.dattex.bean.SymbolConfigBean;
import com.temp.dattex.config.SymbolConfigs;
import com.temp.dattex.kline.KlineActivity;
import com.temp.dattex.net.ApiService;
import com.temp.dattex.net.DataService;
import com.temp.dattex.web.WebViewActivity;
import com.tencent.bugly.crashreport.CrashReport;

import java.util.ArrayList;
import java.util.List;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.temp.dattex.fragments.home
 * @FileName     : HomePageViewModel.java
 * @Author       : chao
 * @Date         : 2020/5/18
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
public class HomePageViewModel extends BaseViewModel {

    private ObservableField<MarketRecyclerAdapter> adapter = new ObservableField<>(new MarketRecyclerAdapter(R.layout.item_market_layout, null));

    public ObservableField<MarketRecyclerAdapter> getAdapter() {
        return adapter;
    }

    public ObservableField<List<String>> urls = new ObservableField<>(new ArrayList<String>());


    public void setAdapter(ObservableField<MarketRecyclerAdapter> adapter) {
        this.adapter = adapter;
    }

    public HomePageViewModel(@NonNull Application application) {
        super(application);
    }


    @SingleClick
    public void checkUpRank(View view) {
    }

    @SingleClick
    public void checkDownRank(View view) {
    }

    @SingleClick
    public void checkDealRank(View view) {
    }


    public void openWebView(String url) {
        Bundle bundle = new Bundle();
        bundle.putString(WebViewActivity.KEY_PARAM_URL, url);
        startActivity(WebViewActivity.class, bundle);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        adapter.get().setOnItemClickListener((adapter, view, position) -> {
            if(adapter instanceof MarketRecyclerAdapter){
                Bundle bundle = new Bundle();
                bundle.putString(Constants.KEY_LEFT_COIN, ((SymbolConfigBean)adapter.getData().get(position)).getCoinSymbol());
                bundle.putString(Constants.KEY_RIGHT_COIN, ((SymbolConfigBean)adapter.getData().get(position)).getBaseSymbol());
                startActivity(KlineActivity.class, bundle);
            }
        });
    }

    @SuppressLint("CheckResult")
    @Override
    public void onStart() {
        super.onStart();
        List<SymbolConfigBean> symbols = SymbolConfigs.getInstance().getSymbols();
        List<SymbolConfigBean> data = getAdapter().get().getData();
        if (null == data || data.size() == 0) {
            getAdapter().get().addData(symbols);
        }
        DataService.getInstance().appBanner().compose(ResponseTransformer.handleResult()).subscribe(
                b -> {
                    if(null != b && b.size() != 0){
                        urls.get().clear();
                        for (BannerItemBean bannerItemBean : b) {
                            if (bannerItemBean.isEnable()) {
                                urls.get().add(bannerItemBean.getUrl());
                            }
                        }
                    }
                    urls.notifyChange();
                }, t -> {
                });
    }
}
