package com.temp.dattex.bean;

import java.util.List;

public class OTCcfgBean {

    /**
     * success : true
     * data : {"payTypes":[{"id":1,"name":"银行卡","minIn":1000,"maxIn":10000,"inDefault":[1000,5000,10000,20000,50000],"minOut":100,"maxOut":1000,"outDefault":[100,500,1000,2000,5000],"enable":1,"stopBegin":"23:00:00","stopEnd":"24:00:00"},{"id":2,"name":"支付宝","minIn":2000,"maxIn":10000,"inDefault":[2000,5000,10000,20000,50000],"minOut":200,"maxOut":1000,"outDefault":[100,500,1000,2000,5000],"enable":1,"stopBegin":null,"stopEnd":null},{"id":3,"name":"微信","minIn":3000,"maxIn":10000,"inDefault":[3000,5000,10000,20000,50000],"minOut":300,"maxOut":1000,"outDefault":[100,500,1000,2000,5000],"enable":1,"stopBegin":null,"stopEnd":null}],"otcCfgs":[{"currency":"CNY","symbol":"¥","sellRatio":1,"buyRatio":1,"created":"2020-09-07 01:35:14","updated":"2020-09-07 01:35:14","weight":2},{"currency":"USD","symbol":"$","sellRatio":1,"buyRatio":1,"created":"2020-09-07 01:35:00","updated":"2020-09-07 01:35:00","weight":1}]}
     */
        private List<PayTypesBean> payTypes;
        private List<OtcCfgsBean> otcCfgs;
        public List<PayTypesBean> getPayTypes() {
            return payTypes;
        }

        public void setPayTypes(List<PayTypesBean> payTypes) {
            this.payTypes = payTypes;
        }

        public List<OtcCfgsBean> getOtcCfgs() {
            return otcCfgs;
        }

        public void setOtcCfgs(List<OtcCfgsBean> otcCfgs) {
            this.otcCfgs = otcCfgs;
        }

        public static class PayTypesBean {
            /**
             * id : 1
             * name : 银行卡
             * minIn : 1000
             * maxIn : 10000
             * inDefault : [1000,5000,10000,20000,50000]
             * minOut : 100
             * maxOut : 1000
             * outDefault : [100,500,1000,2000,5000]
             * enable : 1
             * stopBegin : 23:00:00
             * stopEnd : 24:00:00
             */

            private int id;
            private String name;
            private int minIn;
            private int maxIn;
            private int minOut;
            private int maxOut;
            private int enable;
            private String stopBegin;
            private String stopEnd;
            private List<Integer> inDefault;
            private List<Integer> outDefault;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
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

            public int getEnable() {
                return enable;
            }

            public void setEnable(int enable) {
                this.enable = enable;
            }

            public String getStopBegin() {
                return stopBegin;
            }

            public void setStopBegin(String stopBegin) {
                this.stopBegin = stopBegin;
            }

            public String getStopEnd() {
                return stopEnd;
            }

            public void setStopEnd(String stopEnd) {
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
        }

        public static class OtcCfgsBean {
            /**
             * currency : CNY
             * symbol : ¥
             * sellRatio : 1.0
             * buyRatio : 1.0
             * created : 2020-09-07 01:35:14
             * updated : 2020-09-07 01:35:14
             * weight : 2
             */

            private String currency;
            private String symbol;
            private double sellRatio;
            private double buyRatio;
            private String created;
            private String updated;
            private int weight;

            public String getCurrency() {
                return currency;
            }

            public void setCurrency(String currency) {
                this.currency = currency;
            }

            public String getSymbol() {
                return symbol;
            }

            public void setSymbol(String symbol) {
                this.symbol = symbol;
            }

            public double getSellRatio() {
                return sellRatio;
            }

            public void setSellRatio(double sellRatio) {
                this.sellRatio = sellRatio;
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
}
