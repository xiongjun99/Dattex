package com.temp.dattex.fragments.trade;

import android.annotation.SuppressLint;
import android.app.Application;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;
import androidx.databinding.ObservableField;
import androidx.recyclerview.widget.DiffUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.common.framework.basic.AppManager;
import com.common.framework.basic.BaseApplication;
import com.common.framework.basic.BaseViewModel;
import com.common.framework.click.SingleClick;
import com.exchange.utilslib.SPUtil;
import com.exchange.utilslib.ToastUtil;
import com.independ.framework.response.ResponseTransformer;
import com.temp.dattex.Constants;
import com.temp.dattex.R;
import com.temp.dattex.adapter.CurrentRecyclerAdapter;
import com.temp.dattex.bean.InfoBySymbolBean;
import com.temp.dattex.bean.MarketListBean;
import com.temp.dattex.bean.NewAssetsBean;
import com.temp.dattex.bean.OrdersBean;
import com.temp.dattex.bean.SymbolConfigBean;
import com.temp.dattex.bean.TradeDepthBean;
import com.temp.dattex.binding.adapter.EditTextBinding;
import com.temp.dattex.config.AssetsConfigs;
import com.temp.dattex.config.SymbolConfigs;
import com.temp.dattex.database.LoginInfo;
import com.temp.dattex.databinding.ItemLeverageBinding;
import com.temp.dattex.kline.KlineActivity;
import com.temp.dattex.login.LoginActivity;
import com.temp.dattex.net.DataService;
import com.temp.dattex.order.OrderActivity;
import com.temp.dattex.util.DialogUtil;
import com.temp.dattex.util.PlaceAnOrderDialogModel;
import com.temp.dattex.util.SwitchSymbolDialogViewModel;
import com.temp.dattex.util.Utils;
import com.temp.dattex.wallet.WalletActivity;
import com.temp.dattex.widget.ProgressBar;
import com.temp.dattex.widget.TradeDepthView;
import com.temp.dattex.widget.view.CustomSeekBar;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TradeViewModel extends BaseViewModel implements ProgressBar.OnProgressChangeListener, EditTextBinding.EditListener, PlaceAnOrderDialogModel.OnEnsureListener, SwitchSymbolDialogViewModel.OnSymbolSet {
    public CurrentRecyclerAdapter adapter = new CurrentRecyclerAdapter(getApplication(),R.layout.item_order, new ArrayList<>(),0,0);
    public BaseQuickAdapter leverageAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_leverage, new ArrayList<String>()) {

        @Override
        protected void convert(BaseViewHolder baseViewHolder, String leverageBean) {
            ItemLeverageBinding binding = baseViewHolder.getBinding();
            binding.setLaverageBean(leverageBean);
            binding.setTradeViewModel(TradeViewModel.this);
        }

        @Override
        public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            BaseViewHolder baseViewHolder = super.onCreateViewHolder(parent, viewType);
            DataBindingUtil.bind(baseViewHolder.itemView);
            return baseViewHolder;
        }
    };
    private SymbolConfigBean symbolConfig;
    private Timer timer;

    public TradeViewModel(@NonNull Application application) {
        super(application);
    }


    public ObservableField<List<String>> getSeekbarIntervals() {
        return seekbarIntervals;
    }

    public void setSeekbarIntervals(ObservableField<List<String>> seekbarIntervals) {
        this.seekbarIntervals = seekbarIntervals;
    }

    public ObservableField<List<String>> seekbarIntervals = new ObservableField<>();
    private ObservableField<String> leftCoin = new ObservableField<>("BTC");
    private ObservableField<String> rightCoin = new ObservableField<>("USDT");
    public  ObservableField<Boolean> tradeBuy = new ObservableField<>(true);
    private ObservableField<String> placeAnOrder = new ObservableField<>("创建合约");

    public ObservableField<List<SymbolConfigBean>> getSymbolConfigList() {
        return symbolConfigList;
    }

    public void setSymbolConfigList(ObservableField<List<SymbolConfigBean>> symbolConfigList) {
        this.symbolConfigList = symbolConfigList;
    }

    public ObservableField<List<String>> getmList() {
        return mList;
    }

    public void setmList(ObservableField<List<String>> mList) {
        this.mList = mList;
    }

    private ObservableField<List<String>> mList = new ObservableField<>();

    private ObservableField<List<SymbolConfigBean>> symbolConfigList = new ObservableField<>();
    //可用余额
    private ObservableField<String> availableBalance = new ObservableField<>("0.0");
    //杠杆倍数
    private ObservableField<String> leveraged = new ObservableField<>("");

    //交易总额
    private ObservableField<String> tradeAmount = new ObservableField<>("300");
    //进度条仓位百分比
    private ObservableField<Integer> positionPercent = new ObservableField<>();

    //手续费
    private ObservableField<String> serviceCharge = new ObservableField<>("0");

    public ObservableField<String> getFeeRates() {
        return FeeRates;
    }

    public void setFeeRates(ObservableField<String> feeRates) {
        FeeRates = feeRates;
    }

    private ObservableField<String> FeeRates = new ObservableField<>("0");

    private ObservableField<TradeDepthBean> trade = new ObservableField<>();


    private ObservableField<TradeDepthView> tradeView = new ObservableField<>();

    private ObservableField<String> aska1 = new ObservableField<>("0.00");
    private ObservableField<String> aska2 = new ObservableField<>("0.00");
    private ObservableField<String> aska3 = new ObservableField<>("0.00");
    private ObservableField<String> aska4 = new ObservableField<>("0.00");
    private ObservableField<String> aska5 = new ObservableField<>("0.00");
    private ObservableField<String> aska6 = new ObservableField<>("0.00");
    private ObservableField<String> aska7 = new ObservableField<>("0.00");
    private ObservableField<String> aska8 = new ObservableField<>("0.00");
    private ObservableField<String> aska9 = new ObservableField<>("0.00");
    private ObservableField<String> aska10 = new ObservableField<>("0.00");

    private ObservableField<String> bids1 = new ObservableField<>("0.00");
    private ObservableField<String> bids2 = new ObservableField<>("0.00");
    private ObservableField<String> bids3 = new ObservableField<>("0.00");
    private ObservableField<String> bids4 = new ObservableField<>("0.00");
    private ObservableField<String> bids5 = new ObservableField<>("0.00");
    private ObservableField<String> bids6 = new ObservableField<>("0.00");
    private ObservableField<String> bids7 = new ObservableField<>("0.00");
    private ObservableField<String> bids8 = new ObservableField<>("0.00");
    private ObservableField<String> bids9 = new ObservableField<>("0.00");
    private ObservableField<String> bids10 = new ObservableField<>("0.00");

    private ObservableField<List<Integer>> progressList = new ObservableField<>();
    private ObservableField<Integer> PointCount = new ObservableField<>(0);
    private ObservableField<Integer> progress = new ObservableField<>(0);
    public  ObservableField<Boolean> isShow = new ObservableField<>(true);
    private ObservableField<String> currentprice = new ObservableField<>("0.00");
    private ObservableField<String> cnyprice = new ObservableField<>("0.00");
    private ObservableField<String> CNY_RATE = new ObservableField<>("");

    public ObservableField<String> getCnyprice() {
        return cnyprice;
    }

    public void setCnyprice(ObservableField<String> cnyprice) {
        this.cnyprice = cnyprice;
    }


    public ObservableField<String> getCurrentprice() {
        return currentprice;
    }

    public void setCurrentprice(ObservableField<String> currentprice) {
        this.currentprice = currentprice;
    }


    public ObservableField<Boolean> getIsShow() {
        return isShow;
    }

    public void setIsShow(ObservableField<Boolean> isShow) {
        this.isShow = isShow;
    }


    public ObservableField<Integer> getProgress() {
        return progress;
    }
    public ObservableField<TradeDepthBean> getTrade() {
        return trade;
    }

    public void setTrade(ObservableField<TradeDepthBean> trade) {
        this.trade = trade;
    }
    public void setProgress(ObservableField<Integer> progress) {
        this.progress = progress;
    }


    public ObservableField<Integer> getPointCount() {
        return PointCount;
    }

    public void setPointCount(ObservableField<Integer> pointCount) {
        PointCount = pointCount;
    }

    public ObservableField<List<Integer>> getProgressList() {
        return progressList;
    }

    public void setProgressList(ObservableField<List<Integer>> progressList) {
        this.progressList = progressList;
    }

    public ObservableField<String> getBids1() {
        return bids1;
    }

    public void setBids1(ObservableField<String> bids1)
    {
        bids1.set(Utils.keepTwo(Double.valueOf(bids1.get())));
        this.bids1 = bids1;
    }

    public ObservableField<String> getBids2()
    {
        bids2.set(Utils.keepTwo(Double.valueOf(bids2.get())));
        return bids2;
    }

    public void setBids2(ObservableField<String> bids2) {
        this.bids2 = bids2;
    }

    public ObservableField<String> getBids3() {
        bids3.set(Utils.keepTwo(Double.valueOf(bids3.get())));
        return bids3;
    }

    public void setBids3(ObservableField<String> bids3) {
        this.bids3 = bids3;
    }

    public ObservableField<String> getBids4() {
        bids4.set(Utils.keepTwo(Double.valueOf(bids4.get())));
        return bids4;
    }

    public void setBids4(ObservableField<String> bids4) {
        this.bids4 = bids4;
    }

    public ObservableField<String> getBids5() {
        bids5.set(Utils.keepTwo(Double.valueOf(bids5.get())));
        return bids5;
    }

    public void setBids5(ObservableField<String> bids5) {
        this.bids5 = bids5;
    }

    public ObservableField<String> getBids6() {
        bids6.set(Utils.keepTwo(Double.valueOf(bids6.get())));
        return bids6;
    }

    public void setBids6(ObservableField<String> bids6) {
        this.bids6 = bids6;
    }

    public ObservableField<String> getBids7() {
        bids7.set(Utils.keepTwo(Double.valueOf(bids7.get())));
        return bids7;
    }

    public void setBids7(ObservableField<String> bids7) {
        this.bids7 = bids7;
    }

    public ObservableField<String> getBids8() {
        bids8.set(Utils.keepTwo(Double.valueOf(bids8.get())));
        return bids8;
    }

    public void setBids8(ObservableField<String> bids8) {
        this.bids8 = bids8;
    }

    public ObservableField<String> getBids9() {
        bids9.set(Utils.keepTwo(Double.valueOf(bids9.get())));
        return bids9;
    }

    public void setBids9(ObservableField<String> bids9) {
        this.bids9 = bids9;
    }

    public ObservableField<String> getBids10() {
        bids10.set(Utils.keepTwo(Double.valueOf(bids10.get())));

        return bids10;
    }

    public void setBids10(ObservableField<String> bids10) {
        this.bids10 = bids10;
    }
    public ObservableField<Boolean> getTradeBuy() {
        return tradeBuy;
    }

    public void setTradeBuy(ObservableField<Boolean> tradeBuy) {
        this.tradeBuy = tradeBuy;
    }

    public ObservableField<String> getPlaceAnOrder() {
        return placeAnOrder;
    }

    public void setPlaceAnOrder(ObservableField<String> placeAnOrder) {
        this.placeAnOrder = placeAnOrder;
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

    public ObservableField<String> getAska1() {
        aska1.set(Utils.keepTwo(Double.valueOf(aska1.get())));
        return aska1;
    }

    public void setAska1(ObservableField<String> aska1) {
        this.aska1 = aska1;
    }

    public void setAska2(ObservableField<String> aska1) {
        this.aska2 = aska1;
    }

    public ObservableField<String> getAska2() {
        aska2.set(Utils.keepTwo(Double.valueOf(aska2.get())));
        return aska2;
    }
    public ObservableField<String> getAska3() {
        aska3.set(Utils.keepTwo(Double.valueOf(aska3.get())));
        return aska3;
    }

    public void setAska3(ObservableField<String> aska3) {
        this.aska3= aska3;
    }
    public ObservableField<String> getAska4() {
        aska4.set(Utils.keepTwo(Double.valueOf(aska4.get())));
        return aska4;
    }

    public void setAska4(ObservableField<String> aska4) {
        this.aska4 = aska4;
    }

    public ObservableField<String> getAska5() {
        aska5.set(Utils.keepTwo(Double.valueOf(aska5.get())));
        return aska5;
    }

    public void setAska5(ObservableField<String> aska5) {
        this.aska5 = aska5;
    }

    public ObservableField<String> getAska6() {
        aska6.set(Utils.keepTwo(Double.valueOf(aska6.get())));
        return aska6;
    }

    public void setAska6(ObservableField<String> aska6) {
        this.aska6 = aska6;
    }

    public ObservableField<String> getAska7() {
        aska7.set(Utils.keepTwo(Double.valueOf(aska7.get())));
        return aska7;
    }

    public void setAska7(ObservableField<String> aska7) {
        this.aska7 = aska7;
    }
    public ObservableField<String> getAska8() {
        aska8.set(Utils.keepTwo(Double.valueOf(aska8.get())));
        return aska8;
    }

    public void setAska8(ObservableField<String> aska8) {
        this.aska8 = aska8;
    }

    public ObservableField<String> getAska9() {
        aska9.set(Utils.keepTwo(Double.valueOf(aska9.get())));
        return aska9;
    }

    public void setAska9(ObservableField<String> aska9) {
        this.aska9 = aska9;
    }
    public ObservableField<String> getAska10() {
        aska10.set(Utils.keepTwo(Double.valueOf(aska10.get())));
        return aska10;
    }

    public void setAska10(ObservableField<String> aska10) {
        this.aska10 = aska10;
    }
    public void setRightCoin(ObservableField<String> rightCoin) {
        this.rightCoin = rightCoin;
    }

    public ObservableField<String> getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailaleBalance(ObservableField<String> availableBalance) {
        this.availableBalance = availableBalance;
    }

    public ObservableField<String> getLeveraged() {
        return leveraged;
    }

    public void setLeveraged(ObservableField<String> leveraged) {
        this.leveraged = leveraged;
    }

    public ObservableField<String> getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(ObservableField<String> tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    public ObservableField<Integer> getPositionPercent() {
        return positionPercent;
    }


    public void setPositionPercent(ObservableField<Integer> positionPercent) {
        this.positionPercent = positionPercent;
    }

    public ObservableField<String> getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(ObservableField<String> serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public ObservableField<String> getCNY_RATE() {
        return CNY_RATE;
    }

    public void setCNY_RATE(ObservableField<String> CNY_RATE) {
        this.CNY_RATE = CNY_RATE;
    }


    @Override
    public void onProgressChanged(int progress) {
        getPositionPercent().set(progress);
        tradeAmount.set(String.valueOf(progress));
    }

    @SingleClick
    public void setTradeBuy() {
        getTradeBuy().set(true);
    }

    @SingleClick
    public void setTradeSell() {
        getTradeBuy().set(false);
    }


    @SingleClick
    public void showKline() {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.REQUEST_KEY_COIN_ID, leftCoin.get().toUpperCase() + "/" + rightCoin.get().toUpperCase());
        startActivity(KlineActivity.class, bundle);
    }

    @SingleClick
    public void resetLeveraged() {
        if (LoginInfo.isSign()==true){
            if (isShow.get()==true){
                DialogUtil.changeLeverage(AppManager.getActivityStack().peek(), this);
            }else {
            }
        }
    }

    @SingleClick
    public void changeLeverage(String bean) {
        String newbean = bean.substring(bean.lastIndexOf("~")+1);
        leveraged.set(bean.substring(0, bean.indexOf("X")));
        leverageDialog.dismiss();
        leverageDialog = null;
    }


    @SingleClick
    public void switchSymbol() {
        SwitchSymbolDialogViewModel switchSymbolDialogViewModel = new SwitchSymbolDialogViewModel();
        switchSymbolDialogViewModel.setOnSymbolSet(this);
        DialogUtil.showSwitchCoinDialog(AppManager.getActivityStack().peek(), switchSymbolDialogViewModel,null);
    }

    @SingleClick
    public void showAllOrders() {
        startActivity(OrderActivity.class);
    }

    @SingleClick
    public void placeAnOrder() {
        if (!TextUtils.isEmpty(tradeAmount.get())) {
            if (LoginInfo.isSign()) {
                if (LoginInfo.isCertification().startsWith("2")){
                    PlaceAnOrderDialogModel placeAnOrderDialogModel = new PlaceAnOrderDialogModel();
                    placeAnOrderDialogModel.setOnEnsureListener(this);
                    placeAnOrderDialogModel.setPrice(tradeAmount.get());
                    DialogUtil.createContractDialog(AppManager.getActivityStack().peek(), placeAnOrderDialogModel);
                } else {
                    ToastUtil.show(getApplication(),"请先实名认证");
                }
            } else {
                startActivity(LoginActivity.class);
            }
        } else {
            ToastUtil.show(getApplication(), "请输入金额");
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (null != timer) {
            timer.cancel();
            timer = null;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (LoginInfo.isSign()) {
//            getPlaceAnOrder().set(getApplication().getResources().getString(getTradeBuy().get() ? R.string.text_buy : R.string.text_sell));
            freshAssetsByCoinId(rightCoin.get());
            placeAnOrder.set("创建合约");
        } else {
            placeAnOrder.set("登录");
        }
        freshOrderList();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
//              getMarketList();
                freshOrderList();
                getDepth();
            }
        }, 1000, 1000);
        symbolConfig = SymbolConfigs.getInstance().getSymbol(leftCoin.get().toUpperCase() + "/" + rightCoin.get().toUpperCase());

         if (symbolConfig.getSymbol().equals(leftCoin.get().toUpperCase() + "/" + rightCoin.get().toUpperCase())){
             FeeRates.set(String.valueOf(symbolConfig.getFeeRates()));
         }
            getServiceCharge().set(""+Float.valueOf(getTradeAmount().get())*Float.valueOf(getFeeRates().get()));

    }
    @SuppressLint("CheckResult")
    private void getDepth() {

        DataService.getInstance().getDepth(leftCoin.get().toUpperCase() + "/" + rightCoin.get().toUpperCase()).compose(ResponseTransformer.<List<TradeDepthBean>>handleResult()).subscribe(
                list -> {
                    String CNY_RATE = getCNY_RATE().get();
                    currentprice.set(String.valueOf(list.get(0).getAsks().get(0).keySet()).replaceAll("\\[|\\]", ""));
                    cnyprice.set(Utils.FloatkeepTwo(Float.valueOf(currentprice.get())*Float.valueOf(CNY_RATE)));
                    aska1.set(String.valueOf(list.get(0).getAsks().get(0).keySet()).replaceAll("\\[|\\]", ""));
                    aska2.set(String.valueOf(list.get(0).getAsks().get(0).values()).replaceAll("\\[|\\]", ""));
                    aska3.set(String.valueOf(list.get(0).getAsks().get(1).keySet()).replaceAll("\\[|\\]", ""));
                    aska4.set(String.valueOf(list.get(0).getAsks().get(1).values()).replaceAll("\\[|\\]", ""));
                    aska5.set(String.valueOf(list.get(0).getAsks().get(2).keySet()).replaceAll("\\[|\\]", ""));
                    aska6.set(String.valueOf(list.get(0).getAsks().get(2).values()).replaceAll("\\[|\\]", ""));
                    aska7.set(String.valueOf(list.get(0).getAsks().get(3).keySet()).replaceAll("\\[|\\]", ""));
                    aska8.set(String.valueOf(list.get(0).getAsks().get(3).values()).replaceAll("\\[|\\]", ""));
                    aska9.set(String.valueOf(list.get(0).getAsks().get(4).keySet()).replaceAll("\\[|\\]", ""));
                    aska10.set(String.valueOf(list.get(0).getAsks().get(4).values()).replaceAll("\\[|\\]", ""));
                    bids1.set(String.valueOf(list.get(0).getBids().get(0).keySet()).replaceAll("\\[|\\]", ""));
                    bids2.set(String.valueOf(list.get(0).getAsks().get(0).values()).replaceAll("\\[|\\]", ""));
                    bids3.set(String.valueOf(list.get(0).getAsks().get(1).keySet()).replaceAll("\\[|\\]", ""));
                    bids4.set(String.valueOf(list.get(0).getAsks().get(1).values()).replaceAll("\\[|\\]", ""));
                    bids5.set(String.valueOf(list.get(0).getAsks().get(2).keySet()).replaceAll("\\[|\\]", ""));
                    bids6.set(String.valueOf(list.get(0).getAsks().get(2).values()).replaceAll("\\[|\\]", ""));
                    bids7.set(String.valueOf(list.get(0).getAsks().get(3).keySet()).replaceAll("\\[|\\]", ""));
                    bids8.set(String.valueOf(list.get(0).getAsks().get(3).values()).replaceAll("\\[|\\]", ""));
                    bids9.set(String.valueOf(list.get(0).getAsks().get(4).keySet()).replaceAll("\\[|\\]", ""));
                    bids10.set(String.valueOf(list.get(0).getAsks().get(4).values()).replaceAll("\\[|\\]", ""));
                }, t -> {
                }
        );
    }

    public Observable.OnPropertyChangedCallback tradeAmountChangeCallBack = new Observable.OnPropertyChangedCallback() {
        @Override
        public void onPropertyChanged(Observable sender, int propertyId) {
            String s = getTradeAmount().get();
            if (!TextUtils.isEmpty(s)) {
                if (s.equals(".")) {
                    s = "0.";
                } else if (s.indexOf(".") != s.lastIndexOf(".")) {
                    s = s.substring(0, s.length() - 1);
                } else if (s.startsWith("0") && !s.equals("0") && !s.startsWith("0.")) {
                    s = s.substring(1);
                }
            }
            getTradeAmount().set(s);
        }
    };

    @Override
    public void onStart() {
        super.onStart();
        DataService.getInstance().getUsdtRate().compose(ResponseTransformer.<Double>handleResult()).subscribe(
                d -> {
                    getCNY_RATE().set(String.valueOf(d));
                    getDepth();
                }, t -> {
                }
        );
        getTradeAmount().addOnPropertyChangedCallback(tradeAmountChangeCallBack);
        symbolConfigList.set(SymbolConfigs.getInstance().getSymbols());
//        leftCoin.set(symbolConfigList.get().get(0).getCoinSymbol());
//        rightCoin.set(symbolConfigList.get().get(0).getBaseSymbol());
    }

    @Override
    public void onStop() {
        super.onStop();
        getTradeAmount().removeOnPropertyChangedCallback(tradeAmountChangeCallBack);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onCreate() {
        super.onCreate();
//        DataService.getInstance().getLeverage(leftCoin.get() + "/" + rightCoin.get()).compose(ResponseTransformer.<List<LeverageBean>>handleResult()).subscribe(
//                list -> {
//                    leverageAdapter.addData(list);
//                }, t -> ToastUtil.show(BaseApplication.getInstance(), t.getMessage())
//        );
//      DataService.getInstance().getInfoBySymbol(leftCoin.get() + "/" + rightCoin.get()).compose(ResponseTransformer.<InfoBySymbolBean>handleResult()).subscribe(
//                o -> {
//                    List<String> list = Arrays.asList(o.getExchangePrincipalPrice().replaceAll(" ","").split(","));
//                    for (int i = 0; i < list.size(); i++) {
//                    progressList.get().add(Integer.valueOf(list.get(i)));
//                    }
//                    PointCount.set(progressList.get().size()-1);
//                    progress.set(1000);
//                }, t -> ToastUtil.show(BaseApplication.getInstance(), t.getMessage())
//        );
//        if(progressList.get()==null){
//            List<Integer> list = new ArrayList<>();
//            list.add(0);
//            list.add(0);
//            progressList.set(list);
//            PointCount.set(1);
//        }
    }
    public void initData(){
        DataService.getInstance().getInfoBySymbol(leftCoin.get() + "/" + rightCoin.get()).compose(ResponseTransformer.<InfoBySymbolBean>handleResult()).subscribe(
                o -> {
                    isShow.set(true);
                    leverageAdapter.addData(Arrays.asList(o.getExchangeLevers().replaceAll(" ","").split(",")));
                    seekbarIntervals.set(Arrays.asList(o.getExchangePrincipalPrice().replaceAll(" ","").split(",")));
                    positionPercent.set(Integer.valueOf(seekbarIntervals.get().get(0)));
                }, t ->{
                    isShow.set(false);
//                  ToastUtil.show(getApplication(), t.getMessage());
                }
        );
    }
    @Override
    public void afterTextChanged(EditText view, String text) {
        view.setSelection(text.length());
    }

    @SuppressLint("CheckResult")
    @Override
    public void onEnsure(float stopProfitRate, float stopLossRate) {
        DataService.getInstance().placeOrder(leftCoin.get() + "/" + rightCoin.get(), tradeBuy.get() ? 0 : 1, leveraged.get(), tradeAmount.get(),stopLossRate, stopProfitRate).compose(ResponseTransformer.handleResult()).subscribe(
                o -> {
//                    freshAssetsByCoinId(rightCoin.get());
                    freshOrderList();
                }, t -> ToastUtil.show(BaseApplication.getInstance(), t.getMessage())
        );
    }
    @SuppressLint("CheckResult")
    public void freshAssetsByCoinId(String CoinId) {
        DataService.getInstance().getAssetsByCoinId(CoinId).compose(ResponseTransformer.<NewAssetsBean>handleResult()).subscribe(
                assetsBean -> {
                    if(null != assetsBean) {
                        BigDecimal bg = new BigDecimal(assetsBean.getBalance());
                        availableBalance.set(bg.toPlainString());
                    }
                }, t -> {
                    System.out.println("-------no----根据币种ID获取对应币种的会员资产钱包信息");
                }
        );
    }
    @SuppressLint("CheckResult")
    private void freshOrderList() {
        DataService.getInstance().getAllOrders("01",1,1, leftCoin.get() + "/" + rightCoin.get()).compose(ResponseTransformer.<OrdersBean>handleResult()).subscribe(
                bean -> {
                    int size = adapter.getData().size();
                    List<OrdersBean.OrderItemBean> rows = bean.getRows();
                    DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                        @Override
                        public int getOldListSize() {
                            return adapter.getData() == null ? 0 : adapter.getData().size();
                        }

                        @Override
                        public int getNewListSize() {
                            return null == rows ? 0 : rows.size();
                        }

                        @Override
                        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                            return true;
                        }

                        @Override
                        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                            return false;
                        }
                    });
                    adapter.setNewData(rows);
                    diffResult.dispatchUpdatesTo(adapter);
                    if (size!=rows.size()){
                        freshAssetsByCoinId(rightCoin.get());
                    }
                }, t ->
                {
//                    ToastUtil.show(BaseApplication.getInstance(), t.getMessage());
                }
        );
    }

    public SymbolConfigBean tradeSymbolConfig = SymbolConfigs.getInstance().getSymbol(leftCoin.get() + "/" + rightCoin.get());
    @Override
    public void onSymbolSet(String coinSymbol, String baseSymbol) {
//        this.leftCoin.set(coinSymbol);
//        this.rightCoin.set(baseSymbol);
        tradeSymbolConfig = SymbolConfigs.getInstance().getSymbol(leftCoin.get() + "/" + rightCoin.get());
    }
    private Dialog leverageDialog;

    public void setLeverageDialog(Dialog leverageDialog) {
        this.leverageDialog = leverageDialog;
    }
}
