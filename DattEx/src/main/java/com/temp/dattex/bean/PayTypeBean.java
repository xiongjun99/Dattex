package com.temp.dattex.bean;

import java.util.List;

public class PayTypeBean {


    /**
     * id : 1000
     * name : {"zh":"支付宝"}
     * minIn : 100
     * maxIn : 10000
     * inDefault : [100,200,300]
     * minOut : 100
     * maxOut : 10000
     * outDefault : [111]
     * enable : true
     * stopBegin : 11
     * stopEnd : null
     */

    private int id;
    private NameBean name;
    private int minIn;
    private int maxIn;
    private int minOut;
    private int maxOut;
    private boolean enable;
    private String stopBegin;
    private Object stopEnd;
    private List<Integer> inDefault;
    private List<Integer> outDefault;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public NameBean getName() {
        return name;
    }

    public void setName(NameBean name) {
        this.name = name;
    }

    public int getMinIn() {
        return minIn;
    }

    public void setMinIn(int minIn) {
        this.minIn = minIn;
    }

    public int getMaxIn() {
        return maxIn;
    }

    public void setMaxIn(int maxIn) {
        this.maxIn = maxIn;
    }

    public int getMinOut() {
        return minOut;
    }

    public void setMinOut(int minOut) {
        this.minOut = minOut;
    }

    public int getMaxOut() {
        return maxOut;
    }

    public void setMaxOut(int maxOut) {
        this.maxOut = maxOut;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getStopBegin() {
        return stopBegin;
    }

    public void setStopBegin(String stopBegin) {
        this.stopBegin = stopBegin;
    }

    public Object getStopEnd() {
        return stopEnd;
    }

    public void setStopEnd(Object stopEnd) {
        this.stopEnd = stopEnd;
    }

    public List<Integer> getInDefault() {
        return inDefault;
    }

    public void setInDefault(List<Integer> inDefault) {
        this.inDefault = inDefault;
    }

    public List<Integer> getOutDefault() {
        return outDefault;
    }

    public void setOutDefault(List<Integer> outDefault) {
        this.outDefault = outDefault;
    }

    public static class NameBean {
        /**
         * zh : 支付宝
         */

        private String zh;

        public String getZh() {
            return zh;
        }

        public void setZh(String zh) {
            this.zh = zh;
        }
    }
}
