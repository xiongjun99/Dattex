package com.temp.dattex.bean;

import com.temp.dattex.config.SymbolConfigs;

import java.io.Serializable;
import java.util.List;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.temp.dattex.bean
 * @FileName     : OrdersBean.java
 * @Author       : chao
 * @Date         : 2020/6/3
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
public class OrdersBean implements Serializable {

    /**
     * rows : [{"baseSymbol":"string","buyMarketPrice":0,"buyTime":"2020-06-03T07:31:19.197Z","coinSymbol":"string","direction":0,"endMarketPrice":0,"fee":0,"id":0,"increaseValues":0,"lever":0,"memberId":0,"price":0,"profitType":0,"quitTime":"2020-06-03T07:31:19.197Z","state":0,"stopLossPrice":0,"stopLossRates":0,"stopProfitPrice":0,"stopProfitRates":0,"symbol":"string","upordownType":0}]
     * size : 0
     * start : 0
     * total : 0
     */

    private int size;
    private int start;
    private int total;
    private List<OrderItemBean> rows;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<OrderItemBean> getRows() {
        return rows;
    }

    public void setRows(List<OrderItemBean> rows) {
        this.rows = rows;
    }

    public static class OrderItemBean {

        /**
         * baseSymbol : string
         * buyMarketPrice : 0
         * buyTime : 2020-06-03T07:31:19.197Z
         * coinSymbol : string
         * direction : 0
         * endMarketPrice : 0
         * fee : 0
         * id : 0
         * increaseValues : 0
         * lever : 0
         * memberId : 0
         * price : 0
         * profitType : 0
         * quitTime : 2020-06-03T07:31:19.197Z
         * state : 0
         * stopLossPrice : 0
         * stopLossRates : 0
         * stopProfitPrice : 0
         * stopProfitRates : 0
         * symbol : string
         * upordownType : 0
         */
        private String contractType;
        private String baseSymbol;
        private float buyMarketPrice;
        private String buyTime;
        private String coinSymbol;
        private int direction;
        private float endMarketPrice;
        private float fee;
        private long id;
        private float increaseValues;
        private float lever;
        private int memberId;
        private float price;
        private float marketPrice;
        private int profitType;
        private String quitTime;
        private int state;
        private float stopLossPrice;
        private float stopLossRates;
        private float stopProfitPrice;
        private float stopProfitRates;
        private String symbol;
        private int upordownType;
        private float finalAmount;

        public float getFinalAmount() {
            return finalAmount;
        }

        public void setFinalAmount(float finalAmount) {
            this.finalAmount = finalAmount;
        }


        public float getMarketPrice() {
            return marketPrice;
        }

        public void setMarketPrice(float marketPrice) {
            this.marketPrice = marketPrice;
        }
        public String getContractType() {
            return contractType;
        }

        public void setContractType(String contractType) {
            this.contractType = contractType;
        }
        public String getCurrentPrice() {
            SymbolConfigBean symbol = SymbolConfigs.getInstance().getSymbol(coinSymbol.toUpperCase() + "/" + baseSymbol);
            return String.format("%.0" + symbol.getCoinScale() + "f", symbol.getClose());
        }

        public String getProfit() {
            SymbolConfigBean symbol = SymbolConfigs.getInstance().getSymbol(coinSymbol.toUpperCase() + "/" + baseSymbol);
            float v = symbol.getClose() - buyMarketPrice * lever / 100 * price;
            return String.format("%.0" + symbol.getBaseCoinScale() + "f", v);
        }

        public String getBaseSymbol() {
            return baseSymbol;
        }

        public void setBaseSymbol(String baseSymbol) {
            this.baseSymbol = baseSymbol;
        }

        public float getBuyMarketPrice() {
            return buyMarketPrice;
        }

        public void setBuyMarketPrice(float buyMarketPrice) {
            this.buyMarketPrice = buyMarketPrice;
        }

        public String getBuyTime() {
            return buyTime;
        }

        public void setBuyTime(String buyTime) {
            this.buyTime = buyTime;
        }

        public String getCoinSymbol() {
            return coinSymbol;
        }

        public void setCoinSymbol(String coinSymbol) {
            this.coinSymbol = coinSymbol;
        }

        public int getDirection() {
            return direction;
        }

        public void setDirection(int direction) {
            this.direction = direction;
        }

        public float getEndMarketPrice() {
            return endMarketPrice;
        }

        public void setEndMarketPrice(float endMarketPrice) {
            this.endMarketPrice = endMarketPrice;
        }

        public float getFee() {
            return fee;
        }

        public void setFee(float fee) {
            this.fee = fee;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public float getIncreaseValues() {
            return increaseValues;
        }

        public void setIncreaseValues(float increaseValues) {
            this.increaseValues = increaseValues;
        }

        public float getLever() {
            return lever;
        }

        public void setLever(float lever) {
            this.lever = lever;
        }

        public int getMemberId() {
            return memberId;
        }

        public void setMemberId(int memberId) {
            this.memberId = memberId;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
            this.price = price;
        }

        public int getProfitType() {
            return profitType;
        }

        public void setProfitType(int profitType) {
            this.profitType = profitType;
        }

        public String getQuitTime() {
            return quitTime;
        }

        public void setQuitTime(String quitTime) {
            this.quitTime = quitTime;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public float getStopLossPrice() {
            return stopLossPrice;
        }

        public void setStopLossPrice(float stopLossPrice) {
            this.stopLossPrice = stopLossPrice;
        }

        public float getStopLossRates() {
            return stopLossRates;
        }

        public void setStopLossRates(float stopLossRates) {
            this.stopLossRates = stopLossRates;
        }

        public float getStopProfitPrice() {
            return stopProfitPrice;
        }

        public void setStopProfitPrice(float stopProfitPrice) {
            this.stopProfitPrice = stopProfitPrice;
        }

        public float getStopProfitRates() {
            return stopProfitRates;
        }

        public void setStopProfitRates(float stopProfitRates) {
            this.stopProfitRates = stopProfitRates;
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public int getUpordownType() {
            return upordownType;
        }

        public void setUpordownType(int upordownType) {
            this.upordownType = upordownType;
        }
    }
}
