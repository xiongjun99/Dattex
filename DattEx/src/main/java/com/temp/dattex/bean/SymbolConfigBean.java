package com.temp.dattex.bean;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.temp.dattex.BR;
import com.temp.dattex.config.SymbolConfigs;

import java.io.Serializable;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.temp.dattex.bean
 * @FileName     : SymbolConfigBean.java
 * @Author       : chao
 * @Date         : 2020/5/26
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
public class SymbolConfigBean extends BaseObservable implements Serializable {

    /**
     * symbol : BTC/USDT
     * baseCoinScale : 8
     * baseSymbol : USDT
     * coinScale : 8
     * coinSymbol : BTC
     * enable : 1
     * feeRates : 0.01
     * sort : 1
     * enableMarketBuy : 1
     * enableMarketSell : 1
     * flag : 0
     * maxTradingOrder : 0
     * maxTradingTime : 0
     * zone : 0
     */

    private float open;
    private float high;
    private float low;
    private float close;
    private float vol;
    private long id;
    private float changes;

    @Bindable
    public float getClose() {
        return close;
    }

    public void setClose(float close) {
        this.close = close;
        notifyPropertyChanged(BR.close);
        notifyPropertyChanged(BR.cnyPrice);
    }

    @Bindable
    public float getVol() {
        return vol;
    }

    public void setVol(float vol) {
        this.vol = vol;
        notifyPropertyChanged(BR.vol);
    }

    @Bindable
    public float getChanges() {
        return changes;

    }

    public void setChanges(float changes) {
        this.changes = changes;
        notifyPropertyChanged(BR.changes);
    }

    public float getOpen() {
        return open;
    }

    public void setOpen(float open) {
        this.open = open;
    }

    public float getHigh() {
        return high;
    }

    public void setHigh(float high) {
        this.high = high;
    }

    public float getLow() {
        return low;
    }

    public void setLow(float low) {
        this.low = low;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private String symbol;
    private int baseCoinScale;
    private String baseSymbol;
    private int coinScale;
    private String coinSymbol;
    private int enable;
    private double feeRates;
    private int sort;
    private int enableMarketBuy;
    private int enableMarketSell;
    private int flag;
    private int maxTradingOrder;
    private int maxTradingTime;
    private int zone;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getBaseCoinScale() {
        return baseCoinScale;
    }

    public void setBaseCoinScale(int baseCoinScale) {
        this.baseCoinScale = baseCoinScale;
    }

    public String getBaseSymbol() {
        return baseSymbol;
    }

    public void setBaseSymbol(String baseSymbol) {
        this.baseSymbol = baseSymbol;
    }

    public int getCoinScale() {
        return coinScale;
    }

    public void setCoinScale(int coinScale) {
        this.coinScale = coinScale;
    }

    public String getCoinSymbol() {
        return coinSymbol;
    }

    public void setCoinSymbol(String coinSymbol) {
        this.coinSymbol = coinSymbol;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public double getFeeRates() {
        return feeRates;
    }

    public void setFeeRates(double feeRates) {
        this.feeRates = feeRates;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getEnableMarketBuy() {
        return enableMarketBuy;
    }

    public void setEnableMarketBuy(int enableMarketBuy) {
        this.enableMarketBuy = enableMarketBuy;
    }

    public int getEnableMarketSell() {
        return enableMarketSell;
    }

    public void setEnableMarketSell(int enableMarketSell) {
        this.enableMarketSell = enableMarketSell;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getMaxTradingOrder() {
        return maxTradingOrder;
    }

    public void setMaxTradingOrder(int maxTradingOrder) {
        this.maxTradingOrder = maxTradingOrder;
    }

    public int getMaxTradingTime() {
        return maxTradingTime;
    }

    public void setMaxTradingTime(int maxTradingTime) {
        this.maxTradingTime = maxTradingTime;
    }

    public int getZone() {
        return zone;
    }

    public void setZone(int zone) {
        this.zone = zone;
    }


    @Bindable
    public String getCnyPrice() {
        return String.format("%.02f", SymbolConfigs.getInstance().getCnyRate() * close);
    }
}
