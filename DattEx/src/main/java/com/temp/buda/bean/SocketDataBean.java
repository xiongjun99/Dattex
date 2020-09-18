package com.temp.buda.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.temp.dattex.bean
 * @FileName     : SocketDataBean.java
 * @Author       : chao
 * @Date         : 2020/6/5
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
public class SocketDataBean implements Serializable {

    /**
     * status : null
     * ts : 1591326668529
     * ch : market.ethusdt.kline.1min
     * tick : {"symbol":"ETH/USDT","id":1591326660,"open":244.24,"close":244.12,"high":244.25,"low":244.07,"amount":168.7127,"vol":41191.400488,"count":139,"version":null,"usdtcny":7,"changes":-0.66}
     * ok : false
     * err-code : null
     * err-msg : null
     */

    private Object status;
    private long ts;
    private String ch;
    private TickBean tick;
    private boolean ok;
    @SerializedName("err-code")
    private Object errcode;
    @SerializedName("err-msg")
    private Object errmsg;

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

    public String getCh() {
        return ch;
    }

    public void setCh(String ch) {
        this.ch = ch;
    }

    public TickBean getTick() {
        return tick;
    }

    public void setTick(TickBean tick) {
        this.tick = tick;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public Object getErrcode() {
        return errcode;
    }

    public void setErrcode(Object errcode) {
        this.errcode = errcode;
    }

    public Object getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(Object errmsg) {
        this.errmsg = errmsg;
    }

    public static class TickBean {
        /**
         * symbol : ETH/USDT
         * id : 1591326660
         * open : 244.24
         * close : 244.12
         * high : 244.25
         * low : 244.07
         * amount : 168.7127
         * vol : 41191.400488
         * count : 139
         * version : null
         * usdtcny : 7
         * changes : -0.66
         */

        private String symbol;
        private int id;
        private float open;
        private float close;
        private float high;
        private float low;
        private float amount;
        private float vol;
        private int count;
        private Object version;
        private int usdtcny;
        private float changes;

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public float getOpen() {
            return open;
        }

        public void setOpen(float open) {
            this.open = open;
        }

        public float getClose() {
            return close;
        }

        public void setClose(float close) {
            this.close = close;
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

        public float getAmount() {
            return amount;
        }

        public void setAmount(float amount) {
            this.amount = amount;
        }

        public float getVol() {
            return vol;
        }

        public void setVol(float vol) {
            this.vol = vol;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public Object getVersion() {
            return version;
        }

        public void setVersion(Object version) {
            this.version = version;
        }

        public int getUsdtcny() {
            return usdtcny;
        }

        public void setUsdtcny(int usdtcny) {
            this.usdtcny = usdtcny;
        }

        public float getChanges() {
            return changes;
        }

        public void setChanges(float changes) {
            this.changes = changes;
        }
    }
}
