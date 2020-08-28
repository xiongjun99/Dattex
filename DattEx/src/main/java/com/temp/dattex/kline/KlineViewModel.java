package com.temp.dattex.kline;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.common.framework.basic.BaseApplication;
import com.common.framework.basic.BaseViewModel;
import com.common.framework.click.SingleClick;
import com.exchange.utilslib.ToastUtil;
import com.google.gson.Gson;
import com.icechao.klinelib.adapter.KLineChartAdapter;
import com.icechao.klinelib.formatter.IDateTimeFormatter;
import com.icechao.klinelib.formatter.IValueFormatter;
import com.icechao.klinelib.formatter.ValueFormatter;
import com.icechao.klinelib.utils.DateUtil;
import com.independ.framework.response.ResponseTransformer;
import com.temp.dattex.R;
import com.temp.dattex.adapter.Dealadapter;
import com.temp.dattex.adapter.DethAdapter;
import com.temp.dattex.adapter.NewMarketRecyclerAdapter;
import com.temp.dattex.bean.DealItemBean;
import com.temp.dattex.bean.DethBean;
import com.temp.dattex.bean.KlineDataBean;
import com.temp.dattex.bean.MarketListBean;
import com.temp.dattex.bean.SymbolConfigBean;
import com.temp.dattex.bean.TradeDepthBean;
import com.temp.dattex.config.SymbolConfigs;
import com.temp.dattex.home.HomeActivity;
import com.temp.dattex.net.DataService;
import com.temp.dattex.net.WebSocket;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.temp.dattex.kline
 * @FileName     : KlineViewModel.java
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

public class KlineViewModel extends BaseViewModel implements WebSocket.SocketListener {
    public  Gson gson = new Gson();
    public IValueFormatter valueFormatter = new ValueFormatter() {
        @Override
        public String format(float value) {
            if (null != symbolConfig) {
                return String.format("%.0" + symbolConfig.getBaseCoinScale() + "f", value);
            }
            return super.format(value);
        }
    };

    public IValueFormatter volValueFormatter = new ValueFormatter() {
        @Override
        public String format(float value) {
            if (null != symbolConfig) {
                return String.format("%.0" + symbolConfig.getCoinScale() + "f", value);
            }
            return super.format(value);
        }
    };

    private String period = "1min";
    //     1min, 5min, 15min, 30min, 60min, 4hour, 1day, 1mon, 1week, 1year
    public IDateTimeFormatter dateTimeFormatter = new IDateTimeFormatter() {
        @Override
        public String format(Date date) {
            switch (period) {
                case "1min":
                case "5min":
                case "15min":
                case "30min":
                    return DateUtil.HHMMTimeFormat.format(date);
                case "60min":
                case "1day":
                case "4hour":
                    return DateUtil.MMddHHmmTimeFormat.format(date);
                default:
                    return DateUtil.yyyyMMddFormat.format(date);
            }
        }
    };

    private Timer timer;
    private SymbolConfigBean symbolConfig;

    public KlineViewModel(@NonNull Application application) {
        super(application);
    }

    private ObservableField<String> titleText = new ObservableField<>("");
    private ObservableField<String> realTimePrice = new ObservableField<>("");
    private ObservableField<String> realPriceCny = new ObservableField<>("");
    private ObservableField<Float> change = new ObservableField<>(0f);
    private ObservableField<String> high = new ObservableField<>("");
    private ObservableField<String> low = new ObservableField<>("");
    private ObservableField<String> vol = new ObservableField<>("");
    private ObservableField<String> leftCoin = new ObservableField<>("");
    private ObservableField<String> rightCoin = new ObservableField<>("");
    public ObservableField<List<DethBean>> dethBeanList = new ObservableField<>(new ArrayList<DethBean>());
    public ObservableField<DethAdapter> Depthadapter = new ObservableField<>(new DethAdapter(R.layout.item_deth_layout,getApplication(), null));
    public ObservableField<List<DealItemBean>> dealBeanList = new ObservableField<>(new ArrayList<DealItemBean>());

    public ObservableField<Dealadapter> Dealadapter = new ObservableField<>(new Dealadapter(R.layout.item_deal_layout,getApplication(), null));
    public KLineChartAdapter<KlineDataBean.DataBean> adapter = new KLineChartAdapter<>();
    private ObservableField<BottomNav> bottomNav = new ObservableField<>(BottomNav.DEPTH);

    public ObservableField<Dealadapter> getDealadapter() {
        return Dealadapter;
    }

    public void setDealadapter(ObservableField<Dealadapter> dealadapter) {
        Dealadapter = dealadapter;
    }

