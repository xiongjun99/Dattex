package com.temp.dattex.kline;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.common.framework.basic.BaseViewModel;
import com.common.framework.click.SingleClick;
import com.icechao.klinelib.adapter.KLineChartAdapter;
import com.icechao.klinelib.formatter.IDateTimeFormatter;
import com.icechao.klinelib.formatter.IValueFormatter;
import com.icechao.klinelib.formatter.ValueFormatter;
import com.icechao.klinelib.utils.DateUtil;
import com.independ.framework.response.ResponseTransformer;
import com.temp.dattex.bean.KlineDataBean;
import com.temp.dattex.bean.SymbolConfigBean;
import com.temp.dattex.config.SymbolConfigs;
import com.temp.dattex.net.DataService;
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

public class KlineViewModel extends BaseViewModel {

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

    private ObservableField<String> titleText = new ObservableField<>("BTC/USDT");
    private ObservableField<String> realTimePrice = new ObservableField<>("");
    private ObservableField<String> realPriceCny = new ObservableField<>("");
    private ObservableField<Float> change = new ObservableField<>(0f);
    private ObservableField<String> high = new ObservableField<>("");
    private ObservableField<String> low = new ObservableField<>("");
    private ObservableField<String> vol = new ObservableField<>("");
    private ObservableField<String> leftCoin = new ObservableField<>("BTC");
    private ObservableField<String> rightCoin = new ObservableField<>("USDT");

    public KLineChartAdapter<KlineDataBean.DataBean> adapter = new KLineChartAdapter<>();

    private ObservableField<BottomNav> bottomNav = new ObservableField<>(BottomNav.DEPTH);


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
            case DEAL:
                DataService.getInstance().getDealList(leftCoin.get().toUpperCase() + "/" +
                        rightCoin.get().toUpperCase()).compose(ResponseTransformer.handleResult())
                        .subscribe(data->{

                                },t->{

                                });

                break;
            case DEPTH:

                break;
            case INTRO:

                break;
        }
    }

    public boolean checkBottomNav(BottomNav bottomNav) {
        return getBottomNav().get().value == bottomNav.value;
    }

    public enum BottomNav {
        DEPTH(0), DEAL(1), INTRO(2);
        private int value;

        BottomNav(int value) {
            this.value = value;
        }
    }

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
        DataService.getInstance().klineHistory(symbol.toLowerCase(), 1, "1day").compose(ResponseTransformer.<KlineDataBean>handleResult()).subscribe(
                this::showHeaderData, t -> {
                }
        );
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
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
        }, 3000, 3000);
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

    private void showHeaderData(KlineDataBean k) {
        List<KlineDataBean.DataBean> data = k.getData();
        if (null != data && data.size() > 0) {
            KlineDataBean.DataBean klineItemBean = data.get(0);
            realTimePrice.set(String.format("%.0" + symbolConfig.getBaseCoinScale() + "f", klineItemBean.getClose()));
            high.set(String.format("%.0" + symbolConfig.getBaseCoinScale() + "f", klineItemBean.getHigh()));
            low.set(String.format("%.0" + symbolConfig.getBaseCoinScale() + "f", klineItemBean.getLow()));
            vol.set(String.format("%.0" + symbolConfig.getCoinScale() + "f", klineItemBean.getVol()));
            realPriceCny.set(String.format("%.02f", SymbolConfigs.getInstance().getCnyRate() * klineItemBean.getClose()));
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
