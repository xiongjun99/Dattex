package com.temp.dattex.bean;

public class HomeViewPagerBean {

    private String CurrencyType = "";
    private String CurrentIndex = "";
    private String Percentage = "";
    private String CNY = "";

    public String getCurrencyType() {
        return CurrencyType;
    }

    public void setCurrencyType(String currencyType) {
        CurrencyType = currencyType;
    }

    public String getCurrentIndex() {
        return CurrentIndex;
    }

    public void setCurrentIndex(String currentIndex) {
        CurrentIndex = currentIndex;
    }

    public String getPercentage() {
        return Percentage;
    }

    public void setPercentage(String percentage) {
        Percentage = percentage;
    }

    public String getCNY() {
        return CNY;
    }

    public void setCNY(String CNY) {
        this.CNY = CNY;
    }

    public HomeViewPagerBean(String currencyType, String currentIndex, String percentage, String cny) {
        this.CurrencyType = currencyType;
        this.CurrentIndex = currentIndex;
        this.Percentage = percentage;
        this.CNY = cny;

    }


}