    public ObservableField<DethAdapter> getDepthadapter() {
        return Depthadapter;
    }

    public void setDepthadapter(ObservableField<DethAdapter> depthadapter) {
        Depthadapter = depthadapter;
    }
    public ObservableField<BottomNav> getBottomNav() {
        return bottomNav;
    }

    public void setBottomNav(ObservableField<BottomNav> bottomNav) {
        this.bottomNav = bottomNav;
    }

    public ObservableField<String> getTitleText() {
        return titleText;
    }

    public void setTitleText(ObservableField<String> titleText) {
        this.titleText = titleText;
    }

    public ObservableField<String> getRealTimePrice() {
        return realTimePrice;
    }

    public void setRealTimePrice(ObservableField<String> realTimePrice) {
        this.realTimePrice = realTimePrice;
    }

    public ObservableField<String> getRealPriceCny() {
        return realPriceCny;
    }

    public void setRealPriceCny(ObservableField<String> realPriceCny) {
        this.realPriceCny = realPriceCny;
    }

    public ObservableField<Float> getChange() {
        return change;
    }

    public void setChange(ObservableField<Float> change) {
        this.change = change;
    }

    public ObservableField<String> getHigh() {
        return high;
    }

    public void setHigh(ObservableField<String> high) {
        this.high = high;
    }

    public ObservableField<String> getLow() {
        return low;
    }

    public void setLow(ObservableField<String> low) {
        this.low = low;
    }

    public ObservableField<String> getVol() {
        return vol;
    }

    public void setVol(ObservableField<String> vol) {
        this.vol = vol;
    }

    public ObservableField<String> getLeftCoin() {
        return leftCoin;
    }

    public void setLeftCoin(ObservableField<String> leftCoin) {
        this.leftCoin = leftCoin;
    }

    public ObservableField<String> getRightCoin() {
        return rightCoin;
    }

    public void setRightCoin(ObservableField<String> rightCoin) {
        this.rightCoin = rightCoin;
    }

    @SingleClick
    public void oneMinKline() {
        if (!period.equals("1min")) {
            period = "1min";
            timer.cancel();
            timer = null;
            loadKlineData();
        }
    }

    @SingleClick
    public void fiveMinKline() {
        if (!period.equals("5min")) {
            period = "5min";
            timer.cancel();
            timer = null;
            loadKlineData();
        }
    }

    @SingleClick
    public void oneHourKline() {
        if (!period.equals("60min")) {
            period = "60min";
            timer.cancel();
            timer = null;
            loadKlineData();
        }
    }

    @SingleClick
    public void oneDayKline() {
        if (!period.equals("1day")) {
            period = "1day";
            timer.cancel();
            timer = null;
            loadKlineData();
        }
    }

    @SingleClick
    public void showMoreSetting() {

    }
    @SingleClick
    public void bottomNavSet(BottomNav bottomNav) {
        getBottomNav().set(bottomNav);
        switch (bottomNav){
            case DEAL://成交

                break;
            case DEPTH://深度
//                DataService.getInstance().getDepth(5).compose(ResponseTransformer.<List<TradeDepthBean>>handleResult()).subscribe(
//                        list -> {
//
//                        }, t -> {
//                            ToastUtil.show(BaseApplication.getInstance(), t.getMessage());
//                        }
//                );
                break;
            case INTRO://简介

                break;
        }
    }

    public boolean checkBottomNav(BottomNav bottomNav) {
        return getBottomNav().get().value == bottomNav.value;
    }

    @Override
    public void open() {
    }

    @Override
    public void onMessage(String message) {
        if (!TextUtils.isEmpty(message)) {
//            SocketDataBean socketDataBean = gson.fromJson(message, SocketDataBean.class);
            try {
                JSONObject json_data = new JSONObject(message);
                JSONObject obj =json_data.getJSONObject("tick");
                KlineDataBean.DataBean data = new KlineDataBean.DataBean();
                data.setCount(Integer.valueOf(obj.optString("count")));
                data.setAmount(Float.valueOf(obj.optString("amount")));
                data.setId(Long.valueOf(obj.optString("id")));
                data.setPair(obj.optString("pair"));
                data.setChanges(Float.valueOf(obj.optString("changes")));
                data.setClose(Float.valueOf(obj.optString("close")));
                data.setLow(Float.valueOf(obj.optString("low")));
                data.setHigh(Float.valueOf(obj.optString("high")));
                data.setVol(Float.valueOf(obj.optString("vol")));
                Message msg = new Message();
                Bundle b = new Bundle();
                b.putSerializable("data",data);
                msg.setData(b);
                msg.obj = data;
                msg.what = 1;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        /**
                         * 延时执行的代码
                         */
                        handler.handleMessage(msg);
                    }
                },5000); // 延时1秒
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClosed() {
    }

