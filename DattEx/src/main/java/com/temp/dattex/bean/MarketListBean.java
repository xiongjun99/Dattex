package com.temp.dattex.bean;

public class MarketListBean {


    /**
     * coinId : BTC/USDT
     * price : 11821.69
     * open : 11766.14
     * low : 11684.74
     * high : 11880
     * avg : 11782.37
     * dealCount : 23741.97210192
     * changes : 0.47
     */

    private String coinId;
    private String price;
    private String open;
    private String low;
    private String high;
    private String avg;
    private String dealCount;
    private String changes;

    public String getCoinId() {
        return coinId;
    }

    public void setCoinId(String coinId) {
        this.coinId = coinId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getAvg() {
        return avg;
    }

    public void setAvg(String avg) {
        this.avg = avg;
    }

    public String getDealCount() {
        return dealCount;
    }

    public void setDealCount(String dealCount) {
        this.dealCount = dealCount;
    }

    public String getChanges() {
        return changes;
    }

    public void setChanges(String changes) {
        this.changes = changes;
    }
}
