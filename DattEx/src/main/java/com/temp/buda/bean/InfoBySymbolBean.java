package com.temp.buda.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class InfoBySymbolBean implements Parcelable {

    /**
     * symbol : BTC/USDT
     * feeRates : 0.01
     * exchangePrincipalPrice : 300, 500, 1000, 2000, 5000, 10000
     * exchangeLevers : 25, 50
     */

    private String symbol;
    private double feeRates;
    private String exchangePrincipalPrice;
    private String exchangeLevers;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getFeeRates() {
        return feeRates;
    }

    public void setFeeRates(double feeRates) {
        this.feeRates = feeRates;
    }

    public String getExchangePrincipalPrice() {
        return exchangePrincipalPrice;
    }

    public void setExchangePrincipalPrice(String exchangePrincipalPrice) {
        this.exchangePrincipalPrice = exchangePrincipalPrice;
    }

    public String getExchangeLevers() {
        return exchangeLevers;
    }

    public void setExchangeLevers(String exchangeLevers) {
        this.exchangeLevers = exchangeLevers;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