    @Override
    public void onFailure() {
    }

    public enum BottomNav {
        DEPTH(0), DEAL(1), INTRO(2);
        private int value;

        BottomNav(int value) {
            this.value = value;
        }
    }
   Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            // 如果发送的消息来自子线程
            if(msg.what == 1){
                // 将读取的内容追加显示在文本框中
                Bundle b = msg.getData();
                KlineDataBean.DataBean data= (KlineDataBean.DataBean) b.getSerializable("data");
//                showHeaderData1(data);
//                updateKline1(data);
            }
        }

    };
    @Override
    public void onStart() {
        super.onStart();
        symbolConfig = SymbolConfigs.getInstance().getSymbol(leftCoin.get().toUpperCase() + "/" + rightCoin.get().toUpperCase());
        loadKlineData();
    }

    @SuppressLint("CheckResult")
    private void loadKlineData() {
        String symbol = leftCoin.get() + rightCoin.get();
        timer = new Timer();
        initKlineData(symbol);
//        WebSocket.getInstance().init(ApiAddress.WEB_SOCKET_URL);
//        WebSocket.getInstance().setSocketListener(this);
        DataService.getInstance().klineHistory(symbol.toLowerCase(), 1, "1day").compose(ResponseTransformer.<KlineDataBean>handleResult()).subscribe(
                this::showHeaderData, t -> {
                }
        );
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (bottomNav.get().value == 0) {
                    getDepth();
                }else if (bottomNav.get().value == 1){
                    getDealList();
                }

                DataService.getInstance().klineHistory(symbol.toLowerCase(), 1, period).compose(ResponseTransformer.<KlineDataBean>handleResult()).subscribe(
                        k ->
                                updateKline(k), t -> {
                            System.out.println("--------------"+t.getMessage());
                        }
                );
                DataService.getInstance().klineHistory(symbol.toLowerCase(), 1, "1day").compose(ResponseTransformer.<KlineDataBean>handleResult()).subscribe(
                        k -> showHeaderData(k), t -> {
                            System.out.println("--------------"+t.getMessage());
                        }
                );
            }
        }, 1000, 1000);
    }
    @SuppressLint("CheckResult")
    private void getDealList() {
        DataService.getInstance().getDealList(leftCoin.get().toUpperCase() + "/" +
                rightCoin.get().toUpperCase()).compose(ResponseTransformer.handleResult())
                .subscribe(data->
                {
                    if (dealBeanList.get()!=null&&dealBeanList.get().size()>0){
                        dealBeanList.get().clear();
                    }
                    dealBeanList.set(data);

                   if (Dealadapter.get().getData().size()>9) {
                       Dealadapter.get().removeAt(0);
                       Dealadapter.get().addData(dealBeanList.get());
                   }else {
                       Dealadapter.get().addData(dealBeanList.get());
                   }
                },t->{

                });
    }

    @SuppressLint("CheckResult")
    private void getDepth() {
        DataService.getInstance().getDepth(5).compose(ResponseTransformer.<List<TradeDepthBean>>handleResult()).subscribe(
                list -> {
                    if (dethBeanList.get()!=null&&dethBeanList.get().size()>0){
                        dethBeanList.get().clear();
                    }
                    for (int i = 0; i < list.get(0).getAsks().size(); i++) {
                        DethBean data = new DethBean();
                        data.setNum(String.valueOf(list.get(0).getAsks().get(i).keySet()).replaceAll("\\[|\\]", ""));
                        data.setPrice(String.valueOf(list.get(0).getAsks().get(i).values()).replaceAll("\\[|\\]", ""));
                        data.setName("asks");
                        dethBeanList.get().add(data);
                    }
                    for (int j = 0; j < list.get(0).getBids().size(); j++) {
                        DethBean data = new DethBean();
                        data.setNum(String.valueOf(list.get(0).getBids().get(j).keySet()).replaceAll("\\[|\\]", ""));
                        data.setPrice(String.valueOf(list.get(0).getBids().get(j).values()).replaceAll("\\[|\\]", ""));
                        data.setName("bids");
                        dethBeanList.get().add(data);
                    }
                    if (Depthadapter.get().getData()==null||Depthadapter.get().getData().size()==0){
                        Depthadapter.get().addData(dethBeanList.get());
                    }else {
                        Depthadapter.get().setNewData(dethBeanList.get());
                        Depthadapter.get().notifyDataSetChanged();
                    }
                }, t -> {
                    ToastUtil.show(BaseApplication.getInstance(), t.getMessage());
                });
    }
    @SuppressLint("CheckResult")
    private void initKlineData(String symbol) {
        DataService.getInstance().klineHistory(symbol.toLowerCase(), 300, period).compose(ResponseTransformer.<KlineDataBean>handleResult()).subscribe(
                k -> {
                    List<KlineDataBean.DataBean> data = k.getData();
                    if (null != data) {
                        Collections.reverse(data);
                        adapter.resetData(data, true);
                    }
                }, t -> {
                    int a = 1;
                }
        );
    }
