package com.temp.buda.bean;

public class HomeFunctionBean {
    private String mFunName;
    private String mFunInfo;
    private String mURL;
    private String Test;
    private boolean isWeb;

    public String getTest() {
        return Test;
    }

    public void setTest(String test) {
        Test = test;
    }

    public boolean isWeb() {
        return isWeb;
    }

    public void setWeb(boolean web) {
        isWeb = web;
    }
    public String getmFunName() {
        return mFunName;
    }

    public void setmFunName(String mFunName) {
        this.mFunName = mFunName;
    }

    public String getmFunInfo() {
        return mFunInfo;
    }

    public void setmFunInfo(String mFunInfo) {
        this.mFunInfo = mFunInfo;
    }

    public String getmURL() {
        return mURL;
    }

    public void setmURL(String mURL) {
        this.mURL = mURL;
    }

    public HomeFunctionBean(String FunName, String FunInfo, String URL ,String test , boolean isweb) {
        this.mFunInfo = FunInfo;
        this.mFunName = FunName;
        this.mURL = URL;
        this.Test = test;
        this.isWeb = isweb;
    }
}
