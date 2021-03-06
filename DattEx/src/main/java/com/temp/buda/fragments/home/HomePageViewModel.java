package com.temp.buda.fragments.home;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.common.framework.basic.BaseApplication;
import com.common.framework.basic.BaseViewModel;
import com.common.framework.click.SingleClick;
import com.exchange.utilslib.ToastUtil;
import com.independ.framework.response.ResponseTransformer;
import com.temp.buda.Constants;
import com.temp.buda.R;
import com.temp.buda.adapter.NewMarketRecyclerAdapter;
import com.temp.buda.bean.BannerItemBean;
import com.temp.buda.bean.MarketListBean;
import com.temp.buda.kline.KlineActivity;
import com.temp.buda.net.ApiAddress;
import com.temp.buda.net.DataService;
import com.temp.buda.notice.NoticeActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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
    private Timer timer;
    public ObservableField<Integer> checkRank = new ObservableField<>(1);

    private ObservableField<NewMarketRecyclerAdapter> adapter = new ObservableField<>(new NewMarketRecyclerAdapter(R.layout.item_newmarket_layout,getApplication(), null));

    public ObservableField<NewMarketRecyclerAdapter> getAdapter() {
        return adapter;
    }

    public void setAdapter(ObservableField<NewMarketRecyclerAdapter> adapter) {
        this.adapter = adapter;
    }

    public ObservableField<List<MarketListBean>> list = new ObservableField<>(new ArrayList<MarketListBean>());

    public ObservableField<List<MarketListBean>> getList() {
        return list;
    }

    public void setList(ObservableField<List<MarketListBean>> list) {
        this.list = list;
    }

    public ObservableField<List<BannerItemBean.RowsBean>> urls = new ObservableField<>();
    public ObservableField<String> bannerText = new ObservableField<>("");
    public ObservableField<String> bannerTextcN = new ObservableField<>("");

    public ObservableField<String> getBannerTextcN() {
        return bannerTextcN;
    }

    public void setBannerTextcN(ObservableField<String> bannerTextcN) {
        this.bannerTextcN = bannerTextcN;
    }

    public ObservableField<String> getBannerText() {
        return bannerText;
    }

    public void setBannerText(ObservableField<String> bannerText) {
        this.bannerText = bannerText;
    }

    public HomePageViewModel(@NonNull Application application) {
        super(application);
    }


    @SingleClick
    public void checkUpRank(View view) {
        checkRank.set(1);
//        getMarketList(1);
    }

    @SingleClick
    public void checkDownRank(View view) {
        checkRank.set(2);
//        getMarketList(2);
    }

    @SingleClick
    public void checkDealRank(View view) {
        checkRank.set(3);
//        getMarketList(3);
    }


    public void openWebView(String url) {
        startActivity(NoticeActivity.class);
//        ToastUtil.show(getApplication(),"暂未开通");
//        Bundle bundle = new Bundle();
//        bundle.putString(WebViewActivity.KEY_PARAM_URL, url);
//        startActivity(WebViewActivity.class, bundle);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        adapter.get().setOnItemClickListener((adapter, view, position) -> {
                Bundle bundle = new Bundle();
                bundle.putString(Constants.REQUEST_KEY_COIN_ID,  getAdapter().get().getData().get(position).getCoinId());
                startActivity(KlineActivity.class, bundle);
        });
    }

    @SuppressLint("CheckResult")
    private void getMarketList(int pos) {
        DataService.getInstance().getMarketList().compose(ResponseTransformer.<List<MarketListBean>>handleResult()).subscribe(
                l -> {
                    list.set(l);
                    if (pos ==1) {
                    Collections.sort(list.get(), new Comparator<MarketListBean>() {
                        public int compare(MarketListBean arg0, MarketListBean arg1) {
                            if (Float.valueOf(arg0.getChanges())>Float.valueOf(arg1.getChanges())){
                                return -1;
                            }else if(Float.valueOf(arg0.getChanges())<Float.valueOf(arg1.getChanges())){
                                return 1;
                            }else{
                                return 0;
                            }
                        }
                    });
                    for (MarketListBean p : list.get()) {
                    }
            } else if (pos == 2) {
                        //直接在这里添加我们的排序规则
                        Collections.sort(list.get(), new Comparator<MarketListBean>() {
                            public int compare(MarketListBean arg0, MarketListBean arg1) {
                                return arg0.getChanges().compareTo(arg1.getChanges());
                            }
                 });
            for (MarketListBean p : list.get()) {
             }
            } else if (pos ==3){
                        Collections.sort(list.get(), new Comparator<MarketListBean>() {
                            public int compare(MarketListBean arg0, MarketListBean arg1) {
                                if (Float.valueOf(arg0.getDealCount())>Float.valueOf(arg1.getDealCount())){
                                    return -1;
                                }else if(Float.valueOf(arg0.getDealCount())<Float.valueOf(arg1.getDealCount())){
                                    return 1;
                                }else{
                                    return 0;
                                }
                            }
                        });
                        for (MarketListBean p : list.get()) {
                        }
            }
            if (adapter.get().getData()==null||adapter.get().getData().size()==0){
            adapter.get().addData(list.get());
            }else {
            adapter.get().setNewData(list.get());
            }
                    adapter.get().setPos(pos);
           }, t -> {
                    ToastUtil.show(BaseApplication.getInstance(), t.getMessage());}
        );
    }

    @SuppressLint("CheckResult")
    @Override
    public void onStart() {
        super.onStart();
//        List<SymbolConfigBean> symbols = SymbolConfigs.getInstance().getSymbols();
//        List<SymbolConfigBean> data = getAdapter().get().getData();
//        if (null == data || data.size() == 0) {
//            getAdapter().get().addData(symbols);
//        }
        getMarketList(checkRank.get());
        DataService.getInstance().appBanner().compose(ResponseTransformer.handleResult()).subscribe(
                b -> {
                    if(null != b && b.getRows().size() != 0){
//                        urls.get().clear();
                    urls.set(b.getRows());
                    }
                }, t -> {
                    ToastUtil.show(getApplication(),t.getMessage());
                });
    }
    @Override
    public void onResume() {
        super.onResume();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                getMarketList(checkRank.get());
            }
        }, 1000, 1000);
    }
    @Override
    public void onPause() {
        super.onPause();
        if (null != timer) {
            timer.cancel();
            timer = null;
        }
    }
}
