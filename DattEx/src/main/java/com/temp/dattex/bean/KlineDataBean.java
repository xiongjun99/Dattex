package com.temp.dattex.bean;

import com.icechao.klinelib.model.KLineEntity;

import java.io.Serializable;
import java.util.List;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.temp.dattex.bean
 * @FileName     : KlineBean.java
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
public class KlineDataBean implements Serializable {
    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean extends KLineEntity {
        /**
         * id : 1597152000
         * pair : btc/usdt
         * open : 11626.59
         * close : 11624.27
         * low : 11623.33
         * high : 11629.91
         * amount : 4.015199302861595
         * vol : 46676.7024139439
         * count : 190
         * period : 1min
         * usdtcny : null
         * changes : null
         */

        private long id;
        private String pair;
        private float open;
        private float close;
        private float low;
        private float high;
        private float amount;
        private float vol;
        private int count;
        private String period;
        private float usdtcny;
        private float changes;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getPair() {
            return pair;
        }

        public void setPair(String pair) {
            this.pair = pair;
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

        public float getLow() {
            return low;
        }

        public void setLow(float low) {
            this.low = low;
        }

        public float getHigh() {
            return high;
        }

        public void setHigh(float high) {
            this.high = high;
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

        public String getPeriod() {
            return period;
        }

        public void setPeriod(String period) {
            this.period = period;
        }

        public float getUsdtcny() {
            return usdtcny;
        }

        public void setUsdtcny(float usdtcny) {
            this.usdtcny = usdtcny;
        }

        public float getChanges() {
            return changes;
        }

        public void setChanges(float changes) {
            this.changes = changes;
        }

                @Override
        public Long getDate() {
            return id * 1000;
        }

        @Override
        public float getOpenPrice() {
            return open;
        }

        @Override
        public float getHighPrice() {
            return high;
        }

        @Override
        public float getLowPrice() {
            return low;
        }

        @Override
        public float getClosePrice() {
            return close;
        }

        @Override
        public float getVolume() {
            return vol;
        }
    }


//    /**
//     * ch : string
//     * data : [{"amount":0,"changes":0,"close":0,"count":0,"high":0,"id":0,"low":0,"open":0,"symbol":"string","usdtcny":0,"version":0,"vol":0}]
//     * err-code : string
//     * err-msg : string
//     * ok : true
//     * status : string
//     * ts : 0
//     */
//
//    private String ch;
//    @SerializedName("err-code")
//    private String errcode;
//    @SerializedName("err-msg")
//    private String errmsg;
//    private boolean ok;
//    private String status;
//    private String ts;
//    private List<KlineItemBean> data;
//
//    public String getCh() {
//        return ch;
//    }
//
//    public void setCh(String ch) {
//        this.ch = ch;
//    }
//
//    public String getErrcode() {
//        return errcode;
//    }
//
//    public void setErrcode(String errcode) {
//        this.errcode = errcode;
//    }
//
//    public String getErrmsg() {
//        return errmsg;
//    }
//
//    public void setErrmsg(String errmsg) {
//        this.errmsg = errmsg;
//    }
//
//    public boolean isOk() {
//        return ok;
//    }
//
//    public void setOk(boolean ok) {
//        this.ok = ok;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public String getTs() {
//        return ts;
//    }
//
//    public void setTs(String ts) {
//        this.ts = ts;
//    }
//
//    public List<KlineItemBean> getData() {
//        return data;
//    }
//
//    public void setData(List<KlineItemBean> data) {
//        this.data = data;
//    }
//
//    public static class KlineItemBean extends KLineEntity {
//        /**
//         * amount : 0
//         * changes : 0
//         * close : 0
//         * count : 0
//         * high : 0
//         * id : 0
//         * low : 0
//         * open : 0
//         * symbol : string
//         * usdtcny : 0
//         * version : 0
//         * vol : 0
//         */
//
//        public float amount;
//        public float changes;
//        public float close;
//        public float count;
//        public float high;
//        public long id;
//        public float low;
//        public float open;
//        public String symbol;
//        public float usdtcny;
//        public float version;
//        public float vol;
//
//
//        @Override
//        public Long getDate() {
//            return id * 1000;
//        }
//
//        @Override
//        public float getOpenPrice() {
//            return open;
//        }
//
//        @Override
//        public float getHighPrice() {
//            return high;
//        }
//
//        @Override
//        public float getLowPrice() {
//            return low;
//        }
//
//        @Override
//        public float getClosePrice() {
//            return close;
//        }
//
//        @Override
//        public float getVolume() {
//            return vol;
//        }
//    }
}
