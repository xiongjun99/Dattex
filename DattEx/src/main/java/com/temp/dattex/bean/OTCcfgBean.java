package com.temp.dattex.bean;

public class OTCcfgBean {
    /**
     * buyFee : 0
     * buyRatio : 0
     * created : 2020-08-05T08:36:06.674Z
     * currency : string
     * feeModel : 0
     * sellFee : 0
     * sellRatio : 0
     * symbol : string
     * updated : 2020-08-05T08:36:06.674Z
     * weight : 0
     * minInQty
     * maxInQty
     * minOutQty
     * maxOutQty
     */

    private double buyFee;
    private double buyRatio;
    private String created;
    private String currency;
    private double feeModel;
    private double sellFee;
    private double sellRatio;
    private String symbol;
    private String updated;
    private int weight;
    private String minInQty;
    private String maxInQty;
    private String minOutQty;
    private String maxOutQty;

    public String getMinInQty() {
        return minInQty;
    }

    public void setMinInQty(String minInQty) {
        this.minInQty = minInQty;
    }

    public String getMaxInQty() {
        return maxInQty;
    }

    public void setMaxInQty(String maxInQty) {
        this.maxInQty = maxInQty;
    }

    public String getMinOutQty() {
        return minOutQty;
    }

    public void setMinOutQty(String minOutQty) {
        this.minOutQty = minOutQty;
    }

    public String getMaxOutQty() {
        return maxOutQty;
    }

    public void setMaxOutQty(String maxOutQty) {
        this.maxOutQty = maxOutQty;
    }


    public double getBuyFee() {
        return buyFee;
    }

    public void setBuyFee(double buyFee) {
        this.buyFee = buyFee;
    }

    public double getBuyRatio() {
        return buyRatio;
    }

    public void setBuyRatio(double buyRatio) {
        this.buyRatio = buyRatio;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getFeeModel() {
        return feeModel;
    }

    public void setFeeModel(double feeModel) {
        this.feeModel = feeModel;
    }

    public double getSellFee() {
        return sellFee;
    }

    public void setSellFee(double sellFee) {
        this.sellFee = sellFee;
    }

    public double getSellRatio() {
        return sellRatio;
    }

    public void setSellRatio(double sellRatio) {
        this.sellRatio = sellRatio;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