//    private void updateKline1(KlineDataBean.DataBean data) {
//        List<KlineDataBean.DataBean> kk = new ArrayList<>();
////        dataBean.setVol(k.getVol());
////        dataBean.setHigh(k.getHigh());
////        dataBean.setLow(k.getLow());
////        dataBean.setId(k.getId());
////        dataBean.setOpen(k.getOpen());
////        dataBean.setClose(k.getClose());
////        dataBean.setCount(k.getCount());
//          kk.add(data);
//        if (null != kk && kk.size() >= 1) {
//            for (int i = 0; i < kk.size(); i++) {
//                System.out.println("-----akk"+kk.get(i).getAmount());
//            }
//
//            KlineDataBean.DataBean klineItemBean = kk.get(0);
//            int index = adapter.getCount() - 1;
//            KlineDataBean.DataBean lastShow = adapter.getDatas().get(index);
//            if (lastShow.getDate().equals(klineItemBean.getDate())) {
//                System.out.println("-----eeeeee"+lastShow.getAmount());
//                adapter.changeItem(index, klineItemBean);
//            } else {
//                System.out.println("-----fffff"+lastShow.getAmount());
//                adapter.addLast(klineItemBean);
//            }
//        }
//    }
    private void updateKline(KlineDataBean k) {
        List<KlineDataBean.DataBean> data = k.getData();
        if (null != data && data.size() >= 1) {
            KlineDataBean.DataBean klineItemBean = data.get(0);
            int index = adapter.getCount() - 1;
            KlineDataBean.DataBean lastShow = adapter.getDatas().get(index);
            if (lastShow.getDate().equals(klineItemBean.getDate())) {
                adapter.changeItem(index, klineItemBean);
            } else {
                adapter.addLast(klineItemBean);
            }
        }
    }

//    private void showHeaderData1(KlineDataBean.DataBean kk) {
//         List<KlineDataBean.DataBean> data = new ArrayList<>();
//        data.add(kk);
//        if (null != data && data.size() > 0) {
//            KlineDataBean.DataBean klineItemBean = data.get(0);
//            realTimePrice.set(String.format("%.0" + symbolConfig.getBaseCoinScale() + "f", klineItemBean.getClose()));
//            high.set(String.format("%.0" + symbolConfig.getBaseCoinScale() + "f", klineItemBean.getHigh()));
//            low.set(String.format("%.0" + symbolConfig.getBaseCoinScale() + "f", klineItemBean.getLow()));
//            vol.set(String.format("%.0" + symbolConfig.getCoinScale() + "f", klineItemBean.getVol()));
//            realPriceCny.set(String.format("%.02f", SymbolConfigs.getInstance().getCnyRate() * klineItemBean.getClose()));
//            change.set((klineItemBean.getClose() - klineItemBean.getOpen()) / klineItemBean.getOpen() * 100f);
//        }
//    }
    private void showHeaderData(KlineDataBean k) {
        List<KlineDataBean.DataBean> data = k.getData();
        if (null != data && data.size() > 0) {
            KlineDataBean.DataBean klineItemBean = data.get(0);
            realTimePrice.set(String.format("%.0" + symbolConfig.getBaseCoinScale() + "f", klineItemBean.getClose()));
            high.set(String.format("%.0" + symbolConfig.getBaseCoinScale() + "f", klineItemBean.getHigh()));
            low.set(String.format("%.0" + symbolConfig.getBaseCoinScale() + "f", klineItemBean.getLow()));
            vol.set(String.format("%.0" + symbolConfig.getCoinScale() + "f", klineItemBean.getVol()));
            realPriceCny.set(getApplication().getResources().getString(R.string.About_Symbol)+getApplication().getResources().getString(R.string.xxx)+String.format("%.02f", SymbolConfigs.getInstance().getCnyRate() * klineItemBean.getClose())+getApplication().getResources().getString(R.string.xxx)+getApplication().getResources().getString(R.string.CNY));
            change.set((klineItemBean.getClose() - klineItemBean.getOpen()) / klineItemBean.getOpen() * 100f);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        timer.cancel();
        timer = null;
    }
}
